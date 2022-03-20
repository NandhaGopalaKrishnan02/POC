package com.connectionService.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.List;


@SpringBootTest
class NetworkConnectionRepoTest {

    @Autowired
    private NetworkConnectionRepo networkConnectionRepo;

    @Test
    public void getConnectionNames(){
        List<String> connList = networkConnectionRepo.getAllConnectionNames();
        System.out.println("connList = " + connList);
    }


    @Test
    public void joinScNetwork(){
        List<Object[]> networkConnection = networkConnectionRepo.getConnInfoForGL(2213);

        System.out.println(networkConnection);
        for(Object[] n:networkConnection){
            String connectionName =(String) n[0];
            Integer order_number = (Integer) n[1];
            BigInteger pmId = (BigInteger) n[2];
            Integer connection_rate = (Integer) n[3];
            System.out.println("order_number = " + order_number);
            System.out.println("connectio_name = " + connectionName);
            System.out.println("pmId = " + pmId);
            System.out.println("connection_rate = " + connection_rate);
        }

//        System.out.println((String) networkConnection.get(0)[0]);

    }



}