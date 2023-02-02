package lk.ijse.CherryClothing.bo.custom;

import lk.ijse.CherryClothing.bo.SuperBO;
import lk.ijse.CherryClothing.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    ArrayList<CustomerDTO> loadAllCustomers() throws SQLException, ClassNotFoundException;

    boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    boolean existCustomer(String id) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    String generateNewCustomerID() throws SQLException, ClassNotFoundException;
}
