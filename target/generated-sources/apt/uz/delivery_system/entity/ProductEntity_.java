package uz.delivery_system.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductEntity.class)
public abstract class ProductEntity_ extends uz.delivery_system.entity.base.BaseEntity_ {

	public static volatile SingularAttribute<ProductEntity, String> unitOfMeasurement;
	public static volatile ListAttribute<ProductEntity, SliderImageEntity> slides;
	public static volatile SingularAttribute<ProductEntity, String> saleType;
	public static volatile SingularAttribute<ProductEntity, String> productBrandName;
	public static volatile SingularAttribute<ProductEntity, LogoImageEntity> productLogo;
	public static volatile SingularAttribute<ProductEntity, Integer> amountInOrder;
	public static volatile SingularAttribute<ProductEntity, String> productName;
	public static volatile SingularAttribute<ProductEntity, String> productInfo;
	public static volatile SingularAttribute<ProductEntity, FirmEntity> firm;
	public static volatile SingularAttribute<ProductEntity, Long> firmId;
	public static volatile SingularAttribute<ProductEntity, Integer> productCode;
	public static volatile SingularAttribute<ProductEntity, Integer> amountInStore;
	public static volatile SingularAttribute<ProductEntity, Integer> amountInPending;
	public static volatile SingularAttribute<ProductEntity, CategoryEntity> category;
	public static volatile SingularAttribute<ProductEntity, Long> productPrice;
	public static volatile SingularAttribute<ProductEntity, Long> categoryId;

}

