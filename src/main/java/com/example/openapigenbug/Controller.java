package com.example.openapigenbug;

import com.example.openapigenbug.generated.server.api.FooApi;
import com.example.openapigenbug.generated.server.model.CreateFoo200ResponseDto;
import com.example.openapigenbug.generated.server.model.CreateFooRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class Controller implements FooApi {
    @Override
    public Mono<ResponseEntity<CreateFoo200ResponseDto>> createFoo(Mono<CreateFooRequestDto> fooRequestDto, ServerWebExchange exchange) {
        return fooRequestDto.flatMap(fooRequest -> {
            System.out.println("bar: " + fooRequest.getBar());
            return Mono.just(ResponseEntity.ok(new CreateFoo200ResponseDto().bar(fooRequest.getBar())));
        });
    }
}
