package lk.ijse.CherryClothing.Model;

import lk.ijse.CherryClothing.db.DBConnection;
import lk.ijse.CherryClothing.to.Employee;
import lk.ijse.CherryClothing.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeModel {
    public static boolean register(Employee employee) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Employee VALUES(?,?,?,?)");
        pstm.setString(1,employee.getId());
        pstm.setString(2,employee.getName());
        pstm.setString(3,employee.getAddress());
        pstm.setDouble(4,employee.getSalary());




        return pstm.executeUpdate()>0 ;

    }
    public static Employee search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM Employee WHERE empy_id = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new Employee(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getDouble(4)

            );
        }
        return null;
    }

    public static ArrayList<String> loadEmployeeIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT empy_id FROM Employee";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }
}
