package lk.ijse.CherryClothing.Model;

import lk.ijse.CherryClothing.to.Order;
import lk.ijse.CherryClothing.util.CrudUtil;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderModel {
    public static boolean save(Order order) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Orders VALUES(? , ? , ? , ? , ?)";
        return CrudUtil.execute(sql, order.getOrderId(), order.getDate(), order.getCustomerId(),order.getPayId(),order.getDeliveryId());
    }
/*
    public static String generateNextOrderId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT  order_id FROM orders ORDER BY order_id DESC LIMIT 1";
        ResultSet result = CrudUtil.execute(sql);

        if (result.next()) {
            return generateNextOrderId(result.getString(1));
        }
        return generateNextOrderId(result.getString(null));
    }

    private static String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("O00");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "O00" + id;
        }
        return "O000";

    }*/

    public static String generateNextOrderId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT order_id FROM Orders ORDER BY order_id DESC LIMIT 1";
        ResultSet result = CrudUtil.execute(sql);
        if(result.next()){
            String orderID = result.getString(1);
            String[] split = orderID.split("O");
            int nextId = Integer.parseInt(split[1]);
            return String.format("O%03d",++nextId);

        }

        return "O001";
    }

}
