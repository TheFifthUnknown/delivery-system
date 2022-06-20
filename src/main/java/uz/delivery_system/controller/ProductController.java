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
import uz.delivery_system.dto.product.ProductAmountDTO;
import uz.delivery_system.dto.product.ProductDTO;
import uz.delivery_system.dto.product.ProductDetailsDTO;
import uz.delivery_system.dto.product.ProductSliderDTO;
import uz.delivery_system.service.ProductService;

import java.util.List;

@Api(description = "Products")
@RestController
@RequestMapping(value = "products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Save products", notes = "Добавление продукта, здесь id не должен быть предоставлен ни один параметр.")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> create(@Validated ProductDTO productDTO){
        productService.addProduct(productDTO);
        String message = (productDTO.getId() == null) ? "Product added to the list":"Product information updated";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ApiOperation(value = "Product information updated", notes = "Изменение ранее существовавших данных о продукте")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> update(@Validated ProductDTO productDTO){
        productService.update(productDTO);
        return new ResponseEntity<>("Product information updated", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete product", notes = "заданный {id} удалить продукт из базы данных")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> delete(@PathVariable Long id){
        productService.delete(id);
        return new ResponseEntity<>("The product was removed from the base", HttpStatus.OK);
    }

    @ApiOperation(value = "Product Overview", notes = "Просмотр данных о продукте с заданным {id}")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProductDetailsDTO> showProductDetails(@PathVariable Long id){
        ProductDetailsDTO dto = productService.showProductDetails(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ApiOperation(value = "Product quantity", notes = "Обозначение количества/штуки для продукта.")
    @RequestMapping(method = RequestMethod.POST, value = "/amount", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> showProductDetails(@RequestBody @Validated ProductAmountDTO dto){
        productService.setProductAmount(dto);
        return new ResponseEntity<>("Product quantity changed", HttpStatus.OK);
    }

    @ApiOperation(value = "List of products", notes = "Просмотр списка продуктов фирмы")
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> listProductDetails(Pageable pageable){
        Page<ProductDetailsDTO> dtoPage = productService.listProductDetails(pageable);
        return new ResponseEntity<>(dtoPage, HttpStatus.OK);
    }


    // Role Shop_Manager
    @ApiOperation(value = "Products by firm and category", notes = "Выпуск продукции по фирмам и категориям")
    @RequestMapping(method = RequestMethod.GET, value = "/firm/{firmId}/category/{category}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ProductDetailsDTO>> listCategoryProducts(@PathVariable Long firmId, @PathVariable Long category){
        List<ProductDetailsDTO> dtoPage = productService.listCategoryProducts(firmId, category);
        return new ResponseEntity<>(dtoPage, HttpStatus.OK);
    }

    // Role Firm_Manager
    @ApiOperation(value = "add slider", notes = "Добавление слайдера в качестве рекламы к заданному продукту.")
    @RequestMapping(method = RequestMethod.POST, value = "/slider")
    public ResponseEntity<String> addImageToSlider(ProductSliderDTO dto){
        productService.addSliderImage(dto);
        return ResponseEntity.ok("Added a new image for Slider");
    }

    // Role Firm_Manager
    @ApiOperation(value = "delete slider", notes = "Удаление изображения слайдера, добавленного к данному продукту")
    @RequestMapping(method = RequestMethod.DELETE, value = "slider/{imageId}")
    public ResponseEntity<String> removeSliderItem(@PathVariable Long imageId){
        productService.removeSliderItem(imageId);
        return ResponseEntity.ok("Image removed from slider");
    }

}
