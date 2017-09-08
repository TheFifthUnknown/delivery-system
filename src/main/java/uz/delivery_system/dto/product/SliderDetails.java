package uz.delivery_system.dto.product;

/**
 * Created by Nodirbek on 08.09.2017.
 */
public class SliderDetails {

    private String imageUrl;
    private String imageTitle;

    public SliderDetails(String imageUrl, String imageTitle) {
        this.imageUrl = imageUrl;
        this.imageTitle = imageTitle;
    }

    public SliderDetails() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }
}
