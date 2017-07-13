package uz.delivery_system.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FirmEntity.class)
public abstract class FirmEntity_ extends uz.delivery_system.entity.base.UpdatableBaseEntity_ {

	public static volatile SingularAttribute<FirmEntity, UserEntity> maneger;
	public static volatile SingularAttribute<FirmEntity, String> firmContact;
	public static volatile SingularAttribute<FirmEntity, String> firmName;
	public static volatile SingularAttribute<FirmEntity, String> firmEmail;
	public static volatile SingularAttribute<FirmEntity, String> firmExtraContact;
	public static volatile SingularAttribute<FirmEntity, String> firmAddress;

}

