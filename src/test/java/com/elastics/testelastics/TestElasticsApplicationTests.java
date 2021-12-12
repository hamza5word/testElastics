package com.elastics.testelastics;

import com.elastics.testelastics.business.IService;
import com.elastics.testelastics.business.InitDataService;
import com.elastics.testelastics.models.ReservationForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class TestElasticsApplicationTests {

    @Autowired
    private InitDataService service;
    @Autowired
    private IService businessService;

    @Test
    void testInitData() {
        // TEST DATA
        Assertions.assertDoesNotThrow(() -> service.initUsers());
        Assertions.assertDoesNotThrow(() -> service.initItems());
        Assertions.assertDoesNotThrow(() -> service.initReservations());
    }

    @Test
    void testGetItemImage() {
        Assertions.assertNotNull(businessService.getItemImage(1L));
        Assertions.assertNull(businessService.getItemImage(5L));
    }

    @Test
    void testReserveItem() {
        ReservationForm reservationFormTest = new ReservationForm();
        reservationFormTest.setUserId(2L);
        reservationFormTest.setItemId(4L);
        Assertions.assertNotNull(businessService.reserveItem(reservationFormTest));
        Assertions.assertNull(businessService.reserveItem(null));
        reservationFormTest.setItemId(null);
        Assertions.assertThrows(Exception.class, () -> businessService.reserveItem(reservationFormTest));
        reservationFormTest.setItemId(0L);
        Assertions.assertNull(businessService.reserveItem(reservationFormTest));
    }

}
