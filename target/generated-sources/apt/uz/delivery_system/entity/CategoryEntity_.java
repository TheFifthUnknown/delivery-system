package uz.delivery_system.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CategoryEntity.class)
public abstract class CategoryEntity_ extends uz.delivery_system.entity.base.UpdatableBaseEntity_ {

	public static volatile SingularAttribute<CategoryEntity, Long> categoryParentId;
	public static volatile ListAttribute<CategoryEntity, ProductEntity> productEntities;
	public static volatile SingularAttribute<CategoryEntity, String> categoryImageUrl;
	public static volatile SingularAttribute<CategoryEntity, String> categoryName;
	public static volatile SingularAttribute<CategoryEntity, String> categoryDefinition;

}

