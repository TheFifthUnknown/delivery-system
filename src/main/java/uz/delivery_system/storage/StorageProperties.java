package uz.delivery_system.storage;

import org.springframework.boot.ApplicationHome;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;

@ConfigurationProperties("storage")
public class StorageProperties {

    private String location = System.getProperty("user.dir") + "\\uploads\\";

    public StorageProperties() {
        ApplicationHome home = new ApplicationHome(this.getClass());
        File jarFile = home.getSource();
        File jarDir = home.getDir();
        location = jarDir+"/uploads";
//        File uploadDir = new File(jarDir, "uploads");
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
