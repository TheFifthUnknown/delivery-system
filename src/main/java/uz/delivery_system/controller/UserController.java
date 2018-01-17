package uz.delivery_system.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.delivery_system.dto.user.ChangePasswordDTO;
import uz.delivery_system.service.UserService;

/**
 * Created by Nodirbek on 12.07.2017.
 */
@Api(description = "Foydalanuvchilar")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "bloklash")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/block", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> block(@PathVariable Long id) {
        userService.blockUser(id, Boolean.TRUE);
        return ResponseEntity.ok("Foydalanuvchi bloklandi");
    }

    @ApiOperation(value = "blokdan ochish")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/unblock", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> unblock(@PathVariable Long id) {
        userService.blockUser(id, Boolean.FALSE);
        return ResponseEntity.ok("Foydalanuvchi blokdan chiqarildi");
    }

    @ApiOperation(value = "loginni tekshirish", notes = "Login mavjudligini tekshirish")
    @RequestMapping(method = RequestMethod.GET, value = "/exist/{login}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> exist(@PathVariable String login) {
        return ResponseEntity.ok(userService.exists(login));
    }

    @ApiOperation(value = "parolni o'zgartirish")
    @RequestMapping(method = RequestMethod.POST, value = "/change/password", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO dto) {
        userService.changePassword(dto);
        return ResponseEntity.ok("Parol muvaffaqqiyatli o'zgartirildi");
    }

}
