package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.security.MD5Encoder;
import sun.misc.BASE64Encoder;
import sun.security.provider.MD5;

import javax.crypto.Cipher;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: <br>
 * @CreateDate: Created in 2019/1/31 16:38 <br>
 * @Author: hewei
 */
@Slf4j
public class RsaTest {


    /**
     * 用于封装随机产生的公钥与私钥
     */
    private static Map<Integer, String> keyMap = new HashMap<Integer, String>();

    public static void main(String[] args) throws Exception {
        //生成公钥和私钥
        genKeyPair();

        //加密字符串
        String message = "DC483E80A7A0BD9EF71D8CF973673924";

        //公钥
        keyMap.put(0, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsLdjNy/Z0XF6RlU6/8eKJOSw19pwNsgaznktl8KiQ52sEhuwBZLCUz1xay0NpI2b7XyaAQrt/sRFRksuArAm04oyR94T5+SQn0uLTAEvlGFGrBckHSlWt281pIe1QWA20u0tFpJBtlr3TMFx/EmKgpPw/nsh/y4HFUr9ghrjFPwIDAQAB");
        //私钥
        keyMap.put(1, "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKwt2M3L9nRcXpGVTr/x4ok5LDX2nA2yBrOeS2XwqJDnawSG7AFksJTPXFrLQ2kjZvtfJoBCu3+xEVGSy4CsCbTijJH3hPn5JCfS4tMAS+UYUasFyQdKVa3bzWkh7VBYDbS7S0WkkG2WvdMwXH8SYqCk/D+eyH/LgcVSv2CGuMU/AgMBAAECgYBxmQIMrYAaP4MpXDAL1eQXk0AHDoDhTE69nTeztzcP+48Q+o+lT7BtTe5p/Oep63CbcqitQNoCImoBvu9DIr53olrgzzVpiGq3NOobXs5ktbgsC3TXfSgjBe4ln8uFZ1pkk05gmfbuJFPHF+UU4BNNgzloh3wBNfAQyYFHRgnakQJBAOTl3DuOM9d1cVI47f9y67dDoS4qMLDegaib9b68quvI3gNX7s4duN4mXZaYRjyezWyqyvNT8mfehK+XQhUVg6kCQQDAkMdytpWknVmd5cqXlL4H0g71uKuLhlcSDHq2KQHouIDuXewL0CNT7Kp3qziDrlLvhm27QRAUmKgB3iwTvhKnAkEAnnuIQLP0sPlfvM4t+vBWxEzPDGepzsxAeb2CKM34ichJ74aykYOCvE1T/zDvcImDMfoPCurUq5YlyG2yE2cSAQJAPQBgPmW6x/gUwKFf6V70SBPyK0NCDKQVByGu6e/Zq/hu15QnR6K63kY1xk0WTRi3KyvBrTUyO+QULOtNuT9YNwJAAZoP5IdQMdYojIsPycWGP6A/shZYZe7tvAo5xuL+2cmBwht5GNdrgLu0Neb1iOtvr+lSzigU9Vp4mI9xV/JKHQ==df723820");


//        //公钥
//        keyMap.put(0, "MIICWwIBAAKBgQCl4jCRBofJfyciSnPNQp0c4MLUBQzA/ZRWkj32yImsUyW381My" +
//                "Gpm03pIi3BLeSL0pRoPJKwdLJYGCLwGEa5JcD8KIcBuh+woVOiOtGyIb+9Yz99eH" +
//                "ryCfzYuKF4Zv/5gtdxVkvC7b1/d+8dX7zYBJLhxOlHZ8WmP/ylRkVTa4SQIDAQAB" +
//                "AoGAUEXOvr9sQkO3yk5BD5kmsmzJmxP+gf20JNiMB7ovdSafYA7tuZdJvk7eZOQe" +
//                "dVa5wxmOeHDMDL9PlpHsqAnuPrkXEb7jbCs3pwpCd/5ke77J265YfOZOgklGnPNj" +
//                "s4i3GmjpBA7snlqQcj2Tb18VCzHDGNZjLDa5bKwWi5+HLaECQQDt8K82wvEPwH/2" +
//                "X4Rzy21GiTDSr1AUAx8Je7MQ+mjXNWG78tiihkA/mzmp7QJDlghgHoujh0IMQR+Q" +
//                "IMDF1PvVAkEAsnlnQwYKOWD5XCoLGkg3T5hSVMwrJvpeRGr4/j5ZrxrZCKX4WSHr" +
//                "+/K2d+S8wFWHm2SH/IKF/ZAhERapiGrIpQJAeAR8TLH96AUgRoSdic7leru+jbby" +
//                "PHXqlNz5UKoOcecyOhxfW3M+Oxcv+d9b0f1+kgU2SDD0hrvekNOEiQVKVQJAFfik" +
//                "Ibgoog+yIwp5+25QgNf7qrzHwzCnUjzpEkOSvt/LQClOfYqThpzGodPi+LqOyy6B" +
//                "HzDiVejcaUIk9MpQkQJAHiyiWSVlGiI7O8c006dXaTrikd4zwOcz9KRFeABoAv2G" +
//                "s3Er178nHaygTF+m1Jiyp8C5EMiluohxb+896nopxQ==");
//        //私钥
//        keyMap.put(1, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCl4jCRBofJfyciSnPNQp0c4MLU" +
//                "BQzA/ZRWkj32yImsUyW381MyGpm03pIi3BLeSL0pRoPJKwdLJYGCLwGEa5JcD8KI" +
//                "cBuh+woVOiOtGyIb+9Yz99eHryCfzYuKF4Zv/5gtdxVkvC7b1/d+8dX7zYBJLhxO" +
//                "lHZ8WmP/ylRkVTa4SQIDAQAB");


        //RSA 加密
        String messageEn = encrypt(message, keyMap.get(0));

        String urlEncoder = URLEncoder.encode(messageEn, "utf-8");


        log.debug("数据加密前:" + message);
        log.debug("RSA加密后:" + messageEn);
//        log.debug("URLEncoder:" + urlEncoder);


        String urlDecoder = URLDecoder.decode(urlEncoder, "utf-8");
        String messageDe = decrypt(urlDecoder, keyMap.get(1));


//        log.debug("URLDecoder:" + urlDecoder);
        log.debug("解析出原数据:" + messageDe);
    }

    /**
     * 随机生成密钥对
     *
     * @throws NoSuchAlgorithmException
     */
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥

        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));

        publicKeyString = java.util.Base64.getEncoder().encodeToString(publicKey.getEncoded());

        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));

        privateKeyString = java.util.Base64.getEncoder().encodeToString(privateKey.getEncoded());
        // 将公钥和私钥保存到Map
//        keyMap.put(0, publicKeyString);  //0表示公钥
//        keyMap.put(1, privateKeyString);  //1表示私钥

        log.debug("---publicKeyString:" + publicKeyString);
        log.debug("---privateKeyString:" + privateKeyString);

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
        //base64解码公钥
        byte[] decoded = Base64.decodeBase64(publicKey.getBytes());

        log.debug(new String(decoded));

        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));



        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("utf-8")));
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str        字符串
     * @param privateKey 私钥
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("utf-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }


}
