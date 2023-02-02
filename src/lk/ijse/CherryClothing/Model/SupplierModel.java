package lk.ijse.CherryClothing.Model;

import lk.ijse.CherryClothing.db.DBConnection;
import lk.ijse.CherryClothing.to.Customer;
import lk.ijse.CherryClothing.to.Supplier;
import lk.ijse.CherryClothing.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {
    public static boolean register(Supplier supplier) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO Supplier VALUES (?,?,?)");
        pstm.setString(1,supplier.getSupId());
        pstm.setString(2,supplier.getName());
        pstm.setString(3,supplier.getContact());


        return pstm.executeUpdate()>0;
    }
    public static Supplier search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM Supplier WHERE sup_id = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new Supplier(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3)


            );
        }
        return null;
    }

    public static ArrayList<String> loadCustomerIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT sup_id FROM Supplier";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }
}
