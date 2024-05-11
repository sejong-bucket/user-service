package com.sejong.bucketmanager.domain.common.impl;

import com.sejong.bucketmanager.domain.common.impl.request.LoadSejongInfoDto;
import com.sejong.bucketmanager.domain.common.impl.response.SejongMemberResponseDto;
import com.sejong.bucketmanager.global.aop.meta.ImplService;
import com.sejong.bucketmanager.global.format.exception.auth.InvalidLoginParamException;
import com.sejong.bucketmanager.global.format.exception.auth.NotEnoughWebclientLoginParamException;
import com.sejong.bucketmanager.global.format.exception.webclient.WebClientAuthServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Date;

@RequiredArgsConstructor
@ImplService
public class SejongClient {
    @Value("${sejong.login_server}")
    private String SEJONG_LOGIN_SERVER;
    /*public Mono<SejongMemberResponseDto> callSejongLoginApi(SejongMemberRequestDto requestDto) {
        long now = new Date().getTime();

        Mono<SejongMemberResponseDto> sejongMemberResponseDtoMono = WebClient.builder()
                .baseUrl(SEJONG_LOGIN_SERVER)
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder.queryParam("method", "ClassicSession").build())
                .body(BodyInserters.fromValue(requestDto))
                .retrieve()
//                .onStatus(HttpStatus::is5xxServerError, clientResponse -> handleLoginServerError(clientResponse))
//                .onStatus(HttpStatus::is4xxClientError,clientResponse -> handleWebclientLoginParamError(clientResponse))
                .bodyToMono(SejongMemberResponseDto.class)
                .flatMap(responseDto -> verifyLoginParamError(responseDto));

        return sejongMemberResponseDtoMono;
    }*/

    public SejongMemberResponseDto callSejongLoginApi(LoadSejongInfoDto requestDto) {
        long now = new Date().getTime();

        SejongMemberResponseDto block = WebClient.builder()
                .baseUrl(SEJONG_LOGIN_SERVER)
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder.queryParam("method", "ClassicSession").build())
                .body(BodyInserters.fromValue(requestDto))
                .retrieve()
//                .onStatus(HttpStatus::is5xxServerError, clientResponse -> handleLoginServerError(clientResponse))
//                .onStatus(HttpStatus::is4xxClientError,clientResponse -> handleWebclientLoginParamError(clientResponse))
                .bodyToMono(SejongMemberResponseDto.class)
                .flatMap(responseDto -> verifyLoginParamError(responseDto)).block();
        System.out.println("sejong load time : " + Long.toString(new Date().getTime() - now));

        return block;
    }

    private Mono<SejongMemberResponseDto> verifyLoginParamError(SejongMemberResponseDto responseDto) {
        if ("false".equals(responseDto.getResult().getIs_auth())) {
            return Mono.error(new InvalidLoginParamException());
        }
        return Mono.just(responseDto);
    }

    private Mono<? extends Throwable> handleWebclientLoginParamError(ClientResponse clientResponse) {
        return ClientResponse.create(HttpStatus.BAD_REQUEST)
                .build().createException()
                .flatMap(e -> Mono.error(new NotEnoughWebclientLoginParamException()));
    }
    private Mono<? extends Throwable> handleLoginServerError(ClientResponse clientResponse) {
        return ClientResponse.create(HttpStatus.INTERNAL_SERVER_ERROR)
                .build().createException()
                .flatMap(e -> Mono.error(new WebClientAuthServerException()));
    }
}
