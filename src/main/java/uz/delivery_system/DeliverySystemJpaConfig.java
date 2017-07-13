package uz.delivery_system;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Dilshod Tadjiev.
 */
@Configuration
@EnableJpaRepositories(basePackages = "uz.delivery_system.repository")
@EnableTransactionManagement
public class DeliverySystemJpaConfig {

}
