package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.Balance;

/**
 * Example to get balance without client or having only single apikey
 */
public class ExampleGetBalance {
    public static void main(String[] args) {

        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_vC4O8bNXGmveuozzmTHxfWYmhtLXnXh1BrOlDGJ2ne2I1DUqNxCW6NAkDz7ENA");
        Xendit.Opt.setPrivateKey("MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCwbIaBb5SVXAg0DA9ItLgwq+poHwPdhqd7oSsiyY8sp34Lhe723mEH2lvPptJij4dye8c/6etBqWmfZ1LGVIMYuU/UtWrx0Eld94ieX1MHmHLH1UNqknuoTk9Yxr8cXC8Baj6lS1Dla2oySE9Wi/7yUSINgHgfGwgc2ZR1eWeSt7JqOI3n/PiDRfnnsuACVHa/maQDWeVqVmGekUH/AyzfIaw8y9ZJRRu8uUxt3r+lxvBl7Z6jmNCz11x1EuUr8GO5hPCfu9wm8QN0D+h+9Q1YDkTHhgT6NpUjLQQLbcpU4fK0dL0OEg05wKy6hx013U2Wkd8d22nFokCXzCvFCB35AgMBAAECggEAKa9q1JiFtgjJ02pqDhbNUoq8Vx/E7QnM9vQs2gpf7wAgUx6xSNlgedeJS/YxzaH2MgG3IY3mzVv3Hffu4ffEBgp7J1o6S6qYO0CqoikJ2EiYDkmApWS5HRpruyEytMO/Q4lUBgBWu+52aC0Xue5YLe8nqaMKPudHMnHilyM5arviJAFSfQ4170xdm2ddNWV5hgzY92IIVQ1p0/imDoIyM1seXRRrIy3QTJDl8evMKiphNTb3k2cd2NTa/IJU0/YmP4I5cAsbmjyQh1Nje/Iq3TsnZeK0z/RuhIenGQjpQVIY7DDYVKM0sFqAObnFWNTWdQZqZvI4m/dup2bAUKqAUQKBgQDipQIL+4oGUaI48MKCexri81j0AAtM/91EjsYpZU8QPcFURoFsbkSAuXOCgH30DPUROQWm2WiWyYdr7PPZPNZMRa4Vzc5xdt2XeShOKeFP+gRe38Cq7VwmZ+07mpOZKnG3jylrEH1tk67Y9vWdc6HyJnGqBFBpBa1O0PHcwnrG1QKBgQDHRlI5RnRzXhbvRmBTy3VPKhD9+YDkDIqwbDdye6TbhIn+H4q8hdRkBUlI/wWrNgxB0BNJGu+2t5i7ycgZPM2kwhxwex/tohIFPBedhHsApYG6o98p1ONRqykbZ3p7lu7UfjD52YxV7DZah7Sg7cIF62hyHSc47EMhkustFELUlQKBgFoDBqkToWGZEkBi/h0sHyV7WByv180g4RPVUEYoghoFLL73sXcK5BHrlUCDH4LJkprgdqopNiyMpUfrt3zTeJiaI+Vs80yE1MTPzX4di+pjdVDotHAuDKcEJ2fAoFX4UWmBZgWrauUV/h8VFb8OqYauVf4b8IOFSas/uI7gfjpxAoGAWoBxb1m0IYEl4V2Q+f15NY7hULcz9l5LznwXYoMNCDVVhnhExgHxx8su0Vwg8vUE9A7P82+sVstjLkYh/nRC4QTXba49fesRTBdSklNMmaqRFHbAL3tkEf8uGCgS1HZj2VXGYHKQ7aTtuofPXRcd+xpCwTDEU3HZuzHCZSLCh1ECgYBS5M0cfqcu+QYqJncDmzQve914y/vSPZp+UGEPUtw13a/NOMOm26fGlE5Utqh8sf4tnxh4TDMUC1HvOlAhapRAGmCG0LFAZJRQXgKAfv8VMyJdQr9kbzGFzUppEb1pA9zdikwq8h2ETncPYH+Di0gbWcBiAiIpcSNJzGHDieXBRg==");
        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_...";

        try {
            Balance balance = Balance.get();
            System.out.println(balance.getBalance());
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
