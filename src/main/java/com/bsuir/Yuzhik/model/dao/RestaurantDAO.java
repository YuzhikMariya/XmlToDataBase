package com.bsuir.Yuzhik.model.dao;

import com.bsuir.Yuzhik.model.service.Service;
import org.apache.log4j.Logger;

import java.sql.*;

public class RestaurantDAO {

    private Statement st;
    private Connection con;
    private static final Logger logger = Logger.getLogger(Service.class);

    public RestaurantDAO(String user, String password) throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/restaurant", user, password);
            st = con.createStatement();
    }

    public void insertDish(String id, int tableNumber, String title){
        try {
            String str = "INSERT INTO dishes (id, table_number, title) VALUES (\"" + id + "\", " + tableNumber + ", \"" +
                    title + "\");";
            st.executeUpdate(str);
        }
        catch (SQLException e){
            logger.warn("Dish not imported to table", e);
            System.out.println("Dish not imported to table");
        }
    }

    public void insertEmployee(String id, String name, String surname, int age, String category, String dob) {
        try {
            String str = "INSERT INTO employees (id, name, surname, age, category, birthday) VALUES (\"" + id + "\", \"" +
                    name + "\", \"" + surname + "\"," + age + ", \"" + category + "\", \"" + dob +"\");";
            st.executeUpdate(str);
        }
        catch (SQLException e){
            logger.warn("Employee not imported to table", e);
            System.out.println("Employee not imported to table");
        }
    }

    public void insertOrder(String id, int tableNumber, String title, int time, int price) {
        ResultSet rs = null;
        try {
            rs = st.executeQuery("SELECT id FROM employees");
            int i = 0;
            while (rs.next())
            {
                i++;
            }
            int ind = (int) (Math.random() * i) + 1;
            rs.absolute(ind);
            String employee_ID = rs.getString(1);

            rs = st.executeQuery("SELECT * FROM dishes");
            String dish_ID = "";
            while (rs.next())
            {
                if(rs.getString(3).equals(title) && (rs.getInt(2) == tableNumber))
                    dish_ID = rs.getString(1);
            }

            String str = "INSERT INTO orders (id, dish_id, employee_id, table_number, title, time, price) VALUES (\"" +
                    id + "\", \"" + dish_ID + "\", \"" + employee_ID + "\", " + tableNumber + ", \"" + title + "\", " + time +
                    ", " + price + ");";
            st.executeUpdate(str);
        }catch (SQLException e){
            logger.warn("Order not imported to table", e);
            System.out.println("Order not imported to table");
        }
    }

    public void deleteDish(String id, int tableNumber, String title){
        try {
            String str = "DELETE FROM dishes WHERE id = '" + id + "' AND table_number = '" + tableNumber +
                    "' AND title = '" + title + "';";
            st.executeUpdate(str);
        }catch (SQLException e){
            logger.warn("Dish not imported to table", e);
            System.out.println("Dish not deleted from table");
        }
    }

    public void deleteEmployee(String id, String name, String surname, int age, String category, String dob) {
        try {
            String str = "DELETE FROM employees WHERE id = '" + id + "' AND name = '" + name +"' AND surname = '" +
                    surname +  "' AND age = '" + age +  "' AND category = '" + category + "' AND birthday = '" +
                    dob + "';";
            st.executeUpdate(str);
        }catch (SQLException e){
            logger.warn("Employee not imported to table", e);
            System.out.println("Employee not deleted from table");
        }
    }

    public void deleteOrder(String id, int tableNumber, String title, int time, int price) {
        try{
            String str = "DELETE FROM orders WHERE id = '" + id + "' AND table_number = '" + tableNumber +
                    "' AND title = '" + title +  "' AND time = '" + time +  "' AND price = '" + price + "';";
            st.executeUpdate(str);
        }catch (SQLException e){
            logger.warn("Order not imported to table", e);
            System.out.println("Order not deleted from table");
        }
    }


    public void destroyConnection(){
        try {
            st.close();
            con.close();
        }catch (SQLException e){
            logger.warn("Can't close connection", e);
            System.out.println("Can't close connection");
        }

    }
}