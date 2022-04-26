package com.xendit.libs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

public class DigitalSignature {

  public static boolean doCheck(String content, String sign, String publicKey)
      throws SignatureException {
    try {
      PublicKey pubKey =
          getPublicKeyFromX509("RSA", new ByteArrayInputStream(publicKey.getBytes()));
      Signature signature = Signature.getInstance("SHA256withRSA");
      signature.initVerify(pubKey);
      signature.update(content.getBytes(StandardCharsets.UTF_8.toString()));
      return signature.verify(Base64.decodeBase64(sign.getBytes()));
    } catch (Exception e) {
      throw new SignatureException(
          "RSA Signature[content = "
              + content
              + "; charset = "
              + StandardCharsets.UTF_8.toString()
              + "; signature = "
              + sign
              + "]exception!",
          e);
    }
  }

  public static String doSign(String content, String privateKey) throws SignatureException {
    try {
      PrivateKey priKey =
          getPrivateKeyFromPKCS8("RSA", new ByteArrayInputStream(privateKey.getBytes()));
      Signature signature = Signature.getInstance("SHA256withRSA");
      signature.initSign(priKey);
      signature.update(content.getBytes(StandardCharsets.UTF_8.toString()));
      byte[] signed = signature.sign();
      return new String(Base64.encodeBase64(signed));
    } catch (Exception e) {
      e.printStackTrace();
      throw new SignatureException(
          "RSA Signature[content = "
              + content
              + "; charset = "
              + StandardCharsets.UTF_8.toString()
              + "] exception!",
          e);
    }
  }

  private static PrivateKey getPrivateKeyFromPKCS8(String algorithm, ByteArrayInputStream ins) {
    try {
      KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
      byte[] encodedKey = IOUtils.toByteArray(ins);
      encodedKey = Base64.decodeBase64(encodedKey);
      return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    } catch (IOException var4) {
      var4.printStackTrace();
    } catch (InvalidKeySpecException var5) {
      var5.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static PublicKey getPublicKeyFromX509(String algorithm, ByteArrayInputStream ins) {
    try {
      KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
      byte[] encodedKey = IOUtils.toByteArray(ins);
      encodedKey = Base64.decodeBase64(encodedKey);
      return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    } catch (IOException var5) {
      var5.printStackTrace();
    } catch (InvalidKeySpecException var6) {
      var6.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return null;
  }
}
