package com.bsuir.Yuzhik.controller;

import com.bsuir.Yuzhik.model.dao.RestaurantDAO;
import com.bsuir.Yuzhik.model.service.Service;
import com.bsuir.Yuzhik.model.service.Validator;
import com.bsuir.Yuzhik.view.View;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.SQLException;
import java.util.Scanner;

public class Controller {

    private static final Logger logger = Logger.getLogger(Controller.class);

    /*
     * View field
     */
    private View view = new View();

    /**
     * Reaction to user actions
     */
    public void choiceController() {

        Document doc;
        Scanner userEnter = new Scanner(System.in);
        view.showXmlPathInfo();
        String xmlName = userEnter.nextLine();
        view.showXsdPathInfo();
        String xsdName = userEnter.nextLine();
        if (!Validator.SAXValidator(xmlName, xsdName))
            return;

        try {
            File file = new File(xmlName);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(file);
        } catch (Exception e) {
            logger.error(e);
            view.showParseError();
            return;
        }

        int choice = 0;
        while ((choice != 1) && (choice != 2)) {
            view.choiceInfo();
            try {
                choice = Integer.parseInt(userEnter.next());
            } catch (NumberFormatException e) {
                choice = 0;
            }
        }

        RestaurantDAO dao = null;
        try {
            dao = new RestaurantDAO("root", "Masyanya7");
        }
        catch (ClassNotFoundException e) {
            logger.error("Connection error", e);
            view.showClassExcexption();
        }
        catch (SQLException e) {
            logger.error("Connection error", e);
            view.showConnectionError();
        }

        if (dao != null) {
            if (choice == 1) {
                Service.insertEmployeeToDB(doc, dao);
                Service.insertDishToDB(doc, dao);
                Service.insertOrderToDB(doc, dao);
            } else {
                Service.deleteEmployeeFromDB(doc, dao);
                Service.deleteDishFromDB(doc, dao);
                Service.deleteOrderFromDB(doc, dao);
            }
            dao.destroyConnection();
        }


    }
}

