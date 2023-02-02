package lk.ijse.CherryClothing.Model;

import lk.ijse.CherryClothing.db.DBConnection;
import lk.ijse.CherryClothing.to.Order;
import lk.ijse.CherryClothing.to.PlaceOrder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceOrderModel {
    public static boolean placeOrder(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            System.out.println(placeOrder.toString());
            boolean isOrderAdded = OrderModel.save(new Order(placeOrder.getOrderId(), LocalDate.now(), placeOrder.getCustomerId(),placeOrder.getPayId(),placeOrder.getDeliveryId()));
            if (isOrderAdded) {
                boolean isUpdated = ItemModel.updateQty(placeOrder.getOrderDetails());
                if (isUpdated) {
                    boolean isOrderDetailAdded = OrderDetailModel.saveOrderDetails(placeOrder.getOrderDetails());
                    if (isOrderDetailAdded) {
                        System.out.println("Before Committed");
                        DBConnection.getInstance().getConnection().commit();
                        System.out.println("After Committed");
                        return true;
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
}
