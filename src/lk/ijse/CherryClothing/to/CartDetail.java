package lk.ijse.CherryClothing.to;

public class CartDetail {
    private String orderId;
    private String itemId;
    private int qty;
    private String type;
    private double unitPrice;

    public CartDetail() {
    }

    public CartDetail(String orderId, String itemIdd, int qty, String type, double unitPrice) {
        this.orderId = orderId;
        this.itemId = itemIdd;
        this.qty = qty;
        this.type = type;
        this.unitPrice = unitPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getType() {
        return type;
    }

    public void setType(String description) {
        this.type = type;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "CartDetail{" +
                "orderId='" + orderId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", qty=" + qty +
                ", type='" + type + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
