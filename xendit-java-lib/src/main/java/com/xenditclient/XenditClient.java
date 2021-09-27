package com.xenditclient;

import com.xenditclient.balance.BalanceClient;
import com.xenditclient.batchDisbursements.BatchDisbursementClient;
import com.xenditclient.cardlessCredit.CardlessCreditClient;
import com.xenditclient.creditCard.CreditCardClient;
import com.xenditclient.customer.CustomerClient;
import com.xenditclient.disbursement.DisbursementClient;
import com.xenditclient.ewallet.EWalletClient;
import com.xenditclient.invoice.InvoiceClient;
import com.xenditclient.payout.PayoutClient;
import com.xenditclient.qrCode.QRCodeClient;
import com.xenditclient.retailOutlet.RetailOutletClient;
import com.xenditclient.virtualAccount.FixedVirtualAccountClient;

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
    xenditClient.retailOutlet = new RetailOutletClient(option,Xendit.getRequestClient());
    xenditClient.batchDisbursement = new BatchDisbursementClient(option,Xendit.getRequestClient());
    xenditClient.cardlessCredit = new CardlessCreditClient(option,Xendit.getRequestClient());
    xenditClient.creditCard = new CreditCardClient(option,Xendit.getRequestClient());
    xenditClient.fixedVirtualAccount = new FixedVirtualAccountClient(option,Xendit.getRequestClient());
  }
}
