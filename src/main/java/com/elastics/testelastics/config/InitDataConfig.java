package com.elastics.testelastics.config;

import com.elastics.testelastics.business.InitDataService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InitDataConfig implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(InitDataConfig.class);

    private final InitDataService service;

    @Override
    public void run(String... args) {
        //service.initUsers();
        //service.initItems();
        //service.initReservations();
        service.initRequiredDirectories();
        LOG.info("DEV_LOGS: Data Initialized Successfully");
    }

}
