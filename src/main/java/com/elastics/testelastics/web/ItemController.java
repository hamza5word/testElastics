package com.elastics.testelastics.web;

import com.elastics.testelastics.business.IService;
import com.elastics.testelastics.entities.Reservation;
import com.elastics.testelastics.models.ReservationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("items")
@RequiredArgsConstructor
public class ItemController {

    private final IService service;

    @GetMapping("{id}/image")
    public ResponseEntity<?> getItemImage(@PathVariable  Long id) {
        byte [] itemImg = service.getItemImage(id);
        if(itemImg != null) {
            return ResponseEntity.status(200).contentType(MediaType.IMAGE_JPEG).body(itemImg);
        }
        return ResponseEntity.status(404).body("Item Image not found");
    }

    @PostMapping("reserve")
    public ResponseEntity<?> reserveItem(@RequestBody ReservationForm reservationForm) {
        Reservation reservation = service.reserveItem(reservationForm);
        if(reservation != null) {
            return  ResponseEntity.status(200).body(reservation);
        }
        return ResponseEntity.status(400).body("Reservation for item is failed");
    }

}
