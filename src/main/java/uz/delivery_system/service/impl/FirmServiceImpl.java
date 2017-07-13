package uz.delivery_system.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.delivery_system.dto.firm.FirmDetailsDTO;
import uz.delivery_system.dto.firm.FirmRegistrationDTO;
import uz.delivery_system.dto.firm.FirmUpdateDTO;
import uz.delivery_system.entity.FirmEntity;
import uz.delivery_system.entity.UserEntity;
import uz.delivery_system.enums.UserRole;
import uz.delivery_system.exceptions.NotFoundException;
import uz.delivery_system.exceptions.UserAlreadyExistException;
import uz.delivery_system.repository.FirmRepository;
import uz.delivery_system.repository.UserRepository;
import uz.delivery_system.service.FirmService;
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
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public FirmEntity createFirmWithManager(FirmRegistrationDTO registrationDTO) {
        Optional<UserEntity> user = userRepository.findByUsername(registrationDTO.getUsername());
        if(user.isPresent()){
            throw new UserAlreadyExistException(2,"Bu login avvaldan mavjud");
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
    public void blockFirm(Long id) {
        FirmEntity firmEntity = firmRepository.findOne(id);
        if(firmEntity == null){
            throw new NotFoundException(2,"Bunday firma topilmadi");
        }
        firmEntity.setBlocked(Boolean.TRUE);
        firmRepository.save(firmEntity);
    }

    private FirmEntity fetchFirmData(FirmRegistrationDTO registrationDTO) {
        FirmEntity firmEntity = new FirmEntity();
        BeanUtils.copyProperties(registrationDTO, firmEntity);
        Date current = new Date();
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
        return dto;
    }
}
