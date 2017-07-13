package uz.delivery_system.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.delivery_system.dto.firm.FirmDetailsDTO;
import uz.delivery_system.dto.shop.ShopDetailsDTO;
import uz.delivery_system.dto.shop.ShopRegistrationDTO;
import uz.delivery_system.dto.shop.ShopUpdateDTO;
import uz.delivery_system.entity.ShopEntity;

/**
 * Created by Nodirbek on 12.07.2017.
 */

public interface ShopService {

    ShopEntity createShopWithManager(ShopRegistrationDTO registrationDTO);

    void updateShop(ShopUpdateDTO dto);

    ShopDetailsDTO getShopDetails(Long id);

    Page<ShopDetailsDTO> listShopDetails(Pageable pageable);

    void blockShop(Long id);

    void deleteShopWithManager(Long id);
}
