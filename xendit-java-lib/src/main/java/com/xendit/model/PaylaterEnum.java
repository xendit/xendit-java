package com.xendit.model;

public class PaylaterEnum {
    public enum RefundStatus {
        SUCCEEDED,
        FAILED
    }

    public enum RefundFailureReason {
        INSUFFICIENT_BALANCE,
        REFUND_FAILED
    }

    public enum RefundReasons {
        DUPLICATE,
        FRAUDULENT,
        CHANGE_OF_MIND,
        CHANGE_PAYMENT_METHOD,
        UNFULFILLED_ITEM,
        DEFECTIVE_ITEM,
        OTHERS
    }
}
