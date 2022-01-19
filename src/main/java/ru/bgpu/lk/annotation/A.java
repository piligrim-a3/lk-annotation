package ru.bgpu.lk.annotation;

public class A {

    public static String HOST;
    public static int PORT;


    @Override
    public String toString() {
        return HOST+":"+PORT;
    }
}
