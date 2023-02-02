package lk.ijse.CherryClothing.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.CherryClothing.Model.LoginModel;
import lk.ijse.CherryClothing.to.Login;
import lk.ijse.CherryClothing.util.Navigation;
import lk.ijse.CherryClothing.util.Routes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginFormController {
    public AnchorPane ancLogin;
    public JFXTextField txtUser;
    public JFXTextField txtPassword;
    public Label lblforgot;

    public void OnActionLogin(ActionEvent actionEvent) throws IOException, ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cherrycloths?useSSL=false", "root", "1234");
        PreparedStatement ps = connection.prepareStatement("SELECT password FROM Users WHERE username = ?");

        String username = txtUser.getText();
        String password = txtPassword.getText();

        try {
            Login login = LoginModel.search(username);
            if (login != null) {
                if (txtUser.getText().equals("Admin") && txtPassword.getText().equals("1234")) {
                    Navigation.navigate(Routes.ADMIN, ancLogin);

                } else if (txtPassword.getText().equals(login.getPassword())) {
                    Navigation.navigate(Routes.PLACEORDER, ancLogin);

                } else {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Incorrect Password");
                    alert.setHeaderText("Invalid Username or Password !");
                    alert.setContentText("Please Check Your Username and Password , and Try again !");
                    alert.showAndWait();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public void OnActiontxtPassword(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        if (txtUser.getText().equals("Admin") && txtPassword.getText().equals("1234")){
            Navigation.navigate(Routes.ADMIN, ancLogin);
        } else if (txtUser.getText().equals("Cashier") && txtPassword.getText().equals("1234")) {
            Navigation.navigate(Routes.PLACEORDER, ancLogin);

        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Incorrect Password");
            alert.setHeaderText("Invalid Username or Password !");
            alert.setContentText("Please Check Your Username and Password , and Try again !");
            alert.showAndWait();
        }
    }
}
