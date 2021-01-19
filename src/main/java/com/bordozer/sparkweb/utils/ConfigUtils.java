package com.bordozer.sparkweb.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConfigUtils {

    @SneakyThrows
    public static Properties loadConfig() {
        final Properties appProps = new Properties();
        appProps.load(new ByteArrayInputStream(CommonUtils.readResource("config.properties").getBytes()));
        return appProps;
    }
}
