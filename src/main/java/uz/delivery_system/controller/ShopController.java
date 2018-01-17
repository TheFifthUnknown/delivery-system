package uz.delivery_system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.delivery_system.dto.firm.FirmDetailsDTO;
import uz.delivery_system.dto.firm.FirmRegistrationDTO;
import uz.delivery_system.dto.firm.FirmUpdateDTO;
import uz.delivery_system.dto.shop.ShopDetailsDTO;
import uz.delivery_system.dto.shop.ShopRegistrationDTO;
import uz.delivery_system.dto.shop.ShopUpdateDTO;
import uz.delivery_system.entity.FirmEntity;
import uz.delivery_system.entity.ShopEntity;
import uz.delivery_system.exceptions.ConfirmPasswordException;
import uz.delivery_system.service.ShopService;

import java.net.URI;

/**
 * Created by Nodirbek on 08.07.2017.
 */
@Api(description = "Do'konlar")
@RestController
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @ApiOperation(value = "Do'kon qo'shish")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Validated ShopRegistrationDTO registrationDTO) {
        this.validateRegistration(registrationDTO);
        ShopEntity shopEntity = shopService.createShopWithManager(registrationDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(shopEntity.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Do'konni yangilash")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> update(@RequestBody @Validated ShopUpdateDTO dto) {
        shopService.updateShop(dto);
        return ResponseEntity.ok("Ma\'lumotlar yangilandi!");
    }

    @ApiOperation(value = "Do'konni ko'rish")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ShopDetailsDTO> details(@PathVariable Long id) {
        ShopDetailsDTO dto = shopService.getShopDetails(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ApiOperation(value = "Do'konlar ro'yhati", notes = "Do'konlar ro'yhatini pagination ko'rinishda ko'rish")
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<ShopDetailsDTO>> listShopDetails(Pageable pageable) {
        Page<ShopDetailsDTO> dto = shopService.listShopDetails(pageable);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ApiOperation(value = "Do'konni bloklash")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/block", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> block(@PathVariable Long id) {
        shopService.blockShop(id, Boolean.TRUE);
        return new ResponseEntity("Do'kon blocklab qo'yildi", HttpStatus.OK);
    }

    @ApiOperation(value = "Do'konni blokdan ochish")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/unblock", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> unblock(@PathVariable Long id) {
        shopService.blockShop(id, Boolean.FALSE);
        return new ResponseEntity("Do'kon blockdan ochildi", HttpStatus.OK);
    }

    @ApiOperation(value = "Do'konni o'chirish", notes = "Do'konni o'chirish, ishlarish tavfsiya qilinmaydi")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        shopService.deleteShopWithManager(id);
        return new ResponseEntity("Do'kon bazadan o\'chirildi", HttpStatus.OK);
    }

    private void validateRegistration(ShopRegistrationDTO registrationDTO) {
        if(!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())){
            throw new ConfirmPasswordException(1, "Takroriy parolni to'g'ri kiriting!");
        }
    }
}
