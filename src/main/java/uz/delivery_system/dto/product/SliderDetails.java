package uz.delivery_system.dto.product;
public class SliderDetails {

    private Long imageId;
    private String imageUrl;
    private String imageTitle;

    public SliderDetails(Long imageId, String imageUrl, String imageTitle) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
        this.imageTitle = imageTitle;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
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
