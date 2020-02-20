package com.mytests.spring.webflux.functionaljavatest0;

import com.mytests.spring.webflux.functionaljavatest0.data.Person;
import com.mytests.spring.webflux.functionaljavatest0.data.PersonsHandler;
import com.mytests.spring.webflux.functionaljavatest0.data.PersonsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 2/19/2020.
 * Project: functional-java-test0
 * *******************************
 */
@Configuration
public class PersonNestedRouterConfig {
    @Autowired
    private PersonsRepo personsRepo;
    @Bean
    public RouterFunction<ServerResponse> nestedRouterFunction(PersonsHandler handler) {

        return  RouterFunctions
                .nest(path("/route_nested"),
                        route(GET("/all"), handler::getAllPersons)
                                .andRoute(GET("/by_age/{min}_{max}"), handler::getPersonsByAge)
                                .andRoute(GET("/by_name/{personname}"), this::getPersonsByName)
                                .andRoute(GET("/by_id/{person_id}"), req -> getById(req,req.pathVariable("person_id")))
                                        );

    }

    private Mono<ServerResponse> getPersonsByName(ServerRequest serverRequest) {
        return ServerResponse.ok().body(personsRepo.getPersonsByName(serverRequest.pathVariable("personname")), Person.class);
    }

    private Mono<ServerResponse> getById(ServerRequest serverRequest, String id) {
        return personsRepo.getPersonById(id)
                .flatMap(person -> ServerResponse.ok().body(Mono.just(person), Person.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }


}
