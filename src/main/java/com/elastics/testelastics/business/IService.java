package com.elastics.testelastics.business;

import com.elastics.testelastics.entities.Reservation;
import com.elastics.testelastics.models.ReservationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface IService {

    Logger LOG = LoggerFactory.getLogger(IService.class);

    byte [] getItemImage(Long id);
    Reservation reserveItem(ReservationForm reservationForm);

}
