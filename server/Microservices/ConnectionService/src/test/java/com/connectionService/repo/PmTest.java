package com.connectionService.repo;

import com.connectionService.model.dao.NetworkConnection;
import com.connectionService.model.dao.Pm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PmRepoTest {

    @Autowired
    private PmRepo pmRepo;


    @Test
    public void savePm(){

        NetworkConnection networkConnection = NetworkConnection.builder()
                .connectionAlias("LUNITE-C")
                .connectionName("LUNITE-C")
                .connectionRate("1165")
                .connectionShape("Simple (bi)")
                .connectionState("1")
                .connNameFormat("Free format")
                .customerName("Null")
                .serviceType("unavailable")
                .groupType(null)
                .preplan(null)
                .protectionType("unprotected")
                .routeSelectionMode(null)
                .routingEffort(null)
                .terminationEndPoints(null)
                .build();
        Pm pm = Pm.builder()
                .networkConnection(networkConnection)
                .build();
        pmRepo.save(pm);
    }
}