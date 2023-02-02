package lk.ijse.CherryClothing.view.tm;

public class CClothesTM implements Comparable<CClothesTM>{
    private String id;
    private String type;
    private String price;
    private String qty;

    public CClothesTM(String id, String type, String qty, String price ) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.qty = qty;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String address) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }




    @Override
    public String toString() {
        return "CClothes{" + "id='" + id + '\'' + ", type='" + type + '\'' + ", price='" + price + '\'' + ", qty=" + qty + '}';
    }

    @Override
    public int compareTo(CClothesTM o) {
        return id.compareTo(o.getId());
    }
}
