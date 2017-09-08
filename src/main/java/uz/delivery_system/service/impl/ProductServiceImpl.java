package uz.delivery_system.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.delivery_system.dto.product.ProductDTO;
import uz.delivery_system.dto.product.ProductDetailsDTO;
import uz.delivery_system.entity.LogoImageEntity;
import uz.delivery_system.entity.ProductEntity;
import uz.delivery_system.exceptions.NotFoundException;
import uz.delivery_system.repository.CategoryRepository;
import uz.delivery_system.repository.ProductRepository;
import uz.delivery_system.repository.UserRepository;
import uz.delivery_system.service.ProductService;
import uz.delivery_system.storage.StorageService;
import uz.delivery_system.utils.SecurityUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nodirbek on 15.07.2017.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;
    private String IMAGE_URL = "http://192.168.1.104:8080/api/v1/files/";

    @Override
    @Transactional
    public void addProduct(ProductDTO productDTO) {
//        CategoryEntity categoryEntity = categoryRepository.findOne(productDTO.getCategoryId());
//        if (categoryEntity == null) {
//            throw  new NotFoundException(1, "Mavjud bo'lmagan kategoriya ko'rsatildi");
//        }
        ProductEntity productEntity =  getProductEntity(productDTO);
        String filename = storageService.store(productDTO.getFile());
        productEntity.setProductLogo(getImageEntity(filename, productEntity));
        productRepository.save(productEntity);
//        categoryRepository.save(categoryEntity);
//        categoryEntity.getProductEntities().add(productEntity);
    }

    @Override
    public void update(ProductDTO productDTO) {
        ProductEntity productEntity = productRepository.findOne(productDTO.getId());
        if (productEntity == null) {
            throw new NotFoundException(1, "Bunday maxsulot maavjud emas!");
        }
        BeanUtils.copyProperties(productDTO, productEntity);
        productRepository.save(productEntity);
    }

    @Override
    public void delete(Long id) {
        ProductEntity productEntity = productRepository.findOne(id);
        if (productEntity == null) {
            throw new NotFoundException(1, "Maxsulot topilmadi");
        }
        productRepository.delete(id);
    }

    @Override
    public ProductDetailsDTO showProductDetails(Long id) {
        ProductEntity productEntity = productRepository.findOne(id);
        if (productEntity == null) {
            throw new NotFoundException(1,"Maxsulot topilmadi");
        }
        ProductDetailsDTO dto = getProductDetailsDTO(productEntity);
        return dto;
    }

    private ProductDetailsDTO getProductDetailsDTO(ProductEntity productEntity) {
        ProductDetailsDTO dto = new ProductDetailsDTO();
        BeanUtils.copyProperties(productEntity, dto);
        dto.setProductLogoUrl(productEntity.getLogoUrl());
        dto.setSliders(productEntity.getSliderImageNames());
        dto.setProductCategory(productEntity.getCategory().getCategoryName());
        dto.setProductFirm(productEntity.getFirm().getFirmName());
        return dto;
    }

    @Override
    public Page<ProductDetailsDTO> listProductDetails(Pageable pageable) {
        Page<ProductEntity> productEntities = productRepository.findAll(pageable);
        List<ProductDetailsDTO> list = new ArrayList<>();
        productEntities.forEach(productEntity ->
            list.add(getProductDetailsDTO(productEntity))
        );
        return new PageImpl<>(list);
    }

    @Override
    public List<ProductDetailsDTO> listCategoryProducts(Long firmId, Long categoryId) {
        List<ProductEntity> productEntities = productRepository.findByFirmIdAndCategoryIdOrderByIdDesc(firmId, categoryId);
        List<ProductDetailsDTO> list = new ArrayList<>();
        productEntities.forEach(productEntity -> {
            list.add(getProductDetailsDTO(productEntity));
        });
        return list;
    }

    private ProductEntity getProductEntity(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productDTO, productEntity);
        Long firmId = userRepository.findOne(SecurityUtils.getUserId()).getFirm().getId();
        productEntity.setFirmId(firmId);
        return productEntity;
    }

    private LogoImageEntity getImageEntity(String filename, ProductEntity productEntity) {
        LogoImageEntity imageEntity = new LogoImageEntity();
        imageEntity.setUrl(IMAGE_URL + filename);
        imageEntity.setProduct(productEntity);
        imageEntity.setUploadDate(new Date());
        return imageEntity;
    }

    private List<LogoImageEntity> getImageEntity(List<String> imageNames, ProductEntity productEntity) {
        List<LogoImageEntity> list = new ArrayList<>();
        Date currentDate = new Date();
        imageNames.forEach(imageName ->{
            LogoImageEntity imageEntity = new LogoImageEntity();
            imageEntity.setUrl(IMAGE_URL + imageName);
            imageEntity.setProduct(productEntity);
            imageEntity.setUploadDate(currentDate);
            list.add(imageEntity);
        });
        return list;
    }
}
