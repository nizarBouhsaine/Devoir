package com.example.demo1;



import com.example.demo1.dao.Impl.ProductDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController extends Thread implements Initializable  {

    @FXML
    private TableColumn<Product, String> name;

    @FXML
    private TableColumn<Product, String> price;
    static ObservableList<Product> productList = FXCollections.observableArrayList();

    @FXML
    private TableView<Product> myTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        start();

    }

    private void getProductList() {
        ProductDaoImpl dao = new ProductDaoImpl();
        productList.addAll(dao.findAll());

    }
    @Override
    public void run() {
        try {
            while(true){
                JsonMapper.parse();
                getProductList();
                name.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
                price.setCellValueFactory(new PropertyValueFactory<Product,String>("price"));
                myTable.setItems(productList);
                sleep(60000);
                myTable.getItems().clear();
            }

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

