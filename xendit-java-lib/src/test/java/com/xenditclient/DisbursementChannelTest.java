package com.xenditclient;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.DisbursementChannel;
import com.xendit.model.DisbursementClient;
import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class DisbursementChannelTest {
  private static String URL =
      String.format("%s%s", Xendit.Opt.getXenditURL(), "/disbursement-channels");
  private static String CHANNEL_CODE = "PH_CIMB";
  private static String NAME = "CIMB Bank Philippines Inc";
  private static String CHANNEL_CATEGORY = "BANK";
  private static String CURRENCY = "PHP";
  private static Integer MINIMUM = 50000;
  private static Integer MAXIMUM = 200000000;
  private static Double MINIMUM_INCREMENT = 0.01;

  private static Map<String, Object> PARAMS = new HashMap<>();
  private static Map<String, String> HEADERS = new HashMap<>();
  NetworkClient requestClient = mock(BaseRequest.class);
  Xendit.Option opt = mock(Xendit.Option.class);
  DisbursementClient disbursementClient = mock(DisbursementClient.class);

  private static DisbursementChannel VALID_CHANNEL =
      DisbursementChannel.builder()
          .channelCode(CHANNEL_CODE)
          .name(NAME)
          .channelCategory(CHANNEL_CATEGORY)
          .currency(CURRENCY)
          .minimum(MINIMUM)
          .maximum(MAXIMUM)
          .minimumIncrement(MINIMUM_INCREMENT)
          .build();
  private static DisbursementChannel[] DISBURSEMENTCHANNEL_ARRAY =
      new DisbursementChannel[] {VALID_CHANNEL};

  @Before
  public void initMocks() {
    Xendit.Opt.setApiKey(
        "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
    Xendit.setRequestClient(requestClient);
    HEADERS.clear();
    PARAMS.clear();
  }

  @Test
  public void getDisbursementChannels_Success_IfMethodCalledCorrectly() throws XenditException {
    when(this.requestClient.request(
            RequestResource.Method.GET,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            DisbursementChannel[].class))
        .thenReturn(DISBURSEMENTCHANNEL_ARRAY);
    when(disbursementClient.getDisbursementChannels(HEADERS)).thenReturn(DISBURSEMENTCHANNEL_ARRAY);

    DisbursementChannel disbursementChannels[] =
        disbursementClient.getDisbursementChannels(HEADERS);

    assertArrayEquals(DISBURSEMENTCHANNEL_ARRAY, disbursementChannels);
  }

  @Test(expected = XenditException.class)
  public void getDisbursementChannels_ThrowsException_IfServerError() throws XenditException {
    when(this.requestClient.request(
            RequestResource.Method.GET,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            DisbursementChannel[].class))
        .thenThrow(new XenditException("Something went wrong"));
    when(disbursementClient.getDisbursementChannels(new HashMap<>()))
        .thenThrow(new XenditException("Something went wrong"));
    disbursementClient.getDisbursementChannels(new HashMap<>());
  }

  @Test
  public void getByChannelCategory_Success_IfChannelCategoryIsAvailable() throws XenditException {
    String url = String.format("%s?channel_category=%s", URL, CHANNEL_CATEGORY);

    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), DisbursementChannel[].class))
        .thenReturn(DISBURSEMENTCHANNEL_ARRAY);
    when(disbursementClient.getByChannelCategory(HEADERS, CHANNEL_CATEGORY))
        .thenReturn(DISBURSEMENTCHANNEL_ARRAY);

    DisbursementChannel[] disbursementChannels =
        disbursementClient.getByChannelCategory(HEADERS, CHANNEL_CATEGORY);

    assertArrayEquals(DISBURSEMENTCHANNEL_ARRAY, disbursementChannels);
  }

  @Test(expected = XenditException.class)
  public void getByChannelCategory_ThrowsException_IfChannelCategoryIsNotAvailable()
      throws XenditException {
    String url = String.format("%s?channel_category=%s", URL, CHANNEL_CATEGORY);

    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), DisbursementChannel[].class))
        .thenThrow(new XenditException("Channel not found"));
    when(disbursementClient.getByChannelCategory(HEADERS, CHANNEL_CATEGORY))
        .thenThrow(new XenditException("Channel not found"));

    disbursementClient.getByChannelCategory(HEADERS, CHANNEL_CATEGORY);
  }

  @Test(expected = XenditException.class)
  public void getByChannelCategory_ThrowsException_IfInvalidArgs() throws XenditException {
    String url = String.format("%s?channel_category=%s", URL, CHANNEL_CATEGORY);

    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), DisbursementChannel[].class))
        .thenThrow(new XenditException("Invalid Arguments"));
    when(disbursementClient.getByChannelCategory(HEADERS, ""))
        .thenThrow(new XenditException("Invalid Arguments"));

    disbursementClient.getByChannelCategory(HEADERS, "");
  }

  @Test
  public void getByChannelCode_Success_IfChannelCodeIsAvailable() throws XenditException {
    String url = String.format("%s?channel_code=%s", URL, CHANNEL_CODE);

    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), DisbursementChannel[].class))
        .thenReturn(DISBURSEMENTCHANNEL_ARRAY);
    when(disbursementClient.getByChannelCode(HEADERS, CHANNEL_CODE))
        .thenReturn(DISBURSEMENTCHANNEL_ARRAY);

    DisbursementChannel[] disbursementChannels =
        disbursementClient.getByChannelCode(HEADERS, CHANNEL_CODE);

    assertArrayEquals(DISBURSEMENTCHANNEL_ARRAY, disbursementChannels);
  }

  @Test(expected = XenditException.class)
  public void getByChannelCode_ThrowsException_IfChannelCodeIsNotAvailable()
      throws XenditException {
    String url = String.format("%s?channel_code=%s", URL, CHANNEL_CODE);

    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), DisbursementChannel[].class))
        .thenThrow(new XenditException("Channel not found"));
    when(disbursementClient.getByChannelCode(HEADERS, CHANNEL_CODE))
        .thenThrow(new XenditException("Channel not found"));

    disbursementClient.getByChannelCode(HEADERS, CHANNEL_CODE);
  }

  @Test(expected = XenditException.class)
  public void getByChannelCode_ThrowsException_IfInvalidArgs() throws XenditException {
    String url = String.format("%s?channel_code=%s", URL, CHANNEL_CODE);

    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), DisbursementChannel[].class))
        .thenThrow(new XenditException("Invalid Arguments"));
    when(disbursementClient.getByChannelCode(HEADERS, ""))
        .thenThrow(new XenditException("Invalid Arguments"));

    disbursementClient.getByChannelCode(HEADERS, "");
  }
}
