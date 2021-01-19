package com.bordozer.sparkweb;

import lombok.extern.slf4j.Slf4j;

import static spark.Spark.afterAfter;
import static spark.Spark.get;
import static spark.Spark.port;

@Slf4j
public class WebApp {

    public static void main(String[] args) {
        port(8800);
        afterAfter(new LoggingFilter());
        get("/", "application/json", (request, response) -> welcome());
    }

    private static String welcome() {
        return "Ok";
    }
}
