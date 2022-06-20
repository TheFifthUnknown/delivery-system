package uz.delivery_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uz.delivery_system.dto.user.ChangePasswordDTO;
import uz.delivery_system.entity.UserEntity;
import uz.delivery_system.exceptions.NotFoundException;
import uz.delivery_system.repository.UserRepository;
import uz.delivery_system.service.UserService;
import uz.delivery_system.utils.SecurityUtils;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void blockUser(Long id, Boolean block) {
        UserEntity userEntity = userRepository.findOne(id);
        if (userEntity == null) {
            throw new NotFoundException(1, "Such a user does not exist!");
        }
        userEntity.setActive(!block);
        userRepository.save(userEntity);
    }

    @Override
    public Boolean exists(String username) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        return userEntity.isPresent();
    }

    @Override
    public void changePassword(ChangePasswordDTO dto) {
        UserEntity userEntity = userRepository.findOne(SecurityUtils.getUserId());
        if (dto.getConfirm().equals(dto.getPassword())){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            userEntity.setPassword(encoder.encode(dto.getPassword()));
            System.out.println(encoder.encode(dto.getPassword()));
        }
        userRepository.save(userEntity);
    }
}