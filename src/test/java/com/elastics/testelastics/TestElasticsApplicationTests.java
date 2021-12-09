package com.elastics.testelastics;

import com.elastics.testelastics.business.InitDataService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class TestElasticsApplicationTests {

    @Autowired
    private InitDataService service;

    @Test
    void testInitData() {
        // TEST DATA
        Assertions.assertDoesNotThrow(() -> service.initUsers());
        Assertions.assertDoesNotThrow(() -> service.initItems());
    }

}
