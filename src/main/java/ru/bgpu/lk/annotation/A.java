package ru.bgpu.lk.annotation;

public class A {

    @AppProperty(defaultValue = "localhost")
    public static String HOST;
    @AppProperty
    public static int PORT;


    @Override
    public String toString() {
        return HOST+":"+PORT;
    }
}
