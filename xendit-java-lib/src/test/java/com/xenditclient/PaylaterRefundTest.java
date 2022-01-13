package com.xenditclient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.PaylaterRefund;
import com.xendit.model.PaylaterEnum.RefundReasons;
import com.xendit.model.PaylaterClient;
import com.xendit.model.PaylaterEnum;
import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class PaylaterRefundTest {
        private static String URL = String.format("%s%s", Xendit.Opt.getXenditURL(), "/paylater/charges");
        private static String CHARGE_ID = "test-charge-id";
        private static String REFUND_ID = "test-refund-id";
        private static Number AMOUNT = new Integer("1000");
        private static PaylaterEnum.RefundReasons REASON = RefundReasons.OTHERS;
        private static Map<String, String> HEADERS = new HashMap<>();
        private static Map<String, Object> PARAMS = new HashMap<>();
        NetworkClient requestClient = mock(BaseRequest.class);
        Xendit.Option opt = mock(Xendit.Option.class);
        PaylaterClient paylaterClient = mock(PaylaterClient.class);
        private static PaylaterRefund VALID_PAYLATER_REFUND = PaylaterRefund.builder()
                        .chargeId(CHARGE_ID)
                        .amount(AMOUNT)
                        .reason(REASON)
                        .build();

        @Before
        public void initMocks() {
                Xendit.Opt.setApiKey(
                                "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
                Xendit.setRequestClient(requestClient);

                HEADERS.clear();
                PARAMS.clear();
        }

        @Test
        public void createPaylaterRefund_Success_IfParamsIsValid() throws XenditException {
                String url = String.format("%s%s%s", URL, "/", CHARGE_ID, "/refunds");
                PARAMS.put("amount", AMOUNT);
                PARAMS.put("reason", REASON);

                when(this.requestClient.request(
                                RequestResource.Method.POST,
                                URL,
                                HEADERS,
                                PARAMS,
                                opt.getApiKey(),
                                PaylaterRefund.class))
                                                .thenReturn(VALID_PAYLATER_REFUND);
                when(paylaterClient.createPaylaterRefund(CHARGE_ID, AMOUNT, REASON)).thenReturn(VALID_PAYLATER_REFUND);

                PaylaterRefund paylaterRefund = paylaterClient.createPaylaterRefund(CHARGE_ID, AMOUNT, REASON);

                assertEquals(VALID_PAYLATER_REFUND, paylaterRefund);
        }

        @Test
        public void createPaylaterCharge_Success_WithHeaderProvided() throws XenditException {
                String url = String.format("%s%s%s", URL, "/", CHARGE_ID, "/refunds");
                PARAMS.put("amount", AMOUNT);
                PARAMS.put("reason", REASON);
                HEADERS.put("for-user-id", "user-id");

                when(this.requestClient.request(
                                RequestResource.Method.POST,
                                URL,
                                HEADERS,
                                PARAMS,
                                opt.getApiKey(),
                                PaylaterRefund.class))
                                                .thenReturn(VALID_PAYLATER_REFUND);
                when(paylaterClient.createPaylaterRefund(HEADERS, CHARGE_ID, AMOUNT, REASON))
                                .thenReturn(VALID_PAYLATER_REFUND);

                PaylaterRefund paylaterRefund = paylaterClient.createPaylaterRefund(HEADERS, CHARGE_ID, AMOUNT, REASON);

                assertEquals(paylaterRefund, VALID_PAYLATER_REFUND);
        }

        @Test(expected = XenditException.class)
        public void createPaylaterCharge_ThrowsException_IfParamsIsInvalid() throws XenditException {
                String url = String.format("%s%s", URL, "/fake_id/refunds");
                PARAMS.put("amount", AMOUNT);
                PARAMS.put("reason", REASON);

                when(this.requestClient.request(
                                RequestResource.Method.POST,
                                URL,
                                new HashMap<>(),
                                PARAMS,
                                opt.getApiKey(),
                                PaylaterRefund.class))
                                                .thenThrow(new XenditException("Paylater Refund not found"));
                when(paylaterClient.createPaylaterRefund("fake_id", AMOUNT, REASON))
                                .thenThrow(new XenditException("Paylater Refund not found"));

                paylaterClient.createPaylaterRefund("fake_id", AMOUNT, REASON);
        }

        @Test
        public void getPaylaterRefundStatus_Success_IfIdIsAvailable() throws XenditException {
                String url = String.format("%s/%s%s%s%s", URL, "/", CHARGE_ID, "/refunds/", REFUND_ID);

                when(this.requestClient.request(
                                RequestResource.Method.GET, url, null, opt.getApiKey(), PaylaterRefund.class))
                                                .thenReturn(VALID_PAYLATER_REFUND);
                when(paylaterClient.getPaylaterRefundStatus(CHARGE_ID, REFUND_ID)).thenReturn(VALID_PAYLATER_REFUND);

                PaylaterRefund paylaterRefund = paylaterClient.getPaylaterRefundStatus(CHARGE_ID, REFUND_ID);

                assertEquals(VALID_PAYLATER_REFUND, paylaterRefund);
        }

        @Test(expected = XenditException.class)
        public void getPaylaterChargeStatus_ThrowsException_IfIdIsNotAvailable() throws XenditException {
                String NOT_AVAILABLE_ID = "not-available-id";
                String url = String.format("%s/%s%s%s%s", URL, "/", CHARGE_ID, "/refunds/", NOT_AVAILABLE_ID);

                when(this.requestClient.request(
                                RequestResource.Method.GET, url, null, opt.getApiKey(), PaylaterRefund.class))
                                                .thenThrow(new XenditException("Paylater Refund data not found"));
                when(paylaterClient.getPaylaterRefundStatus(CHARGE_ID, NOT_AVAILABLE_ID))
                                .thenThrow(new XenditException("Paylater Refund data not found"));

                paylaterClient.getPaylaterRefundStatus(CHARGE_ID, NOT_AVAILABLE_ID);
        }
}
