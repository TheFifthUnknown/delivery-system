package uz.delivery_system.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    public StorageProperties() {

    }

    /**
     * Folder location for storing files
     */

    private String location = "/images/category";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
