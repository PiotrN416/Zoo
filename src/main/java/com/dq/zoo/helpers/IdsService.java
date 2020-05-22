package com.dq.zoo.helpers;

public class IdsService {

    private static long current=0;

    public static long getNext() {
        return current++;
    }
}
