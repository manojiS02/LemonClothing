package lk.ijse.CherryClothing.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.CherryClothing.Model.CClothesModel;
import lk.ijse.CherryClothing.db.DBConnection;
import lk.ijse.CherryClothing.to.CClothes;
import lk.ijse.CherryClothing.util.Navigation;
import lk.ijse.CherryClothing.util.Routes;
import lk.ijse.CherryClothing.view.tm.CClothesTM;
import lk.ijse.CherryClothing.view.tm.CustomerTM;

import java.io.IOException;
import java.sql.*;

public class CClothesFormController {
    public AnchorPane ancCClothes;
    public JFXTextField txtId;
    public JFXTextField txtType;
    public JFXTextField txtQty;
    public JFXTextField txtPrice;
    public TableView <CClothesTM>tblItem;
    public TableColumn ColId;
    public TableColumn ColType;
    public TableColumn ColPrice;
    public TableColumn ColQty;
    public JFXButton btnAdd;
    public JFXButton btnDelete;
    public JFXButton btnUpdte;

    public void initialize() throws SQLException, ClassNotFoundException {
        ColId.setCellValueFactory(new PropertyValueFactory("Id"));
        ColType.setCellValueFactory(new PropertyValueFactory("Type"));
        ColPrice.setCellValueFactory(new PropertyValueFactory("Price"));
        ColQty.setCellValueFactory(new PropertyValueFactory("qty"));


        initUI();

        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnAdd.setText(newValue != null ? "Update" : "Save");
            btnAdd.setDisable(newValue == null);



            if (newValue != null) {
                txtId.setText(newValue.getId());
                txtType.setText(newValue.getType());
                txtPrice.setText(newValue.getPrice());
                txtQty.setText(newValue.getQty());

                txtId.setDisable(false);
                txtType.setDisable(false);
                txtPrice.setDisable(false);
                txtQty.setDisable(false);
            }
        });

        txtQty.setOnAction(event -> btnAdd.fire());
        loadAllCustomers();
    }

    private void initUI() {
        txtId.clear();
        txtType.clear();
        txtPrice.clear();
        txtQty.clear();
        txtId.setDisable(false);
        txtType.setDisable(false);
        txtPrice.setDisable(false);
        txtQty.setDisable(false);
        txtId.setEditable(true);
        btnAdd.setDisable(false);
        btnDelete.setDisable(false);
    }

    private void loadAllCustomers() throws SQLException, ClassNotFoundException {
        tblItem.getItems().clear();
        /*Get all customers*/
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Item");

            while (rst.next()) {
                tblItem.getItems().add(new CClothesTM(rst.getString("item_id"), rst.getString("type"), rst.getString("qty_on_hand"), rst.getString("unit_price")));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

        public void OnActionPlace(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PLACEORDER, ancCClothes);
    }

    public void OnActionPay(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PAYMENT, ancCClothes);
    }


    public void OnActionDelivery(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DELIVER, ancCClothes);
    }

    public void OnActionCustomer(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER, ancCClothes);
    }

    public void OnActionOut(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, ancCClothes);
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

    public void OnActionAdd(ActionEvent actionEvent) {
        String id = txtId.getText();
        String type = txtType.getText();
        String price = txtPrice.getText();
        String qty = txtQty.getText();

        CClothes cClothes = new CClothes(txtId.getText(),txtType.getText(),txtPrice.getText(),txtQty.getText());
        try {
            boolean isAdded = CClothesModel.register(cClothes);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Happend!").show();
            }
            tblItem.getItems().add(new CClothesTM(id, type, price, qty));
        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e);
        }
    }

    public void OnActionCClothes(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CCLOTHES, ancCClothes);
    }

    public void OnActionCDash(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CASHIER, ancCClothes);
    }
}
