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

/**
 * Created by Nodirbek on 15.07.2017.
 */
@Api(description = "Maxsulotlar")
@RestController
@RequestMapping(value = "products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Maxsulot qo'shish", notes = "Maxsulot qo'shish, bu yerda id hech qanday parametr berilmasligi kerak.")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> create(@Validated ProductDTO productDTO){
        productService.addProduct(productDTO);
        String message = (productDTO.getId() == null) ? "Maxsulot ro'yhatga qo'shildi":"Maxsulot ma'lumotlari yangilandi";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ApiOperation(value = "Maxsulotni yangilash", notes = "Avvaldan mavjud maxsulot ma'lumotlarini o'zgartirish")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> update(@Validated ProductDTO productDTO){
        productService.update(productDTO);
        return new ResponseEntity<>("Maxsulot ma'lumotlari yangilandi", HttpStatus.OK);
    }

    @ApiOperation(value = "Maxsulotni o'chirish", notes = "berilgan {id} li maxsulotni bazadan o'chirish")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> delete(@PathVariable Long id){
        productService.delete(id);
        return new ResponseEntity<>("Maxsulot bazadan o'chirildi", HttpStatus.OK);
    }

    @ApiOperation(value = "Maxsulotni ko'rish", notes = "Berilgan {id} li maxsulotni ma'lumotlarini ko'rish")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProductDetailsDTO> showProductDetails(@PathVariable Long id){
        ProductDetailsDTO dto = productService.showProductDetails(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ApiOperation(value = "Maxsulot miqdori", notes = "Maxsulot uchun miqdor/штук belgilash. ")
    @RequestMapping(method = RequestMethod.POST, value = "/amount", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> showProductDetails(@RequestBody @Validated ProductAmountDTO dto){
        productService.setProductAmount(dto);
        return new ResponseEntity<>("Maxsulot miqdori o'zgartirildi", HttpStatus.OK);
    }

    @ApiOperation(value = "Maxsulotlar ro'yhati", notes = "Firma maxsulotlari ro'yhatini ko'rish")
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> listProductDetails(Pageable pageable){
        Page<ProductDetailsDTO> dtoPage = productService.listProductDetails(pageable);
        return new ResponseEntity<>(dtoPage, HttpStatus.OK);
    }


    // Role Shop_Manager
    @ApiOperation(value = "Maxsulotlar firma va kategoriya bo'yicha", notes = "Firma va kategoriya bo'yicha maxsulotlarni chiqarish")
    @RequestMapping(method = RequestMethod.GET, value = "/firm/{firmId}/category/{category}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ProductDetailsDTO>> listCategoryProducts(@PathVariable Long firmId, @PathVariable Long category){
        List<ProductDetailsDTO> dtoPage = productService.listCategoryProducts(firmId, category);
        return new ResponseEntity<>(dtoPage, HttpStatus.OK);
    }

    // Role Firm_Manager
    @ApiOperation(value = "slider qo'shish", notes = "Berilgan maxsulotga reklama sifaatida slider qo'shish.")
    @RequestMapping(method = RequestMethod.POST, value = "/slider")
    public ResponseEntity<String> addImageToSlider(ProductSliderDTO dto){
        productService.addSliderImage(dto);
        return ResponseEntity.ok("Slider uchun yangi rasm qo'shildi");
    }

    // Role Firm_Manager
    @ApiOperation(value = "sliderni o'chirish", notes = "Berilgan maxsulotga qo'shilgan slider rasmini o'chirish")
    @RequestMapping(method = RequestMethod.DELETE, value = "slider/{imageId}")
    public ResponseEntity<String> removeSliderItem(@PathVariable Long imageId){
        productService.removeSliderItem(imageId);
        return ResponseEntity.ok("Rasm sliderdan o'chirildi");
    }

}
