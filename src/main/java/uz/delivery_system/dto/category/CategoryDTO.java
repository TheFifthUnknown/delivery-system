package uz.delivery_system.dto.category;

import io.swagger.annotations.ApiParam;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Nodirbek on 13.07.2017.
 */
public class CategoryDTO {

    private Long id;

    @NotNull
    @Size(min = 4, max = 50)
    private String categoryName;

    private String categoryDefinition;

    private String categoryImageUrl;

    private Long categoryParentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
