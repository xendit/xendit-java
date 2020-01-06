package com.xendit.model;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Invoice.class)
public class InvoiceTest {
    private static final String SAMPLE_KEY = "xnd_development_O46JfOtygef9kMNsK+ZPGT+ZZ9b3ooF4w3Dn+R1k+2fT/7GlCAN3jg==:";
    private static final String INVALID_KEY = "xnd_development_...";

    private Invoice initExpiredInvoice() {
        Map<String, Object> params = new HashMap<>();
        params.put("external_id", "my_external_id");
        params.put("id", "my_id");
        params.put("status", "EXPIRED");
        return new Invoice(params);
    }

    @Test
    public void create_Success_IfApiKeyIsValid() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;

        PowerMockito.mockStatic(Invoice.class);
        when(Invoice.create("test_id", 100000, "test@email.com", "Testing")).thenReturn(new Invoice());
        Invoice invoice = Invoice.create("test_id", 100000, "test@email.com", "Testing");
        assertNotNull(invoice);
        assertThat(invoice, instanceOf(Invoice.class));
    }

    @Test
    public void create_Success_IfParamsAreValid() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;

        Map<String, Object> params = new HashMap<>();
        params.put("external_id", "test_id");
        params.put("amount", 100000);
        params.put("payer_email", "test@email.com");
        params.put("description", "Testing");

        PowerMockito.mockStatic(Invoice.class);
        when(Invoice.create(params)).thenReturn(new Invoice());
        Invoice invoice = Invoice.create(params);
        assertNotNull(invoice);
        assertThat(invoice, instanceOf(Invoice.class));
    }

    @Test(expected = XenditException.class)
    public void create_ThrowsException_IfApiKeyIsInvalid() throws XenditException {
        Xendit.apiKey = INVALID_KEY;

        PowerMockito.mockStatic(Invoice.class);
        when(Invoice.create("test_id", 100000, "test@email.com", "Testing")).thenThrow(XenditException.class);
        Invoice.create("test_id", 100000, "test@email.com", "Testing");
    }

    @Test(expected = XenditException.class)
    public void create_ThrowsException_IfAmountIsLessThanMinimum() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;

        PowerMockito.mockStatic(Invoice.class);
        when(Invoice.create("test_id", 1000, "test@email.com", "Testing")).thenThrow(XenditException.class);
        Invoice.create("test_id", 1000, "test@email.com", "Testing");
    }

    @Test(expected = XenditException.class)
    public void create_ThrowsException_IfAmountIsGreaterThanMaximum() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;

        PowerMockito.mockStatic(Invoice.class);
        when(Invoice.create("test_id", 1000000001, "test@email.com", "Testing")).thenThrow(XenditException.class);
        Invoice.create("test_id", 1000000001, "test@email.com", "Testing");
    }

    @Test(expected = XenditException.class)
    public void create_ThrowsException_IfInvalidParams() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;

        Map<String, Object> params = new HashMap<>();
        params.put("externals_id", "test_id");
        params.put("amount", 100000);
        params.put("description", "Testing");

        PowerMockito.mockStatic(Invoice.class);
        when(Invoice.create(params)).thenThrow(XenditException.class);
        Invoice.create(params);
    }

    @Test
    public void getById_Success_IfIdIsAvailable() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;

        PowerMockito.mockStatic(Invoice.class);
        when(Invoice.getById("5e0cb0bbf4d38b20d5421b72")).thenReturn(new Invoice());
        Invoice invoice = Invoice.getById("5e0cb0bbf4d38b20d5421b72");
        assertNotNull(invoice);
        assertThat(invoice, instanceOf(Invoice.class));
    }

    @Test(expected = XenditException.class)
    public void getById_ThrowsException_IfApiKeyIsInvalid() throws XenditException {
        Xendit.apiKey = INVALID_KEY;

        PowerMockito.mockStatic(Invoice.class);
        when(Invoice.getById("5e0cb0bbf4d38b20d5421b72")).thenThrow(XenditException.class);
        Invoice.getById("5e0cb0bbf4d38b20d5421b72");
    }

    @Test(expected = XenditException.class)
    public void getById_ThrowsException_IfIdIsNotAvailable() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;

        PowerMockito.mockStatic(Invoice.class);
        when(Invoice.getById("012345679")).thenThrow(XenditException.class);
        Invoice.getById("012345679");
    }

    @Test
    public void getAll_Success_IfParamsAreValid() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("statuses", "[\"SETTLED\",\"EXPIRED\"]");
        params.put("client_types", "[\"DASHBOARD\",\"API_GATEWAY\"]");
        params.put("last_invoice_id", "5ca186e407f3b83e34176eac");
        params.put("after", "2016-02-24T23:48:36.697Z");
        params.put("before", "2020-02-24T23:48:36.697Z");

        PowerMockito.mockStatic(Invoice.class);
        when(Invoice.getAll(params)).thenReturn(new Invoice[]{});
        Invoice[] invoices = Invoice.getAll(params);
        assertNotNull(invoices);
        assertThat(invoices, instanceOf(Invoice[].class));
    }

    @Test
    public void getAll_Success_IfParamsAreValidButIncomplete() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;
        Map<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("statuses", "[\"SETTLED\",\"EXPIRED\"]");
        params.put("last_invoice_id", "5ca186e407f3b83e34176eac");
        params.put("before", "2020-02-24T23:48:36.697Z");

        PowerMockito.mockStatic(Invoice.class);
        when(Invoice.getAll(params)).thenReturn(new Invoice[]{});
        Invoice[] invoices = Invoice.getAll(params);
        assertNotNull(invoices);
        assertThat(invoices, instanceOf(Invoice[].class));
    }

    @Test
    public void getAll_Success_IfNoGivenParams() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;
        Map<String, Object> params = new HashMap<>();

        PowerMockito.mockStatic(Invoice.class);
        when(Invoice.getAll(params)).thenReturn(new Invoice[]{});
        Invoice[] invoices = Invoice.getAll(params);
        assertNotNull(invoices);
        assertThat(invoices, instanceOf(Invoice[].class));
    }

    @Test(expected = XenditException.class)
    public void getAll_ThrowsException_IfApiKeyIsInvalid() throws XenditException {
        Xendit.apiKey = INVALID_KEY;

        Map<String, Object> params = new HashMap<>();
        PowerMockito.mockStatic(Invoice.class);
        when(Invoice.getAll(params)).thenThrow(XenditException.class);
        Invoice.getAll(params);
    }

    @Test
    public void expire_Success_IfIdIsAvailable() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;

        PowerMockito.mockStatic(Invoice.class);
        when(Invoice.expire("5e0cb0bbf4d38b20d5421b72")).thenReturn(initExpiredInvoice());
        Invoice expiredInvoice = Invoice.expire("5e0cb0bbf4d38b20d5421b72");
        assertNotNull(expiredInvoice);
        assertThat(expiredInvoice, instanceOf(Invoice.class));
        assertEquals(expiredInvoice.getStatus(), "EXPIRED");
    }

    @Test(expected = XenditException.class)
    public void expire_ThrowsException_IfApiKeyIsInvalid() throws XenditException {
        Xendit.apiKey = INVALID_KEY;

        PowerMockito.mockStatic(Invoice.class);
        when(Invoice.expire("my_id")).thenThrow(XenditException.class);
        Invoice.expire("my_id");
    }

    @Test(expected = XenditException.class)
    public void expire_ThrowsException_IfIdIsNotAvailable() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;

        PowerMockito.mockStatic(Invoice.class);
        when(Invoice.expire("123456")).thenThrow(XenditException.class);
        Invoice.expire("123456");
    }
}
