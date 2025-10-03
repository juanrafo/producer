package com.producer.demo.util;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    public static final String REDIS_CAR_0 = "{\"id\":35,\"brand\":\"nissan\",\"model\":\"tercel\",\"year\":100}";
    public static final String REDIS_CAR_1 = "{\"id\":36,\"brand\":\"nissan\",\"model\":\"tercel\",\"year\":100}";
    public static final String REDIS_CAR_2 = "{\"id\":37,\"brand\":\"nissan\",\"model\":\"tercel\",\"year\":100}";
    public static final ArrayList<String> REDIS_CAR_LIST = new ArrayList<>(List.of(REDIS_CAR_0, REDIS_CAR_1, REDIS_CAR_2));
}
