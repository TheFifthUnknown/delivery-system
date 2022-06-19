package uz.delivery_system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SliderImageEntity.class)
public abstract class SliderImageEntity_ extends uz.delivery_system.entity.base.BaseEntity_ {

	public static volatile SingularAttribute<SliderImageEntity, ProductEntity> product;
	public static volatile SingularAttribute<SliderImageEntity, Date> uploadDate;
	public static volatile SingularAttribute<SliderImageEntity, String> title;
	public static volatile SingularAttribute<SliderImageEntity, String> url;

}

