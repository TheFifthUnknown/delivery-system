package uz.delivery_system.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.delivery_system.dto.firm.FirmDetailsDTO;
import uz.delivery_system.dto.firm.FirmRegistrationDTO;
import uz.delivery_system.dto.firm.FirmUpdateDTO;
import uz.delivery_system.entity.FirmEntity;

/**
 * Created by Nodirbek on 07.07.2017.
 */
public interface FirmService {

    FirmEntity createFirmWithManager(FirmRegistrationDTO registrationDTO);

    void deleteFirmWithManager(Long id);

    void updateFirm(FirmUpdateDTO dto);

    FirmDetailsDTO getFirmDetails(Long id);

    void blockFirm(Long id);

    Page<FirmDetailsDTO> listFirmDetails(Pageable pageable);
}
