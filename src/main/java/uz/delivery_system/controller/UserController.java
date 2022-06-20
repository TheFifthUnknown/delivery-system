package uz.delivery_system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.delivery_system.dto.user.ChangePasswordDTO;
import uz.delivery_system.service.UserService;

@Api(description = "Users")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "blocking")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/block", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> block(@PathVariable Long id) {
        userService.blockUser(id, Boolean.TRUE);
        return ResponseEntity.ok("User blocked");
    }

    @ApiOperation(value = "unblock")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/unblock", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> unblock(@PathVariable Long id) {
        userService.blockUser(id, Boolean.FALSE);
        return ResponseEntity.ok("User unblocked");
    }

    @ApiOperation(value = "login verification", notes = "Проверка наличия логина")
    @RequestMapping(method = RequestMethod.GET, value = "/exist/{login}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> exist(@PathVariable String login) {
        return ResponseEntity.ok(userService.exists(login));
    }

    @ApiOperation(value = "Change Password")
    @RequestMapping(method = RequestMethod.POST, value = "/change/password", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO dto) {
        userService.changePassword(dto);
        return ResponseEntity.ok("Password successfully changed");
    }

}
