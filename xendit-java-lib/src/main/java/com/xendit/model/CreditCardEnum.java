package com.xendit.model;

public class CreditCardEnum {
  public enum ChargeStatus {
    CAPTURED,
    AUTHORIZED,
    REVERSED,
    FAILED
  }

  public enum CardType {
    DEBIT,
    CREDIT
  }

  public enum ChargeType {
    SINGLE_USE_TOKEN,
    MULTIPLE_USE_TOKEN,
    RECURRING
  }

  public enum EciCode {
    INCOMPLETE_AUTH("01"),
    SUCCESSFUL_AUTH_MASTECARD("02"),
    SUCCESSFUL_AUTH_VISA("05"),
    AUTH_ATTEMPTED("06"),
    UNABLE_AUTH("07");

    private String val;

    EciCode(String val) {
      this.val = val;
    }
  }

  public enum FailureReason {
    EXPIRED_CARD,
    CARD_DECLINED,
    INSUFFICIENT_BALANCE,
    STOLEN_CARD,
    INACTIVE_CARD,
    INVALID_CVN,
    PROCESSOR_ERROR,
    BIN_BLOCK
  }
}
