package lk.ijse.CherryClothing.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.CherryClothing.Model.CClothesModel;
import lk.ijse.CherryClothing.Model.CustomerModel;
import lk.ijse.CherryClothing.Model.DeliveryModel;
import lk.ijse.CherryClothing.db.DBConnection;
import lk.ijse.CherryClothing.to.CClothes;
import lk.ijse.CherryClothing.to.Customer;
import lk.ijse.CherryClothing.to.Delivery;
import lk.ijse.CherryClothing.util.Navigation;
import lk.ijse.CherryClothing.util.RegexUtil;
import lk.ijse.CherryClothing.util.Routes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeliveryFormController {
    public AnchorPane ancDeliver;
    public JFXTextField txtId;
    public JFXTextField txtLocation;
    public JFXButton btnAdd;

    public void OnActionCustomer(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER, ancDeliver);
    }

    public void OnActionDelivery(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DELIVER, ancDeliver);
    }

    public void OnActionAdd(ActionEvent actionEvent) {

        Delivery delivery = new Delivery(txtId.getText(),txtLocation.getText());
        try {
            boolean isAdded = DeliveryModel.register(delivery);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Delivery Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Happend!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e);
        }
    }

    public void OnActionUpdate(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtLocation.getText();


        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pre = connection.prepareStatement("UPDATE Delivery SET  location = ?  where del_id = ?");

            pre.setObject(1, txtLocation.getText());
            pre.setObject(2, txtId.getText());

            int executeUpdate = pre.executeUpdate();

            if (executeUpdate > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, " Updated!").show();

                //  JOptionPane.showMessageDialog(rootPane, "update Items");
            }

        } catch (Exception e) {

            System.out.println(e);
        }
    }

    public void OnActionDelete(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "DELETE FROM Delivery WHERE del_id=?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setObject(1, txtId.getText());
            int executeUpdate = pre.executeUpdate();

            if (executeUpdate > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, " Deleted!").show();
                txtId.clear();
                txtLocation.clear();


            }
        } catch (Exception e) {
        }
        //clearData();
    }

    public void OnActionSearch(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            Delivery delivery = DeliveryModel.search(id);
            if (delivery != null) {
                fillData(delivery);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    private void fillData(Delivery delivery) {
        txtId.setText(delivery.getId());
        txtLocation.setText(delivery.getLocation());

    }

    public void OnActionPay(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PAYMENT, ancDeliver);
    }

    public void OnActionPlace(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PLACEORDER, ancDeliver);
    }

    public void OnActionOut(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, ancDeliver);
    }

    public void OnActionCClothes(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CCLOTHES, ancDeliver);
    }

    public void OnActionCDash(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CASHIER, ancDeliver);
    }

    public void addressK(KeyEvent keyEvent) {
        if (RegexUtil.regex(txtLocation.getText(),"\\b([a-z]|[A-Z])+")){
            btnAdd.setDisable(false);
            txtLocation.setFocusColor(Paint.valueOf("blue"));
        }else {
            btnAdd.setDisable(true);
            txtLocation.setFocusColor(Paint.valueOf("red"));

        }
    }
}


