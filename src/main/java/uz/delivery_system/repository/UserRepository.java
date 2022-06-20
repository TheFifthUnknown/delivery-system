package uz.delivery_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import uz.delivery_system.entity.UserEntity;
import uz.delivery_system.enums.UserRole;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity,Long>, JpaSpecificationExecutor<UserEntity> {

    Optional<UserEntity> findByUsernameAndActive(String username,Boolean active);

    Optional<UserEntity> findByUsername(String username);


}
