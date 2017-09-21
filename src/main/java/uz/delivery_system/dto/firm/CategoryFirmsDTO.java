package uz.delivery_system.dto.firm;

/**
 * Created by Nodirbek on 29.08.2017.
 */
public class CategoryFirmsDTO {

    private long id;

    private String firmName;

    private int productCount;

    private String imageUrl;

    private boolean deliveriable;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isDeliveriable() {
        return deliveriable;
    }

    public void setDeliveriable(boolean deliveriable) {
        this.deliveriable = deliveriable;
    }
}
