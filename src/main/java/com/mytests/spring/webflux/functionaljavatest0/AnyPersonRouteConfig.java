package com.mytests.spring.webflux.functionaljavatest0;

import com.mytests.spring.webflux.functionaljavatest0.data.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static com.mytests.spring.webflux.functionaljavatest0.PersonRoutersConfig.BASE_PATH;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 11/12/2019.
 * Project: functional-java-test0
 * *******************************
 */
@Configuration
public class AnyPersonRouteConfig {
    @Bean
    public RouterFunction<ServerResponse> routeAnySingle(){
        return route(GET(BASE_PATH +"/persons/new/{any_name}/{any_id}/{any_age}"),this::getAnyPerson);
    }

    public Mono<ServerResponse> getAnyPerson(ServerRequest req) {

        int[] aaa = {1,2,3};
        Integer[][]bbb = new Integer[0][0];
        List list = new ArrayList();

        return ServerResponse.ok().body(Mono.just(new Person(
                        req.pathVariable("any_id"),
                        req.pathVariable("any_name"),
                        Integer.parseInt(req.pathVariable("any_age")))),
                Person.class);

    }
}
