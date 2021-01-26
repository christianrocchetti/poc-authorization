package com.poc.authorization.stub;

import java.util.ArrayList;
import java.util.List;

// T\ODO Replace this stub (if you want to go deeper)
public class Base64Stub {

    // Base64 is composed as follows:
    // username:password
    // when added in http header it becomes 'Basic <hashed value of username:password>'
    public static List<String> get() {
        List<String> stub = new ArrayList<>();
        stub.add("Y2hyaXN0aWFuY2hyaXN0aWFu");   // christian:christian
        stub.add("bG9yZW56bzpsb3Jlbnpv");       // lorenzo:lorenzo
        stub.add("cm9iZXJ0bzpyb2JlcnRv");       // roberto:roberto
        return stub;
    }
}
