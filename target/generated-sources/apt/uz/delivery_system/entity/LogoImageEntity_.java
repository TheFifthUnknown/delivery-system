package uz.delivery_system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LogoImageEntity.class)
public abstract class LogoImageEntity_ extends uz.delivery_system.entity.base.BaseEntity_ {

	public static volatile SingularAttribute<LogoImageEntity, ProductEntity> product;
	public static volatile SingularAttribute<LogoImageEntity, Date> uploadDate;
	public static volatile SingularAttribute<LogoImageEntity, String> url;

}

