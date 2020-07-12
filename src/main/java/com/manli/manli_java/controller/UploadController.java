package com.manli.manli_java.controller;

import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.service.ElectronicRecordService;
import com.manli.manli_java.util.FileType;
import com.manli.manli_java.util.FileTypeJudge;
import com.manli.manli_java.util.ResultBean;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/upload")
public class UploadController {
    @Autowired
    ElectronicRecordService electronicRecordService;
    @Value("${manli.upload.electronic_record.storage_prefix}")
    private String storePrefix;
    @Value("${manli.upload.electronic_record.website_prefix}")
    private String websitePrefix;

    @RequestMapping(value = "electronicrecord")
    public ResultBean electronicRecord(@RequestParam("file") MultipartFile uploadedFile,
                                       @RequestParam("checkTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkTime,
                                       HttpServletRequest request) {
        if (uploadedFile.isEmpty()) {
            return new ResultBean(ErrorCodeEnum.ONLY_IMAGES);
        }
        if (null == checkTime) {
            return new ResultBean(ErrorCodeEnum.MISS_CHECK_DATE);
        }

        try {
            FileType fileType = FileTypeJudge.getType(uploadedFile.getInputStream());
            if (!fileType.equals(FileType.PNG) && !fileType.equals(FileType.JPEG) && !fileType.equals(FileType.GIF)) {
                return new ResultBean(ErrorCodeEnum.ONLY_IMAGES);
            }

            File destDir = new File(storePrefix);
            if (!destDir.exists()) {
                destDir.getParentFile().mkdir();
            }

            String fileName = uploadedFile.getOriginalFilename();
            String[] splitBlock = fileName.split("\\.");
            String ext = splitBlock[splitBlock.length - 1];

            String hashName = DigestUtils.md5Hex(fileName + System.currentTimeMillis() + "") + "." + ext;
            String destFilePath = destDir + "/" + hashName;

            uploadedFile.transferTo(new File(destFilePath)); //保存文件

            Integer userId = (Integer) request.getAttribute("userId");
            electronicRecordService.add(userId, hashName, checkTime);

            Map<String, Object> data = new HashMap<>();
            data.put("url", websitePrefix + hashName);
            data.put("hashName", hashName);

            return new ResultBean(data);

        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ResultBean(ErrorCodeEnum.ONLY_IMAGES);
    }

    @RequestMapping(value = "del_electronicrecord")
    public ResultBean delElectronicrecord(@RequestParam("hashName") String hashName,
                                          HttpServletRequest request) {

        File destFile = new File(storePrefix + hashName);
        if (!destFile.exists()) {
            return new ResultBean(ErrorCodeEnum.FILE_NOT_EXIST);
        }

        try {
            destFile.delete();
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
            return new ResultBean(ErrorCodeEnum.FILE_NOT_EXIST);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResultBean(ErrorCodeEnum.FILE_NOT_EXIST);
        }

        Integer userId = (Integer) request.getAttribute("userId");
        electronicRecordService.remove(userId, hashName);

        return new ResultBean(ErrorCodeEnum.OK);
    }


}
