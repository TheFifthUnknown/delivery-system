package uz.delivery_system.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.delivery_system.dto.shop.ShopDetailsDTO;
import uz.delivery_system.dto.shop.ShopRegistrationDTO;
import uz.delivery_system.entity.ShopEntity;

public interface ShopService {

    ShopEntity createShopWithManager(ShopRegistrationDTO registrationDTO);

    void updateShop(CategoryService.ShopUpdateDTO dto);

    ShopDetailsDTO getShopDetails(Long id);

    Page<ShopDetailsDTO> listShopDetails(Pageable pageable);

    void blockShop(Long id, Boolean blocked);

    void deleteShopWithManager(Long id);
}
