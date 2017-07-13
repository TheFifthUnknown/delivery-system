package uz.delivery_system.entity;

import uz.delivery_system.entity.base.UpdatableBaseEntity;
import uz.delivery_system.utils.TableName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Nodirbek on 13.07.2017.
 */
@Entity
@Table(name = TableName.CATEGORY)
public class CategoryEntity extends UpdatableBaseEntity{

    @Column(name = "name", nullable = false)
    private String categoryName;

    @Column(name = "definition")
    private String categoryDefinition;

    @Column(name = "imageUrl")
    private String categoryImageUrl;

    @Column(name = "parentId", columnDefinition = "bigint default 0")
    private Long categoryParentId=0L;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDefinition() {
        return categoryDefinition;
    }

    public void setCategoryDefinition(String categoryDefinition) {
        this.categoryDefinition = categoryDefinition;
    }

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }

    public Long getCategoryParentId() {
        return categoryParentId;
    }

    public void setCategoryParentId(Long categoryParentId) {
        this.categoryParentId = categoryParentId;
    }

}
