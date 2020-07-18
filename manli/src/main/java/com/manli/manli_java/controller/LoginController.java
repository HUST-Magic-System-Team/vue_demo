package com.manli.manli_java.controller;

import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.repository.TakingMedicineTimeRepository;
import com.manli.manli_java.service.LoginService;
import com.manli.manli_java.service.SmsService;
import com.manli.manli_java.service.UserInfoService;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "api/login")
public class LoginController {

    @Autowired
    private SmsService      smsService;
    @Autowired
    private LoginService    loginService;
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "sendsms", method = RequestMethod.POST)
    public ResultBean sendSms(@RequestParam("phone") String phone) {
        String phoneRegex = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";

        if (null == phone || !Pattern.matches(phoneRegex, phone)) {
            return new ResultBean(ErrorCodeEnum.PHONE_NUMBER_FORMAT_ERROR);
        }
        String smscode = smsService.genSmsCode();
        try {
            smsService.sendSmsCode(phone, smscode);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResultBean(ErrorCodeEnum.SEND_VERIFY_CODE_FAIL);
        }
        smsService.setSmsCode(phone, smscode);
        return new ResultBean(ErrorCodeEnum.OK);
    }

    @RequestMapping(value = "islogin", method = RequestMethod.POST)
    public ResultBean isLogin(@RequestParam("token") String token) {
        if (token == null) {
            return new ResultBean(ErrorCodeEnum.MISS_TOKEN);
        }

        boolean isLogin = loginService.isLogin(token);
        if (isLogin) {
            return new ResultBean(ErrorCodeEnum.OK);
        } else {
            return new ResultBean(ErrorCodeEnum.REQUIRE_LOGIN);
        }
    }

    @RequestMapping(value = "isregister", method = RequestMethod.POST)
    public ResultBean isRegister(@RequestParam("phone") String phone) {
        if (phone == null) {
            return new ResultBean(ErrorCodeEnum.MISS_PHONE_NUMBER);
        }

        boolean isRegister = loginService.isRegister(phone);
        if (isRegister) {
            return new ResultBean(ErrorCodeEnum.OK);
        } else {
            return new ResultBean(ErrorCodeEnum.PHONE_NUMBER_NO_REGISTER);
        }
    }

    @RequestMapping(value = "getUserFirstStatus", method = RequestMethod.POST)
    public ResultBean getUserFirstStatus(@RequestParam("token") String token) {
        if (token == null) {
            return new ResultBean(ErrorCodeEnum.MISS_TOKEN);
        }

        boolean isLogin = loginService.isLogin(token);
        Map<String, Object> data = new HashMap<>();
        data.put("isLogin", isLogin);

        if (isLogin == true) {
            int userId = loginService.getUserIdByToken(token);

            Map<String, Object> takingMedicineTime = userInfoService.getUserTakingMedicineTime(userId);
            data.put("isTagSet", (takingMedicineTime != null ? 1 : 0));

            boolean isAllRequiredUserInfoSet = userInfoService.isAllRequiredUserinfoSet(userId);
            data.put("isAllRequiredUserInfoSet", (isAllRequiredUserInfoSet ? 1 : 0));
        } else {
            data.put("isTagSet", null);
            data.put("isAllRequiredUserInfoSet", null);

        }

        return new ResultBean(data);

    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResultBean login(@RequestParam("phone") String phone, @RequestParam("smscode") String smscode) {

        String phoneRegex = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
        if (phone == null || !Pattern.matches(phoneRegex, phone)) {
            return new ResultBean(ErrorCodeEnum.PHONE_NUMBER_FORMAT_ERROR);
        }

        if (smscode == null) {
            return new ResultBean(ErrorCodeEnum.VERIFY_CODE_ERROR);
        }

        boolean isValid = smsService.isSmscodeValid(phone, smscode);
        if (!isValid) {
            return new ResultBean(ErrorCodeEnum.VERIFY_CODE_ERROR);
        }
        Map<String, Object> data = new HashMap<>();

        boolean isRegister = loginService.isRegister(phone);
        String token = loginService.newToken(phone);
        int userId = loginService.getUserIdByToken(token);
        Map<String, Object> takingMedicineTime = userInfoService.getUserTakingMedicineTime(userId);

        if (!isRegister) {
            userInfoService.add922Medal(userId);
            Map<String, Object> baseInfo = new HashMap<>();
            baseInfo.put("phone", phone);
            userInfoService.updateUserBaseInfo_OldVersion(userId, baseInfo);
        }

        boolean isAllRequiredUserInfoSet = userInfoService.isAllRequiredUserinfoSet(userId);

        data.put("token", token);
        data.put("isTagSet", (takingMedicineTime != null ? 1 : 0));
        data.put("isAllRequiredUserInfoSet", (isAllRequiredUserInfoSet ? 1 : 0));

        return new ResultBean(data);
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public ResultBean logout(@RequestParam("phone") String phone,
                             @RequestParam("token") String token) {
        if (phone == null) {
            return new ResultBean(ErrorCodeEnum.PHONE_NUMBER_FORMAT_ERROR);
        }
        if (token == null) {
            return new ResultBean(ErrorCodeEnum.MISS_TOKEN);
        }

        boolean isLogin = loginService.isLogin(token);
        if (!isLogin) {
            return new ResultBean(ErrorCodeEnum.REQUIRE_LOGIN);
        }

        loginService.clearToken(phone);

        return new ResultBean(ErrorCodeEnum.OK);

    }

}
