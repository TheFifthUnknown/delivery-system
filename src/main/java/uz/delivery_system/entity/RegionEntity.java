package uz.delivery_system.entity;

import org.hibernate.dialect.TypeNames;
import uz.delivery_system.entity.base.BaseEntity;
import uz.delivery_system.utils.TableName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by Nodirbek on 12.07.2017.
 */
@Entity
@Table(name = TableName.REGIONS)
public class RegionEntity extends BaseEntity{

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "definition")
    private String definition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
