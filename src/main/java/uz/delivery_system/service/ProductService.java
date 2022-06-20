package uz.delivery_system.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.delivery_system.dto.product.ProductAmountDTO;
import uz.delivery_system.dto.product.ProductDTO;
import uz.delivery_system.dto.product.ProductDetailsDTO;
import uz.delivery_system.dto.product.ProductSliderDTO;

import java.util.List;
public interface ProductService {

    void addProduct(ProductDTO productDTO);

    void update(ProductDTO productDTO);

    void delete(Long id);

    ProductDetailsDTO showProductDetails(Long id);

    Page<ProductDetailsDTO> listProductDetails(Pageable pageable);

    List<ProductDetailsDTO> listCategoryProducts(Long firmId, Long categoryId);

    void addSliderImage(ProductSliderDTO file);

    void removeSliderItem(Long imageId);

    void setProductAmount(ProductAmountDTO dto);
}
