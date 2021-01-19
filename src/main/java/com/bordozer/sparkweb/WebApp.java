package com.bordozer.sparkweb;

import com.bordozer.sparkweb.filter.LoggingFilter;
import com.bordozer.sparkweb.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

import static spark.Spark.afterAfter;
import static spark.Spark.get;
import static spark.Spark.port;

@Slf4j
public class WebApp {

    public static void main(String[] args) {
        final var properties = ConfigUtils.loadConfig();
        port(Integer.parseInt(properties.getProperty("app.port")));

        afterAfter(new LoggingFilter());

        get("/", "application/json", (request, response) -> welcome());
    }

    private static String welcome() {
        return "Ok";
    }
}
