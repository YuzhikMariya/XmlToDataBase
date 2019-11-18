package com.bsuir.Yuzhik.model.service;

import com.bsuir.Yuzhik.model.dao.RestaurantDAO;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Service {

    private static String getID(Node node)
    {
        if ( ! node.hasAttributes() )
            return "";
        NamedNodeMap namedNodeMap = node.getAttributes();
        if ( namedNodeMap == null )
            return "";
        Node n = namedNodeMap.getNamedItem("id");
        if ( n == null )
            return "";
        return n.getNodeValue();
    }

    private static String getTextContent(Node parentNode,String childName)
    {
        NodeList nlist = parentNode.getChildNodes();
        for (int i = 0 ; i < nlist.getLength() ; i++) {
            Node n = nlist.item(i);
            String name = n.getNodeName();
            if ( name != null && name.equals(childName) )
                return n.getTextContent();
        }
        return "";
    }

    public static void insertOrderToDB(Document doc, RestaurantDAO dao) {
        NodeList nodeList = doc.getElementsByTagName("order");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                String id = getID(node);
                int tableNumber = Integer.parseInt(getTextContent(node, "ordTableNumber"));
                String title = getTextContent(node, "ordTitle");
                int time = Integer.parseInt(getTextContent(node, "time"));
                int price = Integer.parseInt(getTextContent(node, "price"));
                dao.insertOrder(id, tableNumber, title, time, price);
            }
        }
    }

    public static void insertDishToDB(Document doc, RestaurantDAO dao){
        NodeList nodeList = doc.getElementsByTagName("dish");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                String id =  getID(node);
                int tableNumber = Integer.parseInt(getTextContent(node, "dishTableNumber"));
                String title = getTextContent(node, "dishTitle");
                dao.insertDish(id, tableNumber, title);
            }
        }
    }


    public static void insertEmployeeToDB(Document doc, RestaurantDAO dao) {
        NodeList nodeList = doc.getElementsByTagName("employee");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                String id =  getID(node);
                String name = getTextContent(node, "name");
                String surname = getTextContent(node, "surname");
                int age = Integer.parseInt(getTextContent(node, "age"));
                String category = getTextContent(node, "category");
                String dob = getTextContent(node, "dob");
                dao.insertEmployee(id, name, surname, age, category, dob);
            }
        }
    }



    public static void deleteOrderFromDB(Document doc, RestaurantDAO dao) {
        NodeList nodeList = doc.getElementsByTagName("order");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                String id = getID(node);
                int tableNumber = Integer.parseInt(getTextContent(node, "ordTableNumber"));
                String title = getTextContent(node, "ordTitle");
                int time = Integer.parseInt(getTextContent(node, "time"));
                int price = Integer.parseInt(getTextContent(node, "price"));
                dao.deleteOrder(id, tableNumber, title, time, price);
            }
        }
    }

    public static void deleteDishFromDB(Document doc, RestaurantDAO dao){
        NodeList nodeList = doc.getElementsByTagName("dish");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                String id =  getID(node);
                int tableNumber = Integer.parseInt(getTextContent(node, "dishTableNumber"));
                String title = getTextContent(node, "dishTitle");
                dao.deleteDish(id, tableNumber, title);
            }
        }
    }


    public static void deleteEmployeeFromDB(Document doc, RestaurantDAO dao) {
        NodeList nodeList = doc.getElementsByTagName("employee");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                String id =  getID(node);
                String name = getTextContent(node, "name");
                String surname = getTextContent(node, "surname");
                int age = Integer.parseInt(getTextContent(node, "age"));
                String category = getTextContent(node, "category");
                String dob = getTextContent(node, "dob");
                dao.deleteEmployee(id, name, surname, age, category, dob);
            }
        }
    }
}