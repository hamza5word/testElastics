package com.elastics.testelastics.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface InitDataService {

    Logger LOG = LoggerFactory.getLogger(InitDataService.class);

    void initUsers();
    void initItems();
    void initReservations();
    void initRequiredDirectories();

}
