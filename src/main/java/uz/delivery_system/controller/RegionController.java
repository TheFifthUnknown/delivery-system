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

@Api(description = "Regions")
@RestController
@RequestMapping(value = "/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @ApiOperation(value = "Add Region", notes = "Создание региона. Фирмы и магазины будут прикреплены к этим регионам")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> create(@RequestBody @Validated RegionDTO regionDTO) {
        regionService.create(regionDTO);
        return ResponseEntity.ok("Region added");
    }

    @ApiOperation(value = "Кegion update", notes = "Обновление данных о данном регионе")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> update(@RequestBody @Validated RegionDTO regionDTO) {
        regionService.update(regionDTO);
        return ResponseEntity.ok("Region updated");
    }

    @ApiOperation(value = "Delete region", notes = "Удаление региона. Не использовать действия удаления, насколько это возможно, будет оправдано")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        regionService.delete(id);
        return ResponseEntity.ok("Region removed");
    }

    @ApiOperation(value = "List of Regions", notes = "Вывод списка регионов в виде разбиения на страницы")
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<RegionDTO>> listCategory(Pageable pageable) {
        Page<RegionDTO> dto = regionService.listCategories(pageable);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
