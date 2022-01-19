package ru.bgpu.lk.annotation;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.reflections.scanners.Scanners.FieldsAnnotated;

public class PropertyWorker {

    private static Logger logger = LoggerFactory.getLogger(PropertyWorker.class);

    public void initAnnotation(String fileName) {
        logger.debug("Init property worker");
        Properties properties = new Properties();
        File fileProperties = new File(fileName);
        if(fileProperties.isFile()) {
            try {
                properties.load(new FileInputStream(fileProperties));
            } catch (IOException e) {
                logger.error("Error load property file "+fileName, e);
            }
        } else {
            logger.warn("File property not found");
        }
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .forPackage("ru.bgpu.lk.annotation")
                        .setScanners(FieldsAnnotated));
        reflections.getFieldsAnnotatedWith(AppProperty.class).forEach(field -> {
            logger.debug("find: {}",field.getName());
            String value = properties.getProperty(
                    field.getName().toLowerCase(),
                    field.getAnnotation(AppProperty.class).defaultValue()
            );
            try {
                if(field.getType().equals(String.class)) {
                    field.set(null, value);
                } else if(field.getType().equals(int.class)) {
                    if(value.isEmpty()) {
                        value = "0";
                    }
                    field.setInt(null, Integer.parseInt(value));
                }
                logger.debug(
                        "set: {}.{} = {}",
                        field.getDeclaringClass().getName(),
                        field.getName(),
                        value
                );
            } catch (IllegalAccessException e) {
                logger.error(
                        "Error set value "+value+" to Field "+field.getName(),e
                );
            }

        });
    }
}
