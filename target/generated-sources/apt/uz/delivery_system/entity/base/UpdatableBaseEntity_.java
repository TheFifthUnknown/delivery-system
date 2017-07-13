package uz.delivery_system.entity.base;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uz.delivery_system.entity.UserEntity;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UpdatableBaseEntity.class)
public abstract class UpdatableBaseEntity_ extends uz.delivery_system.entity.base.BaseEntity_ {

	public static volatile SingularAttribute<UpdatableBaseEntity, Long> createUserId;
	public static volatile SingularAttribute<UpdatableBaseEntity, Date> updateDate;
	public static volatile SingularAttribute<UpdatableBaseEntity, Long> updateUserId;
	public static volatile SingularAttribute<UpdatableBaseEntity, UserEntity> updateUser;
	public static volatile SingularAttribute<UpdatableBaseEntity, UserEntity> createUser;
	public static volatile SingularAttribute<UpdatableBaseEntity, Date> createDate;

}

