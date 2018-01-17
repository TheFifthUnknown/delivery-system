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
import uz.delivery_system.dto.region.RegionDTO;
import uz.delivery_system.service.RegionService;

/**
 * Created by Nodirbek on 13.07.2017.
 */
@Api(description = "Regionlar")
@RestController
@RequestMapping(value = "/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @ApiOperation(value = "Region qo'shish", notes = "Region yaratish. Firma va do'konlar shu regionlarga biriktiriladi")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> create(@RequestBody @Validated RegionDTO regionDTO) {
        regionService.create(regionDTO);
        return ResponseEntity.ok("Region qo'shildi");
    }

    @ApiOperation(value = "regionni yangilash", notes = "Berilgan region ma'lumotlarini yangilash")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> update(@RequestBody @Validated RegionDTO regionDTO) {
        regionService.update(regionDTO);
        return ResponseEntity.ok("Region yangilandi");
    }

    @ApiOperation(value = "Regionni o'chirish", notes = "Regionni o'chirish. O'chirish amallarini iloji boricha ishlatmaslik tavfsiya qilinadi")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        regionService.delete(id);
        return ResponseEntity.ok("Region o'chirildi");
    }

    @ApiOperation(value = "Regionlar ro'yhati", notes = "Regionlar ro'yhatini pagination ko'rinishda chiqarish")
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<RegionDTO>> listCategory(Pageable pageable) {
        Page<RegionDTO> dto = regionService.listCategories(pageable);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
