package com.xendit.enums;


import javax.annotation.Nullable;

public enum BankCode {
    BNI("BNI"),
    MANDIRI("MANDIRI"),
    BRI("BRI"),
    BCA("BCA"),
    PERMATA("PERMATA");

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