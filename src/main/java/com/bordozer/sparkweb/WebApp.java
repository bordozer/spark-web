package com.bordozer.sparkweb;

import com.google.common.io.Resources;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static spark.Spark.afterAfter;
import static spark.Spark.get;
import static spark.Spark.port;

@Slf4j
public class WebApp {

    public static void main(String[] args) {
        final var properties = loadConfig();

        port(Integer.parseInt(properties.getProperty("app.port")));
        afterAfter(new LoggingFilter());
        get("/", "application/json", (request, response) -> welcome());
    }

    private static String welcome() {
        return "Ok";
    }

    @SneakyThrows
    private static Properties loadConfig() {
        final Properties appProps = new Properties();
        appProps.load(new ByteArrayInputStream(readResource("config.properties").getBytes()));
        return appProps;
    }

    @SneakyThrows
    public static String readResource(final String name) {
        final URL url = Resources.getResource(name);
        return Resources.toString(url, StandardCharsets.UTF_8);
    }
}
