package com.connectionService.repo;

import com.connectionService.model.dao.NcComponent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class NcComponentRepoTest {

    @Autowired
    NcComponentRepo ncComponentRepo;

}