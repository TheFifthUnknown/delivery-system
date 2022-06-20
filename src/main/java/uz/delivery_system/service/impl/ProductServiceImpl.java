package uz.delivery_system.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.delivery_system.dto.product.ProductAmountDTO;
import uz.delivery_system.dto.product.ProductDTO;
import uz.delivery_system.dto.product.ProductDetailsDTO;
import uz.delivery_system.dto.product.ProductSliderDTO;
import uz.delivery_system.entity.LogoImageEntity;
import uz.delivery_system.entity.ProductEntity;
import uz.delivery_system.entity.SliderImageEntity;
import uz.delivery_system.entity.UserEntity;
import uz.delivery_system.exceptions.NotFoundException;
import uz.delivery_system.repository.CategoryRepository;
import uz.delivery_system.repository.ProductRepository;
import uz.delivery_system.repository.SliderImageRepository;
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

    @Autowired
    private SliderImageRepository sliderImageRepository;

    private String IMAGE_URL = "http://34.79.110.94:8083/api/v1/files/";

    @Override
    @Transactional
    public void addProduct(ProductDTO productDTO) {
        if (productDTO.getId()!=null){
            update(productDTO);
        }else{
            ProductEntity productEntity =  getProductEntity(productDTO);
            String filename = storageService.store(productDTO.getFile());
            productEntity.setProductLogo(getImageEntity(filename, productEntity));
            productRepository.save(productEntity);
        }
    }

    @Override
    public void update(ProductDTO productDTO) {
        ProductEntity productEntity = productRepository.findOne(productDTO.getId());
        if (productEntity == null) {
            throw new NotFoundException(1, "Bunday maxsulot mavjud emas!");
        }
        if(productDTO.getFile()!= null){
            String filename = storageService.store(productDTO.getFile());
            productEntity.setProductLogo(getImageEntity(filename, productEntity));
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
        dto.setSliders(productEntity.getSliderDetails());
        dto.setProductCategory(productEntity.getCategory().getCategoryName());
        dto.setProductFirm(productEntity.getFirm().getFirmName());
        return dto;
    }

    @Override
    public Page<ProductDetailsDTO> listProductDetails(Pageable pageable) {
        UserEntity userEntity = userRepository.findOne(SecurityUtils.getUserId());
        if (userEntity.getFirm()==null){
            throw new NullPointerException("Firma logini bilan kiring");
        }
        Page<ProductEntity> productEntities = productRepository.findByFirmId(userEntity.getFirm().getId(),pageable);
        List<ProductDetailsDTO> list = new ArrayList<>();
        productEntities.forEach(productEntity ->
            list.add(getProductDetailsDTO(productEntity))
        );
        Page<ProductDetailsDTO> page = new PageImpl<>(list, pageable, productEntities.getTotalElements());
        return page;
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

    @Override
    public void addSliderImage(ProductSliderDTO dto) {
        ProductEntity productEntity = productRepository.findOne(dto.getProductId());
        if (productEntity == null) {
            throw new NotFoundException(1,"Bunday maxsulot topilmadi");
        }
        String filename = storageService.store(dto.getFile());
        SliderImageEntity sliderImageEntity = getSliderImageEntity(productEntity, filename, dto.getTitle());
        productEntity.getSlides().add(sliderImageEntity);
        productRepository.save(productEntity);
    }

    @Override
    public void setProductAmount(ProductAmountDTO dto) {
        ProductEntity productEntity = productRepository.findOne(dto.getProductId());
        if (productEntity == null) {
            throw new NotFoundException(1, "Bunday maxsulot mavjud emas!");
        }
        productEntity.setAmountInStore(dto.getProductAmount());
        productRepository.save(productEntity);
    }

    @Override
    public void removeSliderItem(Long imageId) {
        sliderImageRepository.delete(imageId);
    }

    private SliderImageEntity getSliderImageEntity(ProductEntity productEntity, String filename, String title) {
        SliderImageEntity sliderImageEntity = new SliderImageEntity();
        sliderImageEntity.setUploadDate(new Date());
        sliderImageEntity.setProduct(productEntity);
        sliderImageEntity.setUrl(IMAGE_URL + filename);
        sliderImageEntity.setTitle(title);
        return sliderImageEntity;
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
