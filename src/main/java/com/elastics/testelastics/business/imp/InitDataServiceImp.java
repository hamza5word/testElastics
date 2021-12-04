package com.elastics.testelastics.business.imp;

import com.elastics.testelastics.business.InitDataService;
import com.elastics.testelastics.dao.ItemRepository;
import com.elastics.testelastics.dao.UserRepository;
import com.elastics.testelastics.entities.Item;
import com.elastics.testelastics.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        userRepository.findAll().forEach(u -> Stream.of("s21", "dell latitude").forEach(item -> {
            Item i = new Item();
            i.setTitle(item);
            i.setQuantity((int) (Math.random() * 100));
            i.setUser(u);
            itemRepository.save(i);
        }));
    }
}
