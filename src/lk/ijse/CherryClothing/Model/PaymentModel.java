package lk.ijse.CherryClothing.Model;

import lk.ijse.CherryClothing.db.DBConnection;
import lk.ijse.CherryClothing.to.Payment;
import lk.ijse.CherryClothing.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentModel {
    public static boolean register(Payment payment) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Payment VALUES(?,?,?,?)");
        pstm.setString(1,payment.getId());
        pstm.setString(2,payment.getDate());
        pstm.setString(3,payment.getTime());
        pstm.setString(4,payment.getMethod());



        return pstm.executeUpdate()>0 ;

    }
    public static Payment search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM Payment WHERE pay_id = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new Payment(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)

            );
        }
        return null;
    }

    public static ArrayList<String> loadNextPayIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT pay_id FROM Payment";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }

    /*public static String generateNextPayId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT  pay_id FROM payment ORDER BY pay_id DESC LIMIT 1";
        ResultSet result = CrudUtil.execute(sql);

        if (result.next()) {
            return generateNextPayId(result.getString(1));
        }
        return generateNextPayId(result.getString(null));
    }*/


    public static String generateNextPayId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT pay_id FROM Payment ORDER BY pay_id DESC LIMIT 1";
        ResultSet result = CrudUtil.execute(sql);
        if(result.next()){
            String orderID = result.getString(1);
            String[] split = orderID.split("P");
            int nextId = Integer.parseInt(split[1]);
            return String.format("P%03d",++nextId);

        }

        return "P001";

    }


}
