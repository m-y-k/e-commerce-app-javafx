package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductList {

    public TableView<Product> productTable;


    public Pane getAllProducts(){
        ObservableList<Product> productsList = Product.getAllProducts();
        return createTable(productsList);
    }

    public Pane createTable(ObservableList<Product> productList){
        TableColumn id = new TableColumn("Item");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("Mobile");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn quantity = new TableColumn("Quantity");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));


//        ObservableList<Product> productsList = FXCollections.observableArrayList();


        productTable = new TableView<>();
        productTable.setItems(productList);
        productTable.getColumns().addAll(id, name, price, quantity);

        Pane tablePane = new Pane();
        tablePane.getChildren().add(productTable);

        return tablePane;
    }
    public Pane itemsCart(ObservableList<Product> productsList) {
        return createTable(productsList);
    }
    public Pane getAllSearchedProducts(String searchName){
        TableColumn id = new TableColumn("Item");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("Mobile");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn quantity = new TableColumn("Quantity");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // select * from products where name like 'a%';
        String query = "select * from products where name like '%" + searchName + "%'";
        ObservableList<Product> productsList = Product.getProducts(query);

        productTable = new TableView<>();
        productTable.setItems(productsList);
        productTable.getColumns().addAll(id, name, price, quantity);

        Pane tablePane = new Pane();
        tablePane.getChildren().add(productTable);

        return tablePane;
    }
    public Product getSelectedProduct() {
        // getting the selected item
        return productTable.getSelectionModel().getSelectedItem();
    }
}






















