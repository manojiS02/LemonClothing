package lk.ijse.CherryClothing.Model;

import lk.ijse.CherryClothing.Controller.CustomerFormController;
import lk.ijse.CherryClothing.db.DBConnection;
import lk.ijse.CherryClothing.to.Customer;
import lk.ijse.CherryClothing.util.CrudUtil;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CustomerModel {
    public static boolean register(Customer customer) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Customer VALUES(?,?,?,?)");
        pstm.setString(1,customer.getId());
        pstm.setString(2,customer.getName());
        pstm.setString(3,customer.getAddress());
        pstm.setString(4,customer.getContact());


        return pstm.executeUpdate()>0 ;

    }
    public static Customer search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM Customer WHERE cus_id = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new Customer(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
        }
        return null;
    }

    public static ArrayList<String> loadCustomerIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT cus_id FROM Customer";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }
}




