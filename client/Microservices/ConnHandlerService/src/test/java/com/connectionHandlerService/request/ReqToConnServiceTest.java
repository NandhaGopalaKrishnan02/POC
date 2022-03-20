package com.connectionHandlerService.request;

import com.connectionHandlerService.model.dto.InfoForValidation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
class ReqToConnServiceTest {

    private final static String BASE_URL="http://localhost:8084/api/v1/connService/";

    private final String requestedPath = BASE_URL +"/getValidationInfo";
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Test
    public void getRequiredInfoForValidation(){
        webClientBuilder.baseUrl(BASE_URL).build()
                .get()
                .uri(builder ->
                        builder.path("/api/v1/connService/getValidationInfo")
                                .queryParam("connGroupId", "1243")
                                .queryParam("fromNE", "LUNITE-A")
                                .queryParam("toNE", "LUNITE-B")
                                .queryParam("fromPort", "1-1-#-#-1-1-1-1")
                                .queryParam("toPort", "1-1-#-#-4-1-1-1")
                                .queryParam("isService", "VC-12")
                                .build())
//                .header(HttpHeaders.AUTHORIZATION, "Bearer " + )
                .retrieve()
                .bodyToMono(InfoForValidation.class)
                .block();
    }

    @Test
    public void createService(){

    }


}