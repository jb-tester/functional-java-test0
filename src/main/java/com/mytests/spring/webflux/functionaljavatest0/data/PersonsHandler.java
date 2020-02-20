package com.mytests.spring.webflux.functionaljavatest0.data;

import io.netty.handler.codec.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 11/1/2019.
 * Project: functional-java-test0
 * *******************************
 */
@Component
public class PersonsHandler {
    @Autowired
    private PersonsRepo personsRepo;
    public Mono<ServerResponse> getOnePerson(ServerRequest req) {
        return personsRepo.getPersonById(req.pathVariable("person_id"))
                .flatMap(person -> ServerResponse.ok().body(Mono.just(person), Person.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
    public Mono<ServerResponse> getPersonsByName(ServerRequest req){
        return ServerResponse.ok().body(personsRepo.getPersonsByName(req.pathVariable("person_name")), Person.class);    }

    public Mono<ServerResponse> getAllPersons(ServerRequest req){
        return  ServerResponse.ok().body(personsRepo.getAllpersons(), Person.class);
    }

    public Mono<ServerResponse> getPersonsByAge(ServerRequest req){
        return ServerResponse.ok().body(personsRepo.getPersonsByAgeLimits(Integer.parseInt(req.pathVariable("min")),
                Integer.parseInt(req.pathVariable("max"))),
                Person.class );
    }
    public Mono<ServerResponse> getOnePersonByReqParam(ServerRequest req){

        return req.queryParam("idparam")
                .map(id -> personsRepo.getPersonById(id))
                .orElseGet(() -> personsRepo.getPersonById("1"))
                .flatMap(person -> ServerResponse.ok().body(Mono.just(person), Person.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
