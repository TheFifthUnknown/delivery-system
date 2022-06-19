package uz.delivery_system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderEntity.class)
public abstract class OrderEntity_ extends uz.delivery_system.entity.base.BaseEntity_ {

	public static volatile SingularAttribute<OrderEntity, ShopEntity> shopEntity;
	public static volatile SingularAttribute<OrderEntity, Long> orderedProductsCost;
	public static volatile SingularAttribute<OrderEntity, FirmEntity> firmEntity;
	public static volatile SingularAttribute<OrderEntity, Integer> orderedProductsCount;
	public static volatile ListAttribute<OrderEntity, OrderProductEntity> orderProducts;
	public static volatile SingularAttribute<OrderEntity, Date> deliverDate;
	public static volatile SingularAttribute<OrderEntity, Date> orderedDate;
	public static volatile SingularAttribute<OrderEntity, Long> registerNumber;
	public static volatile SingularAttribute<OrderEntity, Short> status;
	public static volatile SingularAttribute<OrderEntity, Integer> paymentType;

}

