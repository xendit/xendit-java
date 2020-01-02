package com.xendit.model;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

public class InvoiceTest {
    private static final String SAMPLE_KEY = "xnd_development_O46JfOtygef9kMNsK+ZPGT+ZZ9b3ooF4w3Dn+R1k+2fT/7GlCAN3jg==:";
    private static final String INVALID_KEY = "xnd_development_...";

    @Test
    public void create_Success_IfApiKeyIsValid() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;
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
        Invoice invoice = Invoice.create(params);
        assertNotNull(invoice);
        assertThat(invoice, instanceOf(Invoice.class));
    }

    @Test(expected = XenditException.class)
    public void create_ThrowsException_IfApiKeyIsInvalid() throws XenditException {
        Xendit.apiKey = INVALID_KEY;
        Invoice.create("test_id", 100000, "test@email.com", "Testing");
    }

    @Test(expected = XenditException.class)
    public void create_ThrowsException_IfAmountIsLessThanMinimum() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;
        Invoice.create("test_id", 1000, "test@email.com", "Testing");
    }

    @Test(expected = XenditException.class)
    public void create_ThrowsException_IfAmountIsGreaterThanMaximum() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;
        Invoice.create("test_id", 1000000001, "test@email.com", "Testing");
    }

    @Test(expected = XenditException.class)
    public void create_ThrowsException_IfInvalidParams() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;
        Map<String, Object> params = new HashMap<>();
        params.put("externals_id", "test_id");
        params.put("amount", 100000);
        params.put("description", "Testing");
        Invoice.create(params);
    }

    @Test
    public void getById_Success_IfIdIsAvailable() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;
        Invoice invoice = Invoice.getById("5e0cb0bbf4d38b20d5421b72");
        assertNotNull(invoice);
        assertThat(invoice, instanceOf(Invoice.class));
    }

    @Test(expected = XenditException.class)
    public void getById_ThrowsException_IfApiKeyIsInvalid() throws XenditException {
        Xendit.apiKey = INVALID_KEY;
        Invoice.getById("5e0cb0bbf4d38b20d5421b72");
    }

    @Test(expected = XenditException.class)
    public void getById_ThrowsException_IfIdIsNotAvailable() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;
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
        Invoice[] invoices = Invoice.getAll(params);
        assertNotNull(invoices);
        assertThat(invoices, instanceOf(Invoice[].class));
    }

    @Test
    public void getAll_Success_IfNoGivenParams() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;
        Map<String, Object> params = new HashMap<>();
        Invoice[] invoices = Invoice.getAll(params);
        assertNotNull(invoices);
        assertThat(invoices, instanceOf(Invoice[].class));
    }

    @Test(expected = XenditException.class)
    public void getAll_ThrowsException_IfApiKeyIsInvalid() throws XenditException {
        Xendit.apiKey = INVALID_KEY;
        Map<String, Object> params = new HashMap<>();
        Invoice.getAll(params);
    }

    @Test
    public void expire_Success_IfIdIsAvailable() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;

        Invoice invoice = Invoice.create("test_id", 100000, "test@email.com", "Testing");
        assertNotNull(invoice);
        assertThat(invoice, instanceOf(Invoice.class));
        assertEquals(invoice.getStatus(), "PENDING");

        Invoice expiredInvoice = Invoice.expire(invoice.getId());
        assertNotNull(expiredInvoice);
        assertThat(expiredInvoice, instanceOf(Invoice.class));
        assertEquals(expiredInvoice.getStatus(), "EXPIRED");
    }

    @Test(expected = XenditException.class)
    public void expire_ThrowsException_IfApiKeyIsInvalid() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;

        Invoice invoice = Invoice.create("test_id", 100000, "test@email.com", "Testing");
        assertNotNull(invoice);
        assertThat(invoice, instanceOf(Invoice.class));
        assertEquals(invoice.getStatus(), "PENDING");

        Xendit.apiKey = INVALID_KEY;

        Invoice.expire(invoice.getId());
    }

    @Test(expected = XenditException.class)
    public void expire_ThrowsException_IfIdIsNotAvailable() throws XenditException {
        Xendit.apiKey = SAMPLE_KEY;
        Invoice.expire("123456");
    }
}
