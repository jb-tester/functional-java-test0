package com.mytests.spring.webflux.functionaljavatest0;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;


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
        return route(GET("/case_first"), req -> ok().body(fromValue("aaaa")))   // https://youtrack.jetbrains.com/issue/IDEA-251039
                .andNest(path("/case_second"),
                        route(GET("/c_route"),
                                req -> ok().body(fromValue("cccc")))
                                .andRoute(GET("/d_route"),     // https://youtrack.jetbrains.com/issue/IDEA-251043
                                        req -> ok().body(fromValue("dddd"))))
                ;
    }

    @Bean
    public RouterFunction<ServerResponse> nestedPath() {
        return route()
                .path("/route3", () ->
                        route()
                                .GET("/nested_path",   // https://youtrack.jetbrains.com/issue/IDEA-255854
                                        req -> ok().body(fromValue("nested .path(..) passed"))).build()).build()

                ;
    }

    @Bean
    public RouterFunction<ServerResponse> routeComposedPredicate() {
        return route(method(HttpMethod.GET).and(path("/composedPredicate")),     // https://youtrack.jetbrains.com/issue/IDEA-234927
                this::processComposedPredicates);
    }

    private Mono<ServerResponse> processComposedPredicates(ServerRequest serverRequest) {
        return ServerResponse.ok().body(fromValue("composed predicate"));
    }


}
