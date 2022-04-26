package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.libs.DigitalSignature;
import java.security.SignatureException;
import lombok.*;

@Getter
@Setter
public class SignatureVerification {
  @SerializedName("xsignature")
  private String xsignature;

  public SignatureVerification() {}

  public void setXsignature(String signature) {
    this.xsignature = signature;
  }

  public String getXsignature() {
    return this.xsignature;
  }

  public boolean verifySignature(String content, String publicKey) {
    try {
      return DigitalSignature.doCheck(content, this.xsignature, publicKey);
    } catch (SignatureException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return false;
    }
  }
}
