package lk.ijse.CherryClothing.Model;

import lk.ijse.CherryClothing.to.Login;
import lk.ijse.CherryClothing.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    public static Login search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT password FROM User WHERE userName = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new Login(
                    result.getString(1)
            );
        }
        return null;
    }
}
