package uz.delivery_system.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ShopEntity.class)
public abstract class ShopEntity_ extends uz.delivery_system.entity.base.UpdatableBaseEntity_ {

	public static volatile SingularAttribute<ShopEntity, String> shopActivity;
	public static volatile SingularAttribute<ShopEntity, UserEntity> maneger;
	public static volatile SingularAttribute<ShopEntity, String> shopINN;
	public static volatile SingularAttribute<ShopEntity, String> shopName;
	public static volatile SingularAttribute<ShopEntity, String> shopAddress;
	public static volatile SingularAttribute<ShopEntity, String> shopEmail;
	public static volatile SingularAttribute<ShopEntity, String> shopMFO;
	public static volatile SingularAttribute<ShopEntity, Boolean> blocked;
	public static volatile SingularAttribute<ShopEntity, String> shopAccountNumber;
	public static volatile SingularAttribute<ShopEntity, Long> shopRegionId;
	public static volatile SingularAttribute<ShopEntity, String> shopExtraContact;
	public static volatile SingularAttribute<ShopEntity, String> shopOwnerPassport;
	public static volatile SingularAttribute<ShopEntity, String> shopOrienter;
	public static volatile SingularAttribute<ShopEntity, Long> dpxContractNumber;
	public static volatile SingularAttribute<ShopEntity, String> shopContact;
	public static volatile SingularAttribute<ShopEntity, Integer> shopType;
	public static volatile SingularAttribute<ShopEntity, RegionEntity> region;

}

