package uz.delivery_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.delivery_system.dto.region.RegionDTO;
import uz.delivery_system.service.RegionService;

/**
 * Created by Nodirbek on 13.07.2017.
 */
@RestController
@RequestMapping(value = "/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity create(@RequestBody @Validated RegionDTO regionDTO) {
        regionService.create(regionDTO);
        return ResponseEntity.ok("Region qo'shildi");
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity update(@RequestBody @Validated RegionDTO regionDTO) {
        regionService.update(regionDTO);
        return ResponseEntity.ok("Region yangilandi");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity delete(@PathVariable Long id) {
        regionService.delete(id);
        return ResponseEntity.ok("Region o'chirildi");
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> listCategory(Pageable pageable) {
        Page<RegionDTO> dto = regionService.listCategories(pageable);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
