package com.connectionHandlerService.request;
import com.connectionHandlerService.model.ConnectionParameters;
import com.connectionHandlerService.model.DlConn.DLInput;
import com.connectionHandlerService.model.InfraConn.InfraInput;
import com.connectionHandlerService.model.ServiceConn.ServiceInput;
import com.connectionHandlerService.model.db_dto.ServiceOrder;
import com.connectionHandlerService.model.dto.InfoForValidation;
import com.connectionHandlerService.model.dto.Order;
import com.connectionHandlerService.model.dto.OrderStatus;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Component
@Data
public class ReqToConnService {

    private final  String BASE_URL="http://localhost:8084";
    private String token;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public InfoForValidation getRequiredInfoForValidation(ConnectionParameters connParams){
        return webClientBuilder.baseUrl(BASE_URL).build()
                .get()
                .uri(builder ->
                        builder.path("/api/v1/connService/getValidationInfo")
                                .queryParam("connGroupId", connParams.getConnGroupId())
                                .queryParam("fromNE", connParams.getFromNE())
                                .queryParam("toNE", connParams.getToNE())
                                .queryParam("fromPort", connParams.getFromPort())
                                .queryParam("toPort", connParams.getToPort())
                                .queryParam("isService", connParams.getConnectionRate().equals("VC-12"))
                                .build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(InfoForValidation.class)
                .block();
    }

    // If we want to send multiple custom class to methods then use Global class "Object" and use instanceOf for specific custom class
    public ServiceOrder createServiceOrder(Object input){

        Order order=null;
        OrderStatus serviceOrder;

        if(input instanceof DLInput) {
            order = Order.builder()
                    .connectionParameters(((DLInput) input).getConnectionParameters())
                    .orderParameters(((DLInput) input).getOrderParameters())
                    .build();
        } else if(input instanceof InfraInput) {
            order = Order.builder()
                    .connectionParameters(((InfraInput) input).getConnectionParameters())
                    .orderParameters(((InfraInput) input).getOrderParameters())
                    .routingParameters(((InfraInput) input).getRoutingParameters())
                    .infraParams(((InfraInput) input).getInfraParams())
                    .build();
        } else if(input instanceof ServiceInput){
            order = Order.builder()
                    .connectionParameters(((ServiceInput) input).getConnectionParameters())
                    .orderParameters(((ServiceInput) input).getOrderParameters())
                    .routingParameters(((ServiceInput) input).getRoutingParameters())
                    .serviceParams(((ServiceInput) input).getServiceParams())
                    .build();
        }

        serviceOrder = webClientBuilder.baseUrl(BASE_URL).build()
                    .post()
                    .uri(builder ->
                            builder.path("/api/v1/connService/order/initiate")
                                    .build())
                    .header(HttpHeaders.AUTHORIZATION,  "Bearer " + token)
                    .body(Mono.just(order), Order.class)
                    .retrieve()
                    .bodyToMono(OrderStatus.class)
                    .block();

        System.out.println("serviceOrder = " + serviceOrder);

        return null;
    }



}
