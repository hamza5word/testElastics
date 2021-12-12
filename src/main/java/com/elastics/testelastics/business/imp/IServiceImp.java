package com.elastics.testelastics.business.imp;

import com.elastics.testelastics.business.IService;
import com.elastics.testelastics.config.AppPropertiesConfig;
import com.elastics.testelastics.dao.ItemRepository;
import com.elastics.testelastics.dao.ReservationRepository;
import com.elastics.testelastics.dao.UserRepository;
import com.elastics.testelastics.entities.Item;
import com.elastics.testelastics.entities.Reservation;
import com.elastics.testelastics.entities.User;
import com.elastics.testelastics.models.ReservationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class IServiceImp implements IService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final ReservationRepository reservationRepository;
    private final AppPropertiesConfig propertiesConfig;

    @Override
    public byte[] getItemImage(Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        if(item != null) {
            File imgFile = new File(propertiesConfig.ITEM_IMG_PATH, item.getPhoto());
            try {
                return Files.readAllBytes(Paths.get(imgFile.toURI()));
            } catch (Exception e) {
                if(Boolean.parseBoolean(propertiesConfig.DEV_MODE)) e.printStackTrace();
                LOG.error(e.getMessage(), e);
            }
        }
        return null;
    }

    @Override
    public Reservation reserveItem(ReservationForm reservationForm) {
        if(reservationForm != null && reservationForm.isValid()) {
            Reservation r = new Reservation();
            r.setDate(new Date());
            User user = userRepository.findById(reservationForm.getUserId()).orElse(null);
            Item item = itemRepository.findById(reservationForm.getItemId()).orElse(null);
            if(user != null && item != null) {
                item.setReserved(true);
                r.setUser(user);
                r.setItem(item);
                itemRepository.save(item);
                return reservationRepository.save(r);
            }
        }
        return null;
    }
}
