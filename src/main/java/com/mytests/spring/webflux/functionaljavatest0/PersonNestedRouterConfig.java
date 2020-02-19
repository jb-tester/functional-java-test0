package com.mytests.spring.webflux.functionaljavatest0;

import com.mytests.spring.webflux.functionaljavatest0.data.PersonsHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.ServerResponse.noContent;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 2/19/2020.
 * Project: functional-java-test0
 * *******************************
 */
@Configuration
public class PersonNestedRouterConfig {

    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(PersonsHandler handler) {

        return  RouterFunctions
                .nest(path("/route_nested"),
                        route(GET("/all"), handler::getAllPersons)
                                .andRoute(GET("/by_age/{min}{max}"), handler::getPersonsByAge)
                                .andRoute(GET("/print/{id}"),
                                        req -> noContent().build(printId(req.pathVariable("id")))));

    }

    private BiFunction<ServerWebExchange, ServerResponse.Context, Mono<Void>> printId(String id) {
        System.out.println(id);
        return null;
    }
}
