package com.bordozer.sparkweb.filter;

import lombok.extern.slf4j.Slf4j;
import spark.Filter;
import spark.Request;
import spark.Response;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class LoggingFilter implements Filter {

    private static final String TEMPLATE_WITH_NO_BODY = "\nRequest {} {} {} HEADERS:[{}] \nResponse {} HEADERS:[{}]";

    @Override
    public void handle(final Request request, final Response response) {
        final String requestHeaderString = buildHeadersString(request.headers(), request::headers);
        final String responseHeaderString = buildHeadersString(response.raw().getHeaderNames(), h -> response.raw().getHeader(h));
        final Object[] params = new Object[]{
                request.requestMethod(),
                request.pathInfo(),
                request.protocol(),
                requestHeaderString,
                response.raw().getStatus(),
                responseHeaderString,
        };
        log.info(TEMPLATE_WITH_NO_BODY, params);
    }

    private String buildHeadersString(final Collection<String> headers, final Function<String, String> getHeader) {
        return headers.stream()
                .map(h -> h + ":" + getHeader.apply(h))
                .collect(Collectors.joining(", "));
    }
}
