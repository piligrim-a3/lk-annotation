package ru.bgpu.lk.annotation;

public class B {

    @AppProperty(defaultValue = "10.1.1.1")
    public static String HOST;
    @AppProperty
    public static int PORT;
    @AppProperty
    public static String APPLICATION_NAME;
}
