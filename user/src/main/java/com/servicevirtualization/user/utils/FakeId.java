package com.servicevirtualization.user.utils;

import java.util.UUID;

public class FakeId {
    public static String getFakeUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
