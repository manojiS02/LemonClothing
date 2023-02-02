package lk.ijse.CherryClothing.to;

import java.time.LocalDate;

public class Order {

        private String orderId;
        private LocalDate date;
        private String customerId;
        private String payId;
        private String deliveryId;

        public Order() {
        }

        public Order(String orderId, LocalDate date, String customerId , String payId , String deliveryId) {
            this.orderId = orderId;
            this.date = date;
            this.customerId = customerId;
            this.payId = payId;
            this.deliveryId = deliveryId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {

            this.customerId = customerId;
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
            return "Order{" +
                    "orderId='" + orderId + '\'' +
                    ", date='" + date + '\'' +
                    ", customerId='" + customerId + '\'' +
                    ", payId='" + payId + '\'' +
                    ", deliveryId='" + deliveryId + '\'' +
                    '}';
        }
}
