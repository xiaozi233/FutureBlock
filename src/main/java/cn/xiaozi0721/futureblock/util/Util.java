package cn.xiaozi0721.futureblock.util;

import java.util.function.Supplier;

public class Util {
    public static <T> T make(Supplier<T> factory) {
        return (T)factory.get();
    }
}
