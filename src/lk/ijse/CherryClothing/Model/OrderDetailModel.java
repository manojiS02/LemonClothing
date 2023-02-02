package lk.ijse.CherryClothing.Model;

import lk.ijse.CherryClothing.to.CartDetail;
import lk.ijse.CherryClothing.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailModel {
    public static boolean saveOrderDetails(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException {
        for (CartDetail cartDetail : cartDetails) {
            if (!save(cartDetail)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(CartDetail cartDetail) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO OrderDetail VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(sql, cartDetail.getOrderId(), cartDetail.getItemId(), cartDetail.getQty(), cartDetail.getUnitPrice());
    }
}
