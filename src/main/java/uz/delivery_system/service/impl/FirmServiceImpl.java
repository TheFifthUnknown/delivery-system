package uz.delivery_system.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.delivery_system.dto.firm.CategoryFirmsDTO;
import uz.delivery_system.dto.firm.FirmDetailsDTO;
import uz.delivery_system.dto.firm.FirmRegistrationDTO;
import uz.delivery_system.dto.firm.FirmUpdateDTO;
import uz.delivery_system.entity.FirmEntity;
import uz.delivery_system.entity.ProductEntity;
import uz.delivery_system.entity.UserEntity;
import uz.delivery_system.enums.UserRole;
import uz.delivery_system.exceptions.NotFoundException;
import uz.delivery_system.exceptions.AlreadyExistException;
import uz.delivery_system.repository.FirmRepository;
import uz.delivery_system.repository.UserRepository;
import uz.delivery_system.service.FirmService;
import uz.delivery_system.storage.StorageService;
import uz.delivery_system.utils.SecurityUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Nodirbek on 08.07.2017.
 */
@Service
public class FirmServiceImpl implements FirmService {

    @Autowired
    private FirmRepository firmRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public FirmEntity createFirmWithManager(FirmRegistrationDTO registrationDTO) {
        Optional<UserEntity> user = userRepository.findByUsername(registrationDTO.getUsername());
        if(user.isPresent()){
            throw new AlreadyExistException(2,"Bu login avvaldan mavjud");
        }
        UserEntity userEntity = fetchUserData(registrationDTO);
        FirmEntity firmEntity = fetchFirmData(registrationDTO);
        userEntity.setFirm(firmEntity);
        firmEntity.setManeger(userEntity);
        firmRepository.save(firmEntity);
        userRepository.save(userEntity);
        return firmEntity;
    }

    @Override
    public void updateFirm(FirmUpdateDTO dto) {
        FirmEntity firmEntity = firmRepository.findOne(dto.getFirmId());
        if (firmEntity == null) {
            throw new NotFoundException(3, "Bunday firma topilmadi");
        }
        BeanUtils.copyProperties(dto, firmEntity);
        BeanUtils.copyProperties(dto, firmEntity.getManeger());
        firmRepository.save(firmEntity);
    }

    @Override
    public FirmDetailsDTO getFirmDetails(Long id) {
        FirmEntity firmEntity = firmRepository.findOne(id);
        if (firmEntity == null) {
            throw new NotFoundException(3, "Bunday firma topilmadi");
        }
        return getFirmDetailsAsDTO(firmEntity);
    }

    @Override
    public Page<FirmDetailsDTO> listFirmDetails(Pageable pageable) {
        Page<FirmEntity> firmEntities = firmRepository.findAll(pageable);
        List<FirmDetailsDTO> list = new ArrayList<>();
        firmEntities.forEach(firmEntity -> {
            list.add(getFirmDetailsAsDTO(firmEntity));
        });
        return new PageImpl<>(list);
    }

    @Override
    public List<CategoryFirmsDTO> getFirmsByCategory(Long categoryId) {
        List<FirmEntity> firmEntities = firmRepository.findAll();
        List<CategoryFirmsDTO> list = new ArrayList<>();
        firmEntities.forEach(firmEntity -> {
            int count = 0;
            for (ProductEntity productEntity : firmEntity.getProducts()) {
                if (productEntity.getCategory().getId().equals(categoryId)){
                    count++;
                }
            }
            if(count > 0){
                CategoryFirmsDTO dto = new CategoryFirmsDTO();
                dto.setId(firmEntity.getId());
                dto.setFirmName(firmEntity.getFirmName());
                dto.setProductCount(count);
                dto.setImageUrl(firmEntity.getFirmLogoUrl());
                dto.setDeliveriable(firmEntity.isDeliveriable());
                list.add(dto);
            }
        });
        return list;
    }

    @Override
    @Transactional
    public void deleteFirmWithManager(Long id) {
        FirmEntity firmEntity = firmRepository.findOne(id);
        if(firmEntity == null){
            throw new NotFoundException(2,"Bunday firma topilmadi");
        }
        userRepository.delete(firmEntity.getManeger());
//        firmRepository.delete(firmEntity);
    }

    @Override
    public void blockFirm(Long id, Boolean blocked) {
        FirmEntity firmEntity = firmRepository.findOne(id);
        if(firmEntity == null){
            throw new NotFoundException(2,"Bunday firma topilmadi");
        }
        firmEntity.setBlocked(blocked);
        firmRepository.save(firmEntity);
    }

    @Override
    public void changeFirmLogo(MultipartFile file) {
        UserEntity userEntity = userRepository.findOne(SecurityUtils.getUserId());
        FirmEntity firmEntity = userEntity.getFirm();
        if (firmEntity == null) {
            throw new NotFoundException(1,"Firma logini bilan kiring");
        }
        String filename = storageService.store(file);
        firmEntity.setFirmLogoUrl(IMAGE_URL+filename);
        firmRepository.save(firmEntity);
    }

    private FirmEntity fetchFirmData(FirmRegistrationDTO registrationDTO) {
        FirmEntity firmEntity = new FirmEntity();
        BeanUtils.copyProperties(registrationDTO, firmEntity);
        Date current = new Date();
        firmEntity.setFirmLogoUrl(IMAGE_URL+"default_firm_avatar.png");
        firmEntity.setCreateDate(current);
        firmEntity.setCreateUserId(SecurityUtils.getUserId());
        return firmEntity;
    }

    private UserEntity fetchUserData(FirmRegistrationDTO registrationDTO) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(registrationDTO, userEntity,"password");
        userEntity.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        Date current = new Date();
        userEntity.setCreateDate(current);
        userEntity.setCreateUserId(SecurityUtils.getUserId());
        userEntity.setUserRole(UserRole.FIRM_ADMIN);
        return userEntity;
    }

    private FirmDetailsDTO getFirmDetailsAsDTO(FirmEntity firmEntity) {
        FirmDetailsDTO dto = new FirmDetailsDTO();
        BeanUtils.copyProperties(firmEntity, dto);
        dto.setActive(!firmEntity.getBlocked());
        dto.setFirmId(firmEntity.getId());
        dto.setManagerId(firmEntity.getManeger().getId());
        dto.setManagerName(firmEntity.getManeger().fullName());
        dto.setDeliveriable(firmEntity.isDeliveriable());
        return dto;
    }

    private final String IMAGE_URL = "http://dpx.uz:8080/api/v1/files/";
}
