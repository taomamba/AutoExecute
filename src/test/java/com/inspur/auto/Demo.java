package com.inspur.auto;

import sun.misc.BASE64Decoder;

import java.io.IOException;

public class Demo {
    public static class MyClassLoder extends ClassLoader{ //继承classloader
        public Class get(byte[] bytes){
            return super.defineClass(bytes, 0, bytes.length);
        }
    }

    public static void main(String[] args) {
        String classStr = "5ryx5aOVICAgNCAkDQogCSAUDQogFSAWCCAXDQogFSAYByAZDQogBSAaCCAbByAcByAdASAGJmx0O2luaXQmZ3Q7ASADKClWASAEQ29kZQEgD0xpbmVOdW1iZXJUYWJsZQEgCHRvU3RyaW5nASAUKClMamF2YS9sYW5nL1N0cmluZzsBIA0KU3RhY2tNYXBUYWJsZQcgGQEgDQpTb3VyY2VGaWxlASAMUGF5bG9hZC5qYXZhDCANCiALByAeDCAfICABIAtub3RlcGFkLmV4ZQwgISAiASATamF2YS9pby9JT0V4Y2VwdGlvbgwgIyALASACT0sBIBdjb20vaW5zcHVyL2F1dG8vUGF5bG9hZAEgEGphdmEvbGFuZy9PYmplY3QBIBFqYXZhL2xhbmcvUnVudGltZQEgDQpnZXRSdW50aW1lASAVKClMamF2YS9sYW5nL1J1bnRpbWU7ASAEZXhlYwEgJiMzOTsoTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvUHJvY2VzczsBIA9wcmludFN0YWNrVHJhY2UgISAIIAkgICAgIAIgASANCiALIAEgDCAgIB0gASABICAgBSo/IAE/ICAgASANCiAgIAYgASAgIAUgASAOIA8gASAMICAgUSACIAIgICAUPyACEgM/IARXPyAITCs/IAYSBz8gASAgIAkgDCAFIAIgDQogICAWIAUgICALIAkgDyAMIAwgDQogDiARIBAgECAgIAcgAkwHIBEEIAEgEiAgIAIgEw==";
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            Class result = new MyClassLoder().get(base64Decoder.decodeBuffer(classStr));
            System.out.println(result.newInstance().toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
