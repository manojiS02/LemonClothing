package lk.ijse.CherryClothing.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.CherryClothing.Model.CustomerModel;
import lk.ijse.CherryClothing.Model.EmployeeModel;
import lk.ijse.CherryClothing.db.DBConnection;
import lk.ijse.CherryClothing.to.Customer;
import lk.ijse.CherryClothing.to.Employee;
import lk.ijse.CherryClothing.util.Navigation;
import lk.ijse.CherryClothing.util.RegexUtil;
import lk.ijse.CherryClothing.util.Routes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeFormController {
    public AnchorPane ancEmployee;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtId;
    public JFXTextField txtSalary;
    public JFXButton btnAdd;

    public void OnActionEmployee(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEE, ancEmployee);
    }

    public void OnActionSupplier(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIER, ancEmployee);
    }

    public void OnActionDash(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN, ancEmployee);
    }

    public void OnActionAdd(ActionEvent actionEvent) {

        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());

        Employee employee= new Employee(id,name,address,salary);
        try {
            boolean isAdded = EmployeeModel.register(employee);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Happend!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public void OnActionUpdate(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());


        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pre = connection.prepareStatement("UPDATE Employee SET  empy_name = ?, empy_address = ?, salary = ?  where empy_id = ?");

            pre.setObject(1, txtName.getText());
            pre.setObject(2, txtAddress.getText());
            pre.setObject(3, txtSalary.getText());
            pre.setObject(4, txtId.getText());



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
            String sql = "DELETE FROM Employee WHERE empy_id=?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setObject(1, txtId.getText());
            int executeUpdate = pre.executeUpdate();

            if (executeUpdate > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, " Deleted!").show();
                txtId.clear();
                txtName.clear();
                txtAddress.clear();
                txtSalary.clear();

            }
        } catch (Exception e) {
        }
    }

    public void OnActionSearch(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            Employee employee = EmployeeModel.search(id);
            if (employee != null) {
                fillData(employee);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    private void fillData(Employee employee) {
        txtId.setText(employee.getId());
        txtName.setText(employee.getName());
        txtAddress.setText(employee.getAddress());
        txtSalary.setText(String.valueOf(employee.getSalary()));

    }


    public void OnActionOut(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, ancEmployee);
    }

    public void OnActionClothes(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CLOTHES, ancEmployee);
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

    public void addressK(KeyEvent keyEvent) {
        if (RegexUtil.regex(txtAddress.getText(),"\\b([a-z]|[A-Z])+")){
            btnAdd.setDisable(false);
            txtAddress.setFocusColor(Paint.valueOf("blue"));
        }else {
            btnAdd.setDisable(true);
            txtAddress.setFocusColor(Paint.valueOf("red"));

        }
    }

    public void salaryK(KeyEvent keyEvent) {

    }
}


