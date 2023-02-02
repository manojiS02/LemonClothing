package lk.ijse.CherryClothing.view.tm;

import javafx.scene.control.Button;

public class PlaceOrderTM {
    private String id;
    private String type;
    private int qty;
    private double unitPrice;
    private double total;
    private Button btnDelete;

    public PlaceOrderTM() {
    }

    public PlaceOrderTM(String id, String type, int qty, double unitPrice, double total, Button btnDelete) {
        this.id = id;
        this.type = type;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
        this.btnDelete = btnDelete;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "PlaceOrderTM{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", total=" + total +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
