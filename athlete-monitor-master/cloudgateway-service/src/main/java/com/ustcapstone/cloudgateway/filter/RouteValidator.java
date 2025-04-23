package com.ustcapstone.cloudgateway.filter;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.http.server.reactive.ServerHttpRequest;


import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/token",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
            
            
}


