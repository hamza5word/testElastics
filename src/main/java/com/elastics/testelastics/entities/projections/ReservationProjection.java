package com.elastics.testelastics.entities.projections;

import com.elastics.testelastics.entities.Item;
import com.elastics.testelastics.entities.Reservation;
import com.elastics.testelastics.entities.User;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "RP", types = {Reservation.class})
public interface ReservationProjection {

    Date getDate();
    User getUser();
    Item getItem();

}
