package uz.delivery_system.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.delivery_system.dto.product.ProductDTO;
import uz.delivery_system.dto.product.ProductDetailsDTO;

import java.util.List;

/**
 * Created by Nodirbek on 15.07.2017.
 */
public interface ProductService {

    void addProduct(ProductDTO productDTO);

    void update(ProductDTO productDTO);

    void delete(Long id);

    ProductDetailsDTO showProductDetails(Long id);

    Page<ProductDetailsDTO> listProductDetails(Pageable pageable);

    List<ProductDetailsDTO> listCategoryProducts(Long firmId, Long categoryId);
}
