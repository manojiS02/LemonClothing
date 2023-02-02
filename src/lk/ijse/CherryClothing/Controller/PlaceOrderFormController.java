package lk.ijse.CherryClothing.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.CherryClothing.Model.*;
import lk.ijse.CherryClothing.to.*;
import lk.ijse.CherryClothing.util.Navigation;
import lk.ijse.CherryClothing.view.tm.PlaceOrderTM;
import lk.ijse.CherryClothing.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {
    @FXML
    public AnchorPane ancPlaceOrder;
    @FXML
    public JFXComboBox cmbItemId;
    @FXML
    public Label lblPayId;
    @FXML
    public Label lblDeliveryId;
    @FXML
    private TableView<PlaceOrderTM> tblOrder;
    @FXML
    public TableColumn ColId;
    @FXML
    public TableColumn colType;
    @FXML
    public TableColumn colQty;
    @FXML
    public TableColumn colPrice;
    @FXML
    public Label lblType;
    @FXML
    public JFXTextField txtQty;
    @FXML
    public TableColumn colTotal;
    @FXML
    public JFXComboBox cmbCustomerId;
    @FXML
    public Label lblUnitPrice;
    @FXML
    public Label lblQtyOnHand;
    @FXML
    public Label lblOrderId;
    @FXML
    public Label lblOrderDate;
    @FXML
    public Label lblCustomerName;
    @FXML
    public TableColumn colAction;
    @FXML
    private ObservableList<PlaceOrderTM> obList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        start();
    }

    public void start(){
        loadOrderDate();
        loadCustomerIds();
        loadNextPayIds();
        loadNextDeliveryIds();
        loadNextOrderId();
        loadItemCodes();
        setCellValueFactory();
    }


    public void OnActionCustomer(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER, ancPlaceOrder);
    }

    public void OnActionDelivery(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DELIVER, ancPlaceOrder);
    }

    public void OnActionPay(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PAYMENT, ancPlaceOrder);
    }

    public void btnNewCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER, ancPlaceOrder);
    }

    public void OnActionCClothes(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CCLOTHES, ancPlaceOrder);
    }

    public void OnActionCDash(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CASHIER, ancPlaceOrder);
    }

    public void OnActionPlace(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PLACEORDER, ancPlaceOrder);
    }

    public void OnActionOut(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, ancPlaceOrder);
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        String orderId = lblOrderId.getText();
        String customerId = String.valueOf(cmbCustomerId.getValue());
        String payId = lblPayId.getText();
        String deliveryId = lblDeliveryId.getText();

        Payment payment = new Payment(payId,
                LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")),
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "CASH");

        Delivery delivery = new Delivery(deliveryId,"Panadura");

        ArrayList<CartDetail> cartDetails = new ArrayList<>();

        /* load all cart items' to orderDetails arrayList */
        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            /* get each row details to (PlaceOrderTm)tm in each time and add them to the orderDetails */
            PlaceOrderTM tm = obList.get(i);
            cartDetails.add(new CartDetail(orderId, tm.getId(), tm.getQty(), tm.getType(), tm.getUnitPrice()));
        }

        PlaceOrder placeOrder = new PlaceOrder(customerId, orderId, payId, deliveryId, cartDetails);



        try {
            boolean register = PaymentModel.register(payment);
            boolean register1 = DeliveryModel.register(delivery);
            if(register && register1){
                boolean isPlaced = PlaceOrderModel.placeOrder(placeOrder);
                if (isPlaced) {
                    /* to clear table */
                    obList.clear();
                    loadNextOrderId();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!");
                    alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
                        @Override
                        public void handle(DialogEvent event) {
                            loadNextOrderId();
                            loadNextDeliveryIds();
                            loadNextPayIds();
                            //cmbCustomerId.valueProperty().set(null);
                            //cmbItemId.getSelectionModel().clearSelection();
                            lblCustomerName.setText("");
                            lblType.setText("");
                            lblUnitPrice.setText("");
                            lblQtyOnHand.setText("");

                        }
                    });
                    alert.setOnHidden(new EventHandler<DialogEvent>() {
                        @Override
                        public void handle(DialogEvent event) {
                            loadNextOrderId();
                            loadNextDeliveryIds();
                            loadNextPayIds();
                            cmbCustomerId.getSelectionModel().clearSelection();
                            //cmbItemId.getSelectionModel().clearSelection();
                            lblCustomerName.setText("");
                            lblType.setText("");
                            lblUnitPrice.setText("");
                            lblQtyOnHand.setText("");

                        }
                    });
                    alert.show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Order Not Placed!").show();
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    private void loadNextOrderId() {
        try {
            String orderId = OrderModel.generateNextOrderId();
            lblOrderId.setText(orderId);
           // System.out.println("OrderID : "+orderId);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    private void loadOrderDate() {

        lblOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    private void loadNextPayIds() {
        try {
            String payId = PaymentModel.generateNextPayId();
            lblPayId.setText(payId);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }

    }

    private void loadNextDeliveryIds() {
        try {
            String deliveryId = DeliveryModel.generateNextDeliveryId();
            lblDeliveryId.setText(deliveryId);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }

    }

    private void fillCustomerFields(Customer customer) {

        lblCustomerName.setText(customer.getName());
    }

    public void cmbCustomerOnAction(ActionEvent actionEvent) {
        String id = String.valueOf(cmbCustomerId.getValue());
        try {
            Customer customer = CustomerModel.search(id);
            fillCustomerFields(customer);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    private void loadCustomerIds() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> idList = CustomerModel.loadCustomerIds();

            for (String id : idList) {
                observableList.add(id);
            }
            cmbCustomerId.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    private void loadItemCodes() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> itemList = ItemModel.loadItemCodes();

            for (String code : itemList) {
                observableList.add(code);
            }
            cmbItemId.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    private void setCellValueFactory() {
        ColId.setCellValueFactory(new PropertyValueFactory("id"));
        colType.setCellValueFactory(new PropertyValueFactory("type"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));
        colAction.setCellValueFactory(new PropertyValueFactory("btnDelete"));
    }

    public void cmbItemOnAction(ActionEvent actionEvent) {
        String code = String.valueOf(cmbItemId.getValue());
        try {
            Item item = ItemModel.search(code);
            fillItemFields(item);
            txtQty.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    private void fillItemFields(Item item) {
        lblType.setText(item.getType());
        lblUnitPrice.setText(String.valueOf(item.getUnitPrice()));
        lblQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
    }


    public void txtQtyOnAction(ActionEvent actionEvent) {

        btnAddToCartOnAction(actionEvent);
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
       /* boolean isEnough2 = Double.parseDouble(txtQty.getText()) <= Double.parseDouble(lblQtyOnHand.getText());
        boolean isEnough = Double.parseDouble(lblQtyOnHand.getText()) > 0;

        if (!cmbCustomerId.getId().equals("") && !cmbItemId.getId().equals("")) {
            if (isEnough && isEnough2) {

            }else {
                try {
                    new Alert(Alert.AlertType.WARNING,"Please select enough qty").show();
                }catch (RuntimeException e){
                    System.out.println(e);
                }*/
        String id = String.valueOf(cmbItemId.getValue());
        int qty = Integer.parseInt(txtQty.getText());
        String type = lblType.getText();
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double total = unitPrice * qty;
        Button btnDelete = new Button("Delete");

        txtQty.setText("");

        if (!obList.isEmpty()) {
            L1:
            /* check same item has been in table. If so, update that row instead of adding new row to the table */
            for (int i = 0; i < tblOrder.getItems().size(); i++) {
                if (ColId.getCellData(i).equals(id)) {
                    qty += (int) colQty.getCellData(i);
                    total = unitPrice * qty;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(total);
                    tblOrder.refresh();
                    return;

                }
            }
        }


        /* set delete button to some action before it put on obList */
        btnDelete.setOnAction((e) -> {
            ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(no) == ok) {
                PlaceOrderTM tm = tblOrder.getSelectionModel().getSelectedItem();
                /*
                netTot = Double.parseDouble(txtNetTot.getText());
                netTot = netTot - tm.getTotalPrice();
                */

                tblOrder.getItems().removeAll(tblOrder.getSelectionModel().getSelectedItem());
            }
        });
        obList.add(new PlaceOrderTM(id, type, qty, unitPrice, total, btnDelete));
        tblOrder.setItems(obList);
    }



}
