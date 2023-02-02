package lk.ijse.CherryClothing.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.CherryClothing.Model.CustomerModel;
import lk.ijse.CherryClothing.Model.PaymentModel;
import lk.ijse.CherryClothing.db.DBConnection;
import lk.ijse.CherryClothing.to.Customer;
import lk.ijse.CherryClothing.to.Payment;
import lk.ijse.CherryClothing.util.Navigation;
import lk.ijse.CherryClothing.util.RegexUtil;
import lk.ijse.CherryClothing.util.Routes;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PaymentFormController {
    public AnchorPane ancPay;
    public JFXTextField txtId;
    public JFXTextField txtTime;
    public JFXTextField txtDate;
    public JFXRadioButton rbtnCard;
    public JFXRadioButton rbtnCash;
    public JFXButton btnAdd;

    String getPayMethod() {
        if (rbtnCash.isSelected()) {
            return "cash";
        } else if (rbtnCard.isSelected()) {
            return "Card";
        } else {
            return null;
        }
    }

    public void OnActionCustomer(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER, ancPay);
    }

    public void OnActionDelivery(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DELIVER, ancPay);
    }

    public void OnActionAdd(ActionEvent actionEvent) {
        Payment payment = new Payment(txtId.getText(), txtTime.getText(), txtDate.getText(),getPayMethod());
        try {
            boolean isAdded = PaymentModel.register(payment);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Happend!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public void OnActionUpdate(ActionEvent actionEvent) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pre = connection.prepareStatement("UPDATE Payment SET  time = ?, date = ?, method = ?  where pay_id = ?");

            pre.setObject(1, txtId.getText());
            pre.setObject(2, txtTime.getText());
            pre.setObject(3, txtDate.getText());
            pre.setObject(4, getPayMethod());



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
            String sql = "DELETE FROM Payment WHERE pay_id=?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setObject(1, txtId.getText());
            int executeUpdate = pre.executeUpdate();

            if (executeUpdate > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, " Deleted!").show();
                txtId.clear();
                txtDate.clear();
                txtTime.clear();
            }
        } catch (Exception e) {
        }
        //clearData();
    }


    public void OnActionSearch(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            Payment payment = PaymentModel.search(id);
            if (payment != null) {
                fillData(payment);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    private void fillData(Payment payment) {
        txtId.setText(payment.getId());
        txtTime.setText(payment.getTime());
        txtDate.setText(payment.getDate());
        getPayMethod();

    }

    public void OnActionPay(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PAYMENT, ancPay);
    }

    public void OnActionPlace(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PLACEORDER, ancPay);
    }

    public void OnActionOut(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, ancPay);
    }

    public void OnActionCClothes(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CCLOTHES, ancPay);
    }

    public void OnActionCDash(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CASHIER, ancPay);
    }

    public void OnAtionCash(ActionEvent actionEvent) {

    }

    public void timeK(KeyEvent keyEvent) {
        /*if (RegexUtil.regex(txtTime.getText(),"/(?:[01]\\d|2[0-3]):(?:[0-5]\\d):(?:[0-5]\\d)/")){
            btnAdd.setDisable(false);
            txtTime.setFocusColor(Paint.valueOf("blue"));
        }else {
            btnAdd.setDisable(true);
            txtTime.setFocusColor(Paint.valueOf("red"));

        }*/

    }


    public void dateK(KeyEvent keyEvent) {
        /*if (RegexUtil.regex(txtDate.getText(),"^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")){
            btnAdd.setDisable(false);
            txtDate.setFocusColor(Paint.valueOf("blue"));
        }else {
            btnAdd.setDisable(true);
            txtDate.setFocusColor(Paint.valueOf("red"));

        }*/

    }
    }



