package com.mytests.spring.webflux.functionaljavatest0;

import com.mytests.spring.webflux.functionaljavatest0.data.Person;
import com.mytests.spring.webflux.functionaljavatest0.data.PersonsHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 11/1/2019.
 * Project: functional-java-test0
 * *******************************
 */
@Configuration
public class PersonRoutersConfig {

    @Bean
    public RouterFunction<ServerResponse> routeSingle(PersonsHandler handler){
        return route(GET("/routing/persons/single/{person_id}"),handler::getOnePerson);
    }
    @Bean
    public RouterFunction<ServerResponse> routeMulti(PersonsHandler handler){
        return route(GET("/routing/persons/all"),handler::getAllPersons)
                .andRoute(GET("/routing/persons/ny_name/{person_name}"),handler::getPersonsByName);
    }
    @Bean
    public RouterFunction<ServerResponse> routeAnySingle(){
        return route(GET("/routing/persons/new/{any_name}/{any_id}/{any_age}"),this::getAnyPerson);
    }

    public Mono<ServerResponse> getAnyPerson(ServerRequest req) {
        return ServerResponse.ok().body(Mono.just(new Person(
                req.pathVariable("any_id"),
                req.pathVariable("any_name"),
                Integer.parseInt(req.pathVariable("any_age")))),
                Person.class);

    }

}
