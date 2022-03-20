package com.connectionService.repo;

import com.connectionService.model.dao.Node;
import com.connectionService.model.dao.Tp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class NodeRepoTest {
    
    @Autowired
    private NodeRepo nodeRepo;
    
    @Test
    public void getAllNodes(){
        List<Node> nodes = nodeRepo.findAll();
        System.out.println("nodes = " + nodes);
    }

    @Test
    public void getNodeByName(){
        List<Node> node = nodeRepo.findFromAndToNe("LUNITE-A", "LUNITE-B");
        System.out.println("node = " + node);
    }

    @Test
    public void getNodeByNameStartsWith(){
        List<Node> nodes = nodeRepo.findByDisplayNameStartingWith("LUNITE");
        System.out.println("nodes = " + nodes);
    }

    @Test
    public void getNodeByNameEndsWith(){
        List<Node> nodes = nodeRepo.findByDisplayNameEndingWith("A");
        System.out.println("nodes = " + nodes);
    }

    @Test
    public void getNodeByNameContaining(){
        List<Node> nodes = nodeRepo.findByDisplayNameContaining("LU");
        System.out.println("nodes = " + nodes);
    }

}