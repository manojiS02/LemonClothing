package lk.ijse.CherryClothing.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.CherryClothing.Model.CClothesModel;
import lk.ijse.CherryClothing.db.DBConnection;
import lk.ijse.CherryClothing.to.CClothes;
import lk.ijse.CherryClothing.util.Navigation;
import lk.ijse.CherryClothing.util.Routes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClothesFormController {
    public AnchorPane ancClothes;
    public JFXTextField txtId;
    public JFXTextField txtType;
    public JFXTextField txtQty;
    public JFXTextField txtPrice;

    public void OnActionEmployee(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEE, ancClothes);
    }

    public void OnActionSupplier(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIER, ancClothes);
    }

    public void OnActionDash(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN, ancClothes);
    }


    public void OnActionAdd(ActionEvent actionEvent) {
        CClothes cClothes = new CClothes(txtId.getText(), txtType.getText(), txtPrice.getText(), txtQty.getText());
        try {
            boolean isAdded = CClothesModel.register(cClothes);
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
            PreparedStatement pre = connection.prepareStatement("UPDATE Item SET  type = ?, unit_price = ?, qty_on_hand = ? where item_id = ?");

            pre.setObject(1, txtType.getText());
            pre.setObject(2, txtPrice.getText());
            pre.setObject(3, txtQty.getText());
            pre.setObject(4, txtId.getText());

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
            String sql = "DELETE FROM Item WHERE item_id=?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setObject(1, txtId.getText());
            int executeUpdate = pre.executeUpdate();

            if (executeUpdate > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, " Deleted!").show();
                txtId.clear();
                txtType.clear();
                txtPrice.clear();
                txtQty.clear();
            }
        } catch (Exception e) {
        }
    }

    public void OnActionSearch(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            CClothes cClothes = CClothesModel.search(id);
            if (cClothes != null) {
                fillData(cClothes);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    private void fillData(CClothes cClothes) {
        txtId.setText(cClothes.getId());
        txtType.setText(cClothes.getType());
        txtPrice.setText(cClothes.getPrice());
        txtQty.setText(cClothes.getQty());

    }


    public void OnActionOut(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, ancClothes);
    }

    public void OnActionClothes(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CLOTHES, ancClothes);

    }
}
