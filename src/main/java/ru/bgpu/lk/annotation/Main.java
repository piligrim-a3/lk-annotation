package ru.bgpu.lk.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    static {
        new PropertyWorker().initAnnotation();
    }

    public static void main(String[] args) {
        logger.info("Start program.");
    }
}
