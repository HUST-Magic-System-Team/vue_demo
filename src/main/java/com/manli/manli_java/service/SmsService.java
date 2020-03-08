package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.PhoneSmscodeEntity;
import com.manli.manli_java.repository.PhoneSmscodeRepository;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

@Service
public class SmsService {
    @Value("${manli.sms.universal}")
    private String smsUniversal;

    @Autowired
    private PhoneSmscodeRepository phoneSmscodeRepository;


    public String genSmsCode() {
        long seed = new Date().getTime();
        Random rd = new Random(seed);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(String.valueOf(rd.nextInt(10)));
        }
        return sb.toString();
    }

    @Transactional
    public void sendSmsCode(String phone, String smscode) {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", "key-084089172f0ab387e4a35d2dc666271b"));
        WebResource webResource = client.resource("http://sms-api.luosimao.com/v1/send.json");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("mobile", phone);
        formData.add("message", "验证码__CODE__, 15分钟之内有效【慢粒管家】".replaceAll("__CODE__", smscode));

        ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .post(ClientResponse.class, formData);

    }

    @Transactional
    public void setSmsCode(String phone, String smscode) {
        Timestamp expire = new Timestamp(System.currentTimeMillis() + 60 * 15 * 1000);  //15min expire

        PhoneSmscodeEntity phoneSmscodeEntity = phoneSmscodeRepository.findByPhone(phone);
        phoneSmscodeEntity.setPhone(phone);
        phoneSmscodeEntity.setSmscode(smscode);
        phoneSmscodeEntity.setExpire(expire);
        phoneSmscodeRepository.save(phoneSmscodeEntity);

    }

    public boolean isSmscodeValid(String phone, String smscode) {
        if (this.smsUniversal.equals(smscode)) {
            return true;
        }

        PhoneSmscodeEntity phoneSmscodeEntity = phoneSmscodeRepository.findOneByPhoneAndSmscode(phone, smscode);
        if (null != phoneSmscodeEntity) {
            Timestamp ts = phoneSmscodeEntity.getExpire();
            long expire = 60 * 15 * 1000;
            if (System.currentTimeMillis() - ts.getTime() > expire) {
                return false;
            } else {
                return true;
            }
        }

        return false;
    }

}
