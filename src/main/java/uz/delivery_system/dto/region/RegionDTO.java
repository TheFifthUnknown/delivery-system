package uz.delivery_system.dto.region;

import javax.validation.constraints.NotNull;
public class RegionDTO {

    private Long id;

    @NotNull
    private String name;

    private String definition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
