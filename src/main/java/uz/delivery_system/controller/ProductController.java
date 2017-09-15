package uz.delivery_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.delivery_system.dto.product.ProductDTO;
import uz.delivery_system.dto.product.ProductDetailsDTO;
import uz.delivery_system.dto.product.ProductSliderDTO;
import uz.delivery_system.service.ProductService;

import java.util.List;

/**
 * Created by Nodirbek on 15.07.2017.
 */
@RestController
@RequestMapping(value = "products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@Validated ProductDTO productDTO){
        productService.addProduct(productDTO);
        return new ResponseEntity<>("Maxsulot ro'yhatga qo'shildi", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@Validated ProductDTO productDTO){
        productService.update(productDTO);
        return new ResponseEntity<>("Maxsulot ma'lumotlari yangilandi", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id){
        productService.delete(id);
        return new ResponseEntity<>("Maxsulot bazadan o'chirildi", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> showProductDetails(@PathVariable Long id){
        ProductDetailsDTO dto = productService.showProductDetails(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> listProductDetails(Pageable pageable){
        Page<ProductDetailsDTO> dtoPage = productService.listProductDetails(pageable);
        return new ResponseEntity<>(dtoPage, HttpStatus.OK);
    }

    // Role Shop_Manager
    @RequestMapping(method = RequestMethod.GET, value = "/firm/{firmId}/category/{category}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> listCategoryProducts(@PathVariable Long firmId, @PathVariable Long category){
        List<ProductDetailsDTO> dtoPage = productService.listCategoryProducts(firmId, category);
        return new ResponseEntity<>(dtoPage, HttpStatus.OK);
    }

    // Role Firm_Manager
    @RequestMapping(method = RequestMethod.POST, value = "{id}/slider")
    public ResponseEntity<?> addImageToSlider(@PathVariable Long id, ProductSliderDTO dto){
        productService.addSliderImage(id, dto);
        return ResponseEntity.ok("Slider uchun yangi rasm qo'shildi");
    }

    // Role Firm_Manager
    @RequestMapping(method = RequestMethod.DELETE, value = "slider/{imageId}")
    public ResponseEntity<?> removeSliderItem(@PathVariable Long imageId){
        productService.removeSliderItem(imageId);
        return ResponseEntity.ok("Slider uchun yangi rasm qo'shildi");
    }


}
