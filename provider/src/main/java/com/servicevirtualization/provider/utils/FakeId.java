package com.servicevirtualization.provider.utils;

import java.util.UUID;

public class FakeId {
    public static String getFakeUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
