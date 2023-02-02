package lk.ijse.CherryClothing.Model;

import lk.ijse.CherryClothing.db.DBConnection;
import lk.ijse.CherryClothing.to.CClothes;
import lk.ijse.CherryClothing.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CClothesModel {
    public static boolean register(CClothes cClothes) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Item VALUES(?,?,?,?)");
        pstm.setString(1, cClothes.getId());
        pstm.setString(2, cClothes.getType());
        pstm.setString(4, cClothes.getPrice());
        pstm.setString(3, cClothes.getQty());


        return pstm.executeUpdate() > 0;

    }
    public static CClothes search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM Item WHERE item_id = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new CClothes(
                    result.getString(1),
                    result.getString(2),
                    result.getString(4),
                    result.getString(3)

            );
        }
        return null;
    }
}
