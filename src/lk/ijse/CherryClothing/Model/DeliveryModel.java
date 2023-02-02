package lk.ijse.CherryClothing.Model;

import lk.ijse.CherryClothing.db.DBConnection;
import lk.ijse.CherryClothing.to.Delivery;
import lk.ijse.CherryClothing.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryModel {
    public static boolean register(Delivery delivery) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Delivery VALUES(?,?)");
        pstm.setString(1, delivery.getId());
        pstm.setString(2, delivery.getLocation());

        return pstm.executeUpdate() > 0;

    }

    public static Delivery search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM Delivery WHERE del_id = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new Delivery(
                    result.getString(1),
                    result.getString(2)
            );
        }
        return null;
    }

    public static ArrayList<String> loadDeliveryIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT del_id FROM Delivery";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }

    public static String generateNextDeliveryId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT del_id FROM Delivery ORDER BY del_id DESC LIMIT 1";
        ResultSet result = CrudUtil.execute(sql);
        if(result.next()){
            String delID = result.getString(1);
            String[] split = delID.split("D");
            int nextId = Integer.parseInt(split[1]);
            return String.format("D%03d",++nextId);

        }

        return "D001";
    }

    /*public static String generateNextDeliveryId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT  del_id FROM delivery ORDER BY del_id DESC LIMIT 1";
        ResultSet result = CrudUtil.execute(sql);

        if (result.next()) {
            return generateNextDeliveryId(result.getString(1));
        }
        return generateNextDeliveryId(result.getString(null));
    }


    public static String generateNextDeliveryId(String currentDeliverId) {
        if (currentDeliverId != null) {
            String[] split = currentDeliverId.split("D00");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "D00" + id;
        }
        return "D000";
    }*/
}

