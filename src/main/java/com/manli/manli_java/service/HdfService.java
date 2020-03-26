package com.manli.manli_java.service;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class HdfService {
    @Value("${hdf.urlPrefix}")
    String urlPrefix;
    @Value("${hdf.partnerKey}")
    String partnerKey;
    @Value("${hdf.secret}")
    String secret;
    public String getHdfUrl(Integer partnerUserId){
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000); // 时间戳 毫秒转换成秒
        String signature = generateSignature(partnerKey, secret, String.valueOf(partnerUserId), timestamp);
        StringBuilder spellUrlSb = new StringBuilder();
        spellUrlSb.append(urlPrefix);
        spellUrlSb.append("?partnerKey=").append(partnerKey);
        spellUrlSb.append("&timestamp=").append(String.valueOf(timestamp));
        spellUrlSb.append("&signature=").append(signature);
        spellUrlSb.append("&partnerUserId=").append(String.valueOf(partnerUserId));
        return spellUrlSb.toString();
    }
    private String generateSignature(String partnerKey,String secret,String partenrUserId,String timestamp) {
        ArrayList<String> list = new ArrayList<>();//将数据add到list中以便后续进行排序
        list.add(partnerKey);
        list.add(secret);
        list.add(partenrUserId);
        list.add(timestamp);
        Collections.sort(list);//将list数据进行排序
        //排序完成后将数据拼接成一个字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        //最后对拼接的字符串进行Sha签名 （本例子使用Apache的DigestUtiles 进行sha签名  依赖库 compile group: 'commons-codec', name: 'commons-codec', version: '1.4'）
        return new String(Hex.encodeHex(DigestUtils.sha(sb.toString())));
    }




}
