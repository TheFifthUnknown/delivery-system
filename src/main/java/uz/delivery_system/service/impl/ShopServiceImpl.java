package uz.delivery_system.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.delivery_system.dto.shop.ShopDetailsDTO;
import uz.delivery_system.dto.shop.ShopRegistrationDTO;
import uz.delivery_system.entity.RegionEntity;
import uz.delivery_system.entity.ShopEntity;
import uz.delivery_system.entity.UserEntity;
import uz.delivery_system.enums.UserRole;
import uz.delivery_system.exceptions.NotFoundException;
import uz.delivery_system.exceptions.AlreadyExistException;
import uz.delivery_system.repository.RegionRepository;
import uz.delivery_system.repository.ShopRepository;
import uz.delivery_system.repository.UserRepository;
import uz.delivery_system.service.CategoryService;
import uz.delivery_system.service.ShopService;
import uz.delivery_system.utils.SecurityUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ShopEntity createShopWithManager(ShopRegistrationDTO registrationDTO) {
        Optional<UserEntity> user = userRepository.findByUsername(registrationDTO.getUsername());
        if (user.isPresent()) {
            throw new AlreadyExistException(2, "This login is available from the beginning");
        }
        Optional<ShopEntity> shop = shopRepository.findByShopINN(registrationDTO.getShopINN());
        if (shop.isPresent()) {
            throw new AlreadyExistException(2,"Such an INN is available from the beginning!");
        }
        UserEntity userEntity = fetchUserData(registrationDTO);
        ShopEntity shopEntity = fetchShopData(registrationDTO);
        RegionEntity regionEntity = regionRepository.findOne(registrationDTO.getShopRegionId());
        if (regionEntity == null) {
            throw new NotFoundException(1,"Such a region does not exist!");
        }
        userEntity.setShop(shopEntity);
        shopEntity.setManeger(userEntity);
        shopEntity.setRegion(regionEntity);
        userRepository.save(userEntity);
        shopRepository.save(shopEntity);
        return shopEntity;
    }

    @Override
    public void updateShop(CategoryService.ShopUpdateDTO dto) {
        ShopEntity shopEntity = shopRepository.findOne(dto.getShopId());
        if (shopEntity == null) {
            throw new NotFoundException(3, "Such a store could not be found");
        }
        Long count = shopRepository.countByShopINN(dto.getShopINN());
        if (count > 1) {
            throw new AlreadyExistException(2,"Such an INN is available from the beginning!");
        }
        RegionEntity regionEntity = regionRepository.findOne(dto.getShopRegionId());
        if (regionEntity == null) {
            throw new NotFoundException(1,"Such a region does not exist!");
        }
        BeanUtils.copyProperties(dto, shopEntity);
        BeanUtils.copyProperties(dto, shopEntity.getManeger());
        shopEntity.setRegion(regionEntity);
        shopRepository.save(shopEntity);
    }

    @Override
    public ShopDetailsDTO getShopDetails(Long id) {
        ShopEntity shopEntity = shopRepository.findOne(id);
        if (shopEntity == null) {
            throw new NotFoundException(3, "Such a store could not be found");
        }
        return getShopDetailsAsDTO(shopEntity);
    }

    @Override
    public Page<ShopDetailsDTO> listShopDetails(Pageable pageable) {
        Page<ShopEntity> firmEntities = shopRepository.findAll(pageable);
        List<ShopDetailsDTO> list = new ArrayList<>();
        firmEntities.forEach(firmEntity -> {
            list.add(getShopDetailsAsDTO(firmEntity));
        });
        return new PageImpl<>(list);
    }

    @Override
    public void blockShop(Long id, Boolean blocked) {
        ShopEntity shopEntity = shopRepository.findOne(id);
        if (shopEntity == null) {
            throw new NotFoundException(2, "Such a firm could not be found");
        }
        shopEntity.setBlocked(blocked);
        shopRepository.save(shopEntity);
    }

    @Override
    public void deleteShopWithManager(Long id) {
        ShopEntity shopEntity = shopRepository.findOne(id);
        if (shopEntity == null) {
            throw new NotFoundException(2, "Such a firm could not be found");
        }
        userRepository.delete(shopEntity.getManeger());
    }

    public ShopEntity fetchShopData(ShopRegistrationDTO registrationDTO) {
        ShopEntity shopEntity = new ShopEntity();
        BeanUtils.copyProperties(registrationDTO, shopEntity);
        Date current = new Date();
        shopEntity.setCreateDate(current);
        shopEntity.setCreateUserId(42L);
        return shopEntity;
    }

    public UserEntity fetchUserData(ShopRegistrationDTO registrationDTO) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(registrationDTO, userEntity, "password");
        userEntity.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        Date current = new Date();
        userEntity.setCreateDate(current);
        userEntity.setCreateUserId(SecurityUtils.getUserId());
        userEntity.setUserRole(UserRole.SHOP_MANAGER);
        return userEntity;
    }

    public ShopDetailsDTO getShopDetailsAsDTO(ShopEntity shopEntity) {
        ShopDetailsDTO dto = new ShopDetailsDTO();
        BeanUtils.copyProperties(shopEntity, dto);
        dto.setActive(!shopEntity.getBlocked());
        dto.setShopId(shopEntity.getId());
        dto.setManagerId(shopEntity.getManeger().getId());
        dto.setManagerName(shopEntity.getManeger().fullName());
        dto.setShopRegionName(shopEntity.getRegion().getName());
        return dto;
    }
}