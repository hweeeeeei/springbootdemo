package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Description: <br>
 * @CreateDate: Created in 2019/2/11 11:34 <br>
 * @Author: hewei
 */
@Slf4j
public class DemoT1 {


    // 公钥配对  (给前端使用)
    private static final String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsLdjNy/Z0XF6RlU6/8eKJOSw19pwNsgaznktl8KiQ52sEhuwBZLCUz1xay0NpI2b7XyaAQrt/sRFRksuArAm04oyR94T5+SQn0uLTAEvlGFGrBckHSlWt281pIe1QWA20u0tFpJBtlr3TMFx/EmKgpPw/nsh/y4HFUr9ghrjFPwIDAQAB";

    public static void main(String[] args) throws Exception {


        encrypt("666666", RSA_PUBLIC_KEY);
    }

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String str, String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);

//        log.debug("--------decoded:" + decoded.toString());

        log.debug("--------decoded:" + new String(decoded));
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("utf-8")));
        return outStr;
    }

}
