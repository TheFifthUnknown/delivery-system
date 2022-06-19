package uz.delivery_system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uz.delivery_system.enums.UserRole;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserEntity.class)
public abstract class UserEntity_ extends uz.delivery_system.entity.base.UpdatableBaseEntity_ {

	public static volatile SingularAttribute<UserEntity, String> firstname;
	public static volatile SingularAttribute<UserEntity, String> serialNum;
	public static volatile SingularAttribute<UserEntity, ShopEntity> shop;
	public static volatile SingularAttribute<UserEntity, Boolean> active;
	public static volatile SingularAttribute<UserEntity, String> language;
	public static volatile SingularAttribute<UserEntity, String> lastname;
	public static volatile SingularAttribute<UserEntity, FirmEntity> firm;
	public static volatile SingularAttribute<UserEntity, String> password;
	public static volatile SingularAttribute<UserEntity, String> imieCode;
	public static volatile SingularAttribute<UserEntity, String> phone;
	public static volatile SingularAttribute<UserEntity, Date> lastPasswordChange;
	public static volatile SingularAttribute<UserEntity, UserRole> userRole;
	public static volatile SingularAttribute<UserEntity, String> username;

}

