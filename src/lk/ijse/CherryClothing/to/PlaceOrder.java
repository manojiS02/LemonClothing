package lk.ijse.CherryClothing.to;

import java.util.ArrayList;

public class PlaceOrder {
    private String customerId;
    private String orderId;
    private String payId;
    private String deliveryId;
    private ArrayList<CartDetail> orderDetails = new ArrayList<>();

    public PlaceOrder() {
    }

    public PlaceOrder(String customerId, String orderId, String payId, String deliveryId, ArrayList<CartDetail> orderDetails) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.payId = payId;
        this.deliveryId =deliveryId;
        this.orderDetails = orderDetails;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    public ArrayList<CartDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<CartDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }


    @Override
    public String toString() {
        return "PlaceOrder{" +
                "customerId='" + customerId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderDetails=" + orderDetails +
                ", payId='" + payId + '\'' +
                ", deliveryId=" + deliveryId +
                '}';
    }
}
