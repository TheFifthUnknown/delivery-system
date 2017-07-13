package uz.delivery_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.delivery_system.entity.UserEntity;
import uz.delivery_system.exceptions.NotFoundException;
import uz.delivery_system.repository.UserRepository;
import uz.delivery_system.service.UserService;

import java.util.Optional;

/**
 * Created by Nodirbek on 12.07.2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void blockUser(Long id, Boolean block) {
        UserEntity userEntity = userRepository.findOne(id);
        if (userEntity == null) {
            throw new NotFoundException(1, "Bunday foydalanuvchi mavjud emas!");
        }
        userEntity.setActive(!block);
        userRepository.save(userEntity);
    }

    @Override
    public Boolean exists(String username) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        return userEntity.isPresent();
    }
}
