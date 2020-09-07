package com.xendit.enums;

import javax.annotation.Nullable;

public enum BankCode {
  BNI("BNI"),
  BNI_SYARIAH("BNI_SYARIAH"),
  MANDIRI("MANDIRI"),
  BRI("BRI"),
  BCA("BCA"),
  PERMATA("PERMATA"),
  SINARMAS("SINARMAS"),
  SAHABAT_SAMPOERNA("SAHABAT_SAMPOERNA");

  private String text;

  BankCode(String text) {
    this.text = text;
  }

  public String getText() {
    return this.text;
  }

  @Nullable
  public static BankCode fromString(String text) {
    for (BankCode m : BankCode.values()) {
      if (m.text.equalsIgnoreCase(text)) {
        return m;
      }
    }
    return null;
  }
}
