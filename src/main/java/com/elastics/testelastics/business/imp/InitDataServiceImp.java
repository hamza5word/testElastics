package com.elastics.testelastics.business.imp;

import com.elastics.testelastics.business.InitDataService;
import com.elastics.testelastics.config.AppPropertiesConfig;
import com.elastics.testelastics.dao.ItemRepository;
import com.elastics.testelastics.dao.ReservationRepository;
import com.elastics.testelastics.dao.UserRepository;
import com.elastics.testelastics.entities.Item;
import com.elastics.testelastics.entities.Reservation;
import com.elastics.testelastics.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.File;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class InitDataServiceImp implements InitDataService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final ReservationRepository reservationRepository;
    private final AppPropertiesConfig propertiesConfig;

    @Override
    public void initUsers() {
        if(Boolean.parseBoolean(propertiesConfig.DEV_MODE)) {
            Stream.of("hamza", "ahmed", "karim", "walid").forEach(user -> {
                User u = new User();
                u.setName(user);
                userRepository.save(u);
            });
        }
    }

    @Override
    public void initItems() {
        if(Boolean.parseBoolean(propertiesConfig.DEV_MODE)) {
            Stream.of("Galaxy s21 Ultra", "Iphone 12 Pro Max", "Asus ROG Strix 17", "Samsung LED 50", "Apple Watch").forEach(item -> {
                Item i = new Item();
                i.setTitle(item);
                i.setPhoto(item + ".jpg");
                i.setReserved(false);
                i.setQuantity(new SecureRandom().nextInt(100));
                i.setPrice(new SecureRandom().nextDouble() * 100000);
                itemRepository.save(i);
            });
        }
    }

    @Override
    public void initReservations() {
        if(Boolean.parseBoolean(propertiesConfig.DEV_MODE)) {
            List<User> users = userRepository.findAll();
            List<Item> items = itemRepository.findAll();
            for (int i = 0; i < 10; i++) {
                Reservation r = new Reservation();
                r.setDate(new Date());
                r.setUser(users.get(new SecureRandom().nextInt(users.size())));
                Item item = items.get(new SecureRandom().nextInt(items.size()));
                item.setReserved(true);
                r.setItem(item);
                itemRepository.save(item);
                reservationRepository.save(r);
            }
        }
    }

    @Override
    public void initRequiredDirectories() {
        StringBuilder collector = new StringBuilder();
        String baseLocation = propertiesConfig.ITEM_IMG_PATH;
        String envHome = System.getProperty("user.home");
        String splitLocation = baseLocation.replace(envHome, "").substring(1);
        for(String dir : splitLocation.split("/")) {
            collector.append("/").append(dir);
            File dirToMake = new File(envHome, collector.substring(1));
            if(!dirToMake.exists()) {
                if(!dirToMake.mkdir()) {
                    LOG.error("DEV_LOG Directory " + dir + " creation failed");
                }
            }
        }

    }
}
