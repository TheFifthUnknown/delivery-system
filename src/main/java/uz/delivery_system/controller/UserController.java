package uz.delivery_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.delivery_system.dto.user.ChangePasswordDTO;
import uz.delivery_system.service.UserService;

/**
 * Created by Nodirbek on 12.07.2017.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/block", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity block(@PathVariable Long id) {
        userService.blockUser(id, Boolean.TRUE);
        return ResponseEntity.ok("Foydalanuvchi bloklandi");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/unblock", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity unblock(@PathVariable Long id) {
        userService.blockUser(id, Boolean.FALSE);
        return ResponseEntity.ok("Foydalanuvchi blokdan chiqarildi");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/exist/{login}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity exist(@PathVariable String login) {
        return ResponseEntity.ok(userService.exists(login));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/change/password", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO dto) {
        userService.changePassword(dto);
        return ResponseEntity.ok("Parol muvaffaqqiyatli o'zgartirildi");
    }

}
