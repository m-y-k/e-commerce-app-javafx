package com.example.ecommerce;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {

    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty quantity;

    public int getQuantity() {
        return quantity.get();
    }

    public  int getId(){
        return id.get();
    }

    public  String getName(){
        return name.get();
    }

    public  Double getPrice(){
        return price.get();
    }

    public Product(int id, String name, Double price, int quantity){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public static ObservableList<Product> getAllProducts() {
        String allProductList = "SELECT * FROM products";
        return getProducts(allProductList);
    }
    public static ObservableList<Product> getProducts(String query) {
        DataBaseConnection dbConn = new DataBaseConnection();
        ResultSet rs = dbConn.getQueryTable(query);
        ObservableList<Product> result = FXCollections.observableArrayList();
        try {
            if (rs != null) {
                while (rs.next()) {
                   // taking out values from resultset
                    result.add(new Product(
                            rs.getInt("pid"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("quantity")
                    ));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ObservableList<Product> getProducts2(String query) {
        DataBaseConnection dbConn = new DataBaseConnection();
        ResultSet rs = dbConn.getQueryTable(query);
        ObservableList<Product> result = FXCollections.observableArrayList();
        try {
            if (rs != null) {
                while (rs.next()) {
                    // taking out values from resultset
                    result.add(new Product(
                            rs.getInt("oid"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("quantity")
                    ));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
