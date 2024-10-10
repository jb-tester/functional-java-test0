package com.mytests.spring.webflux.functionaljavatest0;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AnotherRouter {

    @Bean
    public AnotherHandler anotherHandler() {
        return new AnotherHandler();
    }

    @Bean
    public RouterFunction<ServerResponse> anotherRouter(AnotherHandler handler) {
        return route().GET("/another/get1", handler::get1)
                .GET("/another/get2", handler::get2)
                .build();
    }


    public static class AnotherHandler {

        public Mono<ServerResponse> get1(ServerRequest request) {
            return ServerResponse.ok().body(Mono.just("Response from AnotherHandler - 1"), String.class);
        }

        public Mono<ServerResponse> get2(ServerRequest request) {
            return ServerResponse.ok().body(Mono.just("Response from AnotherHandler - 2"), String.class);
        }

    }
}
