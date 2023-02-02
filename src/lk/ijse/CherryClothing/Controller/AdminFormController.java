package lk.ijse.CherryClothing.Controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.CherryClothing.util.Navigation;
import lk.ijse.CherryClothing.util.Routes;

import java.io.IOException;

public class AdminFormController {
    public AnchorPane ancAdmin;

    public void OnActionOut(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, ancAdmin);
    }

    public void OnActionEmployee(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEE, ancAdmin);
    }

    public void OnActionSupplier(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIER, ancAdmin);
    }

    public void OnActionClothes(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CLOTHES, ancAdmin);
    }

    public void OnActionDash(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN, ancAdmin);
    }
}


