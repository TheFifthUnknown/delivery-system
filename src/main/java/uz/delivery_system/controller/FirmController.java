package uz.delivery_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.delivery_system.dto.firm.FirmDetailsDTO;
import uz.delivery_system.dto.firm.FirmRegistrationDTO;
import uz.delivery_system.dto.firm.FirmUpdateDTO;
import uz.delivery_system.entity.FirmEntity;
import uz.delivery_system.exceptions.ConfirmPasswordException;
import uz.delivery_system.service.FirmService;

import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Nodirbek on 08.07.2017.
 */
@RestController
@RequestMapping("/firms")
public class FirmController {

    @Autowired
    private FirmService firmService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity create(@RequestBody @Validated FirmRegistrationDTO registrationDTO) {
        this.validateRegistration(registrationDTO);
        FirmEntity firmEntity = firmService.createFirmWithManager(registrationDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(firmEntity.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity update(@RequestBody @Validated FirmUpdateDTO dto) {
        firmService.updateFirm(dto);
        return ResponseEntity.ok("Ma\'lumotlar yangilandi!");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> details(@PathVariable Long id) {
        FirmDetailsDTO dto = firmService.getFirmDetails(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> listFirmDetails(Pageable pageable) {
        Page<FirmDetailsDTO> dto = firmService.listFirmDetails(pageable);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}/block", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> block(@PathVariable Long id) {
        firmService.blockFirm(id);
        return new ResponseEntity("Firma blocklab qo'yildi", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        firmService.deleteFirmWithManager(id);
        return new ResponseEntity("Firma bazadan o\'chirildi", HttpStatus.OK);
    }

    private void validateRegistration(FirmRegistrationDTO registrationDTO) {
        if(!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())){
            throw new ConfirmPasswordException(1, "Takroriy parolni to'g'ri kiriting!");
        }
    }
}
