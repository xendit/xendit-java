package com.xendit;

import com.xendit.model.balance.BalanceClient;
import com.xendit.model.batchDisbursements.BatchDisbursementClient;
import com.xendit.model.cardlessCredit.CardlessCreditClient;
import com.xendit.model.creditCard.CreditCardClient;
import com.xendit.model.customer.CustomerClient;
import com.xendit.model.directDebit.DirectDebitPaymentClient;
import com.xendit.model.disbursement.DisbursementClient;
import com.xendit.model.ewallet.EWalletClient;
import com.xendit.model.invoice.InvoiceClient;
import com.xendit.model.payout.PayoutClient;
import com.xendit.model.qrCode.QRCodeClient;
import com.xendit.model.recurringPayment.RecurringPaymentClient;
import com.xendit.model.retailOutlet.RetailOutletClient;
import com.xendit.model.virtualAccount.FixedVirtualAccountClient;

public class XenditClient {

  private static Xendit.Option opt;
  public InvoiceClient invoice;
  public BalanceClient balance;
  public PayoutClient payout;
  public DisbursementClient disbursement;
  public EWalletClient eWallet;
  public QRCodeClient qrCode;
  public CustomerClient customer;
  public RetailOutletClient retailOutlet;
  public BatchDisbursementClient batchDisbursement;
  public CardlessCreditClient cardlessCredit;
  public CreditCardClient creditCard;
  public FixedVirtualAccountClient fixedVirtualAccount;
  public RecurringPaymentClient recurringPayment;
  public DirectDebitPaymentClient directDebitPayment;

  private XenditClient() {}

  public static class Builder {

    private String apikey;

    public Builder() {}

    public Builder apikey(String apikey) {
      this.apikey = apikey;
      return this;
    }

    public XenditClient build() {
      Xendit.Option option = new Xendit.Option();
      option.setApiKey(this.apikey);
      return initClient(option);
    }

    public String getApikey() {
      return apikey;
    }
  }

  private static XenditClient initClient(Xendit.Option option) {
    XenditClient.opt = option;
    XenditClient xenditClient = new XenditClient();
    buildClient(option, xenditClient);
    return xenditClient;
  }

  private static void buildClient(Xendit.Option option, XenditClient xenditClient) {
    xenditClient.invoice = new InvoiceClient(option, Xendit.getRequestClient());
    xenditClient.balance = new BalanceClient(option, Xendit.getRequestClient());
    xenditClient.disbursement = new DisbursementClient(option, Xendit.getRequestClient());
    xenditClient.payout = new PayoutClient(option, Xendit.getRequestClient());
    xenditClient.eWallet = new EWalletClient(option, Xendit.getRequestClient());
    xenditClient.qrCode = new QRCodeClient(option, Xendit.getRequestClient());
    xenditClient.customer = new CustomerClient(option, Xendit.getRequestClient());
    xenditClient.retailOutlet = new RetailOutletClient(option, Xendit.getRequestClient());
    xenditClient.batchDisbursement = new BatchDisbursementClient(option, Xendit.getRequestClient());
    xenditClient.cardlessCredit = new CardlessCreditClient(option, Xendit.getRequestClient());
    xenditClient.creditCard = new CreditCardClient(option, Xendit.getRequestClient());
    xenditClient.fixedVirtualAccount =
        new FixedVirtualAccountClient(option, Xendit.getRequestClient());
    xenditClient.recurringPayment = new RecurringPaymentClient(option, Xendit.getRequestClient());
    xenditClient.directDebitPayment =
        new DirectDebitPaymentClient(option, Xendit.getRequestClient());
  }
}
