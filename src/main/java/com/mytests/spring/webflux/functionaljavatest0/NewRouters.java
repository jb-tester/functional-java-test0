package com.mytests.spring.webflux.functionaljavatest0;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * *
 * <p>Created by irina on 18.09.2020.</p>
 * <p>Project: spring-webflux-security-test0</p>
 * *
 */
@Configuration
public class NewRouters {

    @Bean
    public RouterFunction<ServerResponse> routeNestedSimple() {
        return RouterFunctions.nest(path("/zero"),
                route(GET("/a_route"),
                        req -> ok().body(fromValue("aaa")))
                        .andRoute(GET("/b_route"),
                                req -> ok().body(fromValue("bbb")))
        );
    }


    @Bean
    public RouterFunction<ServerResponse> routeNested2() {
        return route(GET("/case_first"), req -> ok().body(fromValue("aaaa")))
                .andNest(path("/case_second"),
                        route(GET("/c_route"),
                                req -> ok().body(fromValue("cccc")))
                                .andRoute(GET("/d_route"),
                                        req -> ok().body(fromValue("dddd"))))
                ;
    }
}
