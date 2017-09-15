package uz.delivery_system.dto.product;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * Created by Nodirbek on 15.09.2017.
 */
public class ProductSliderDTO {

    @NotEmpty
    private MultipartFile file;

    @NotNull
    private String title;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
