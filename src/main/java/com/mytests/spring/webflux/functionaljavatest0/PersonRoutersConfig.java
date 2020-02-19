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

    public static final String BASE_PATH = "/routing";

    @Bean
    public RouterFunction<ServerResponse> routeSingle(PersonsHandler handler){
        return route(GET("/routing/persons/single/{person_id}"),handler::getOnePerson);
    }
    @Bean
    public RouterFunction<ServerResponse> routeMulti(PersonsHandler handler){
        return route(GET("/"+"routing" + "/persons/all"),handler::getAllPersons)
                .andRoute(GET("/routing" + "/" +"persons/ny_name/{person_name}"),handler::getPersonsByName);
    }

    @Bean
    public RouterFunction<ServerResponse> routePersonUsingReqParam(PersonsHandler handler){
        return route(GET("/routing/persons/single1"),handler::getOnePersonByReqParam);

    }

}
