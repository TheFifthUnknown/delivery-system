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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.delivery_system.dto.firm.CategoryFirmsDTO;
import uz.delivery_system.dto.firm.FirmDetailsDTO;
import uz.delivery_system.dto.firm.FirmRegistrationDTO;
import uz.delivery_system.dto.firm.FirmUpdateDTO;
import uz.delivery_system.entity.FirmEntity;
import uz.delivery_system.exceptions.ConfirmPasswordException;
import uz.delivery_system.service.FirmService;

import java.net.URI;
import java.util.List;

/**
 * Created by Nodirbek on 08.07.2017.
 */
@Api(description = "Firmalar")
@RestController
@RequestMapping("/firms")
public class FirmController {

    @Autowired
    private FirmService firmService;

    @ApiOperation(value = "yaratish", notes = "Yangi firma yaratish ")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Validated FirmRegistrationDTO registrationDTO) {
        this.validateRegistration(registrationDTO);
        FirmEntity firmEntity = firmService.createFirmWithManager(registrationDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(firmEntity.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "yangilash", notes = "Mavjud firmani yangilash")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> update(@RequestBody @Validated FirmUpdateDTO dto) {
        firmService.updateFirm(dto);
        return ResponseEntity.ok("Ma\'lumotlar yangilandi!");
    }

    @ApiOperation(value = "firmani ko'rish", notes = "Berilgan {id} li firmani ma'lumotlarini ko'rish")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<FirmDetailsDTO> details(@PathVariable Long id) {
        FirmDetailsDTO dto = firmService.getFirmDetails(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ApiOperation(value = "firmalar", notes = "Firmalar ro'yhatini pagination ko'rinishi, url manzilga page va sort berish mumkin")
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<FirmDetailsDTO>> listFirmDetails(Pageable pageable) {
        Page<FirmDetailsDTO> dto = firmService.listFirmDetails(pageable);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ApiOperation(value = "kategoriyaga bo'yicha firmalar", notes = "Kategoriyaga tegishli firmalar ro'yhatini ko'rish. " +
            "Url manzilga caategoriyani {id} si yuboriladi")
    @RequestMapping(method = RequestMethod.GET, value = "/category/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CategoryFirmsDTO>> getFirmsByCategory(@PathVariable Long id) {
        List<CategoryFirmsDTO> dto = firmService.getFirmsByCategory(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ApiOperation(value = "firmani bloklash", notes = "Berilgan firmani bloklab qo'yish. firma bloklangandan so'ng login qilib kira olmaydi")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/block", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> block(@PathVariable Long id) {
        firmService.blockFirm(id, Boolean.TRUE);
        return new ResponseEntity("Firma blocklab qo'yildi", HttpStatus.OK);
    }

    @ApiOperation(value = "firmani blokdan ochish")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/unblock", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> unblock(@PathVariable Long id) {
        firmService.blockFirm(id, Boolean.FALSE);
        return new ResponseEntity("Firma blockdan ochildi", HttpStatus.OK);
    }

    @ApiOperation(value = "Berilgan firmani o'chirish", notes = "Firmani o'chirish. Agarda firmaga yangi yaratilgan bo'lsa o'chirish mumkin. " +
            "Firmaga maxsulot qo'shilgan bo'lsa, yoki boshqa amallar bajarilgan bo'lsa o'chirish mumkin emas. Buni o'rniga bloklash" +
            "funksiyasini ishlatish tavfsiya etiladi")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String > delete(@PathVariable Long id) {
        firmService.deleteFirmWithManager(id);
        return new ResponseEntity("Firma bazadan o\'chirildi", HttpStatus.OK);
    }

    @ApiOperation(value = "firma logotipi", notes = "firmaga yangi logotip belgilash")
    @RequestMapping(method = RequestMethod.POST, value = "/logo")
    public ResponseEntity<?> changeLogo(MultipartFile file){
        firmService.changeFirmLogo(file);
        return ResponseEntity.ok("Firmani logotipi yangilandi");
    }

    private void validateRegistration(FirmRegistrationDTO registrationDTO) {
        if(!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())){
            throw new ConfirmPasswordException(1, "Takroriy parolni to'g'ri kiriting!");
        }
    }
}
