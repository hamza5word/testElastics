package com.elastics.testelastics.models;

import lombok.Data;

@Data
public class ReservationForm {

    private Long userId;
    private Long itemId;

    public boolean isValid() {
        return (userId + itemId)  > 0;
    }

}
