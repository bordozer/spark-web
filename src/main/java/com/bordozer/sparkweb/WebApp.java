package com.bordozer.sparkweb;

import lombok.extern.slf4j.Slf4j;

import static spark.Spark.afterAfter;
import static spark.Spark.get;

@Slf4j
public class WebApp {

    public static void main(String[] args) {
        afterAfter(new LoggingFilter());
        get("/", (request, response) -> welcome());
    }

    private static String welcome() {
        return "Ok";
    }
}
