package uz.delivery_system.service;

import uz.delivery_system.dto.user.ChangePasswordDTO;

/**
 * Created by Nodirbek on 12.07.2017.
 */
public interface UserService {
    void blockUser(Long id, Boolean blocked);

    Boolean exists(String username);

    void changePassword(ChangePasswordDTO dto);
}
