package lk.ijse.CherryClothing.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.CherryClothing.Model.SupplierModel;
import lk.ijse.CherryClothing.db.DBConnection;
import lk.ijse.CherryClothing.to.Supplier;
import lk.ijse.CherryClothing.util.Navigation;
import lk.ijse.CherryClothing.util.RegexUtil;
import lk.ijse.CherryClothing.util.Routes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SupplierFormController {
    public AnchorPane ancSupplier;
    public JFXTextField txtName;
    public JFXTextField txtContact;
    public JFXTextField txtId;
    public JFXButton btnAdd;

    public void OnActionEmployee(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEE, ancSupplier);
    }

    public void OnActionSupplier(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIER, ancSupplier);
    }

    public void OnActionDash(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN, ancSupplier);
    }

    public void OnActionAdd(ActionEvent actionEvent) {
        Supplier supplier = new Supplier(txtId.getText(),txtName.getText(),txtContact.getText());
        try {
            boolean isAdded = SupplierModel.register(supplier);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Added!").show();
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
            PreparedStatement pre = connection.prepareStatement("UPDATE Supplier SET  sup_name = ?, contact = ? where sup_id = ?");

            pre.setObject(1, txtName.getText());
            pre.setObject(2,txtContact.getText());
            pre.setObject(3, txtId.getText());



            int executeUpdate = pre.executeUpdate();

            if (executeUpdate > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, " Updated!").show();

            }

        } catch (Exception e) {

            System.out.println(e);
        }
    }

    public void OnActionDelete(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "DELETE FROM Supplier WHERE sup_id=?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setObject(1, txtId.getText());
            int executeUpdate = pre.executeUpdate();

            if (executeUpdate > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, " Deleted!").show();
                txtId.clear();
                txtName.clear();
                txtContact.clear();
            }
        } catch (Exception e) {
        }
    }

    public void OnActionSearch(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            Supplier supplier = SupplierModel.search(id);
            if (supplier != null) {
                fillData(supplier);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    private void fillData(Supplier supplier) {
        txtId.setText(supplier.getSupId());
        txtName.setText(supplier.getName());
        txtContact.setText(supplier.getContact());

    }



    public void OnActionOut(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, ancSupplier);
    }

    public void OnActionClothes(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CLOTHES, ancSupplier);
    }

    public void nameK(KeyEvent keyEvent) {
        if (RegexUtil.regex(txtName.getText(),"\\b([a-z]|[A-Z])+")){
            btnAdd.setDisable(false);
            txtName.setFocusColor(Paint.valueOf("blue"));
        }else {
            btnAdd.setDisable(true);
            txtName.setFocusColor(Paint.valueOf("red"));

        }
    }

    public void mobileK(KeyEvent keyEvent) {
        if (RegexUtil.regex(txtContact.getText(), "0((11)|(7(7|0|8|4|9|1|[3-7]))|(3[1-8])|(4(1|5|7))|(5(1|2|4|5|7))|(6(3|[5-7]))|([8-9]1))[0-9]{7}")) {
            btnAdd.setDisable(false);
            txtContact.setFocusColor(Paint.valueOf("blue"));
        } else {
            btnAdd.setDisable(true);
            txtContact.setFocusColor(Paint.valueOf("red"));

        }
    }
}


