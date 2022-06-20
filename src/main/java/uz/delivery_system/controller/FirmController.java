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

@Api(description = "Firms")
@RestController
@RequestMapping("/firms")
public class FirmController {

    @Autowired
    private FirmService firmService;

    @ApiOperation(value = "create", notes = "Создание новой фирмы")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Validated FirmRegistrationDTO registrationDTO) {
        this.validateRegistration(registrationDTO);
        FirmEntity firmEntity = firmService.createFirmWithManager(registrationDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(firmEntity.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "update", notes = "Обновление существующей фирмы")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> update(@RequestBody @Validated FirmUpdateDTO dto) {
        firmService.updateFirm(dto);
        return ResponseEntity.ok("Information updated!");
    }

    @ApiOperation(value = "delete", notes = "Просмотр информации о фирме, предоставленной {id}")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<FirmDetailsDTO> details(@PathVariable Long id) {
        FirmDetailsDTO dto = firmService.getFirmDetails(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ApiOperation(value = "firms", notes = "Список фирм может быть представлен в виде страницы, страницы и сортировки по url")
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<FirmDetailsDTO>> listFirmDetails(Pageable pageable) {
        Page<FirmDetailsDTO> dto = firmService.listFirmDetails(pageable);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ApiOperation(value = "firms by Category", notes = "Просмотр списка фирм, относящихся к категории. " +
            "Url отправляет категорию {id}")
    @RequestMapping(method = RequestMethod.GET, value = "/category/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CategoryFirmsDTO>> getFirmsByCategory(@PathVariable Long id) {
        List<CategoryFirmsDTO> dto = firmService.getFirmsByCategory(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ApiOperation(value = "blocking the firm", notes = "Блокировка данной фирмы. как не войти в систему после блокировки фирмы")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/block", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> block(@PathVariable Long id) {
        firmService.blockFirm(id, Boolean.TRUE);
        return new ResponseEntity("The firm is blocked", HttpStatus.OK);
    }

    @ApiOperation(value = "opening the firm from the block")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/unblock", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> unblock(@PathVariable Long id) {
        firmService.blockFirm(id, Boolean.FALSE);
        return new ResponseEntity("The firm opened from block", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a given firm", notes = "Удалить фирму. Если фирма вновь создана, ее можно удалить. " +
            "Фирма не может быть удалена, если продукт был добавлен, или если были выполнены другие действия. Заблокируйте это вместо" +
            "использование функции обходится")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String > delete(@PathVariable Long id) {
        firmService.deleteFirmWithManager(id);
        return new ResponseEntity("The firm was removed from the base", HttpStatus.OK);
    }

    @ApiOperation(value = "company logo", notes = "присвоение фирме нового логотипа")
    @RequestMapping(method = RequestMethod.POST, value = "/logo")
    public ResponseEntity<?> changeLogo(MultipartFile file){
        firmService.changeFirmLogo(file);
        return ResponseEntity.ok("Updated the logo of the firm");
    }

    private void validateRegistration(FirmRegistrationDTO registrationDTO) {
        if(!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())){
            throw new ConfirmPasswordException(1, "Enter the repeated password correctly!");
        }
    }
}
