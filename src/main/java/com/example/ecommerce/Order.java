package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class Order {
    static TableView<Product> orderTable;
    public static boolean placeOrder(Customer customer, Product product) {
        try {
            String placeOrder = "INSERT INTO orders(cid, pid, status) VALUES(" + customer.getId() + "," + product.getId() + ", 'Ordered')";
            DataBaseConnection dbConn = new DataBaseConnection();
            return dbConn.insertUpdate(placeOrder);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static int placeMultipleProductsOrder(ObservableList<Product> productObservableList, Customer customer) {
        int cnt = 0;
        for (Product product : productObservableList)
            if (placeOrder(customer, product)) cnt++;
        return cnt;
    }

    public Pane getAllProducts(){
        ObservableList<Product> productsList = Product.getAllProducts();
        return createTable(productsList);
    }

    public static Pane createTable(ObservableList<Product> orderList){
        TableColumn id = new TableColumn("Item");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("Mobile");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn quantity = new TableColumn("Quantity");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        orderTable = new TableView<>();
        orderTable.setItems(orderList);
        orderTable.getColumns().addAll(id, name, price, quantity);

        Pane tablePane = new Pane();
        tablePane.getChildren().add(orderTable);

        return tablePane;
    }

    public static Pane getOrders(Customer customer) {
        if (customer==null) System.out.println("null");
        String query = "select orders.oid, products.name, products.price, orders.quantity from orders inner join products on orders.pid = products.pid where cid = " + customer.getId();
        ObservableList<Product> orderList = Product.getProducts2(query);
        return createTable(orderList);
    }
}
