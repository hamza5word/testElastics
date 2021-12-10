package com.elastics.testelastics.business.imp;

import com.elastics.testelastics.business.InitDataService;
import com.elastics.testelastics.dao.ItemRepository;
import com.elastics.testelastics.dao.UserRepository;
import com.elastics.testelastics.entities.Item;
import com.elastics.testelastics.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class InitDataServiceImp implements InitDataService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Override
    public void initUsers() {
        Stream.of("hamza", "ahmed", "karim", "walid").forEach(user -> {
            User u = new User();
            u.setName(user);
            userRepository.save(u);
        });
    }

    @Override
    public void initItems() {
        List<User> users = userRepository.findAll();
        Stream.of("Galaxy s21", "Iphone 12", "Asus ROG Strix 17", "Samsung LED 50", "Apple Watch").forEach(item -> {
            Item i = new Item();
            i.setTitle(item);
            i.setQuantity(new SecureRandom().nextInt() * 100);
            i.setUser(users.get(new SecureRandom().nextInt() * users.size()));
            itemRepository.save(i);
        });
    }
}
