package com.mytests.spring.webflux.functionaljavatest0;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 10/31/2019.
 * Project: functional-java-test0
 * *******************************
 */
@Configuration
public class SimpleRouter {

    @Bean
    public RouterFunction<ServerResponse> simple() {
        return route(GET("/test0/functional/simple/{pathvar}"),
                req -> ok().body(fromValue("simple "+req.pathVariable("pathvar")))) ;
    }
}
