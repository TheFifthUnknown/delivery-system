package uz.delivery_system.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderProductEntity.class)
public abstract class OrderProductEntity_ extends uz.delivery_system.entity.base.BaseEntity_ {

	public static volatile SingularAttribute<OrderProductEntity, ProductEntity> product;
	public static volatile SingularAttribute<OrderProductEntity, Integer> countProduct;
	public static volatile SingularAttribute<OrderProductEntity, Long> priceProduct;
	public static volatile SingularAttribute<OrderProductEntity, Boolean> accepted;
	public static volatile SingularAttribute<OrderProductEntity, OrderEntity> order;

}

