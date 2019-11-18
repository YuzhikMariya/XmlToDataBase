package com.bsuir.Yuzhik.model.service;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class Validator {

    private static final Logger logger = Logger.getLogger(Validator.class);

    public static boolean SAXValidator(String xmlName, String xsdName) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            File schemaLocation = new File(xsdName);
            Schema schema = factory.newSchema(schemaLocation);
            javax.xml.validation.Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlName);
            try {
                validator.validate(source);
                return true;
            } catch (Exception e) {
                logger.warn(xmlName + " is not valid because ", e);
                System.out.println(xmlName + " is not valid because ");
                System.out.println(e.getMessage());
                return false;
            }
        }catch (SAXException e){
            logger.warn(xmlName + " is not valid because ", e);
            System.out.println(" is'n valid.");
            return false;
        }
    }
}