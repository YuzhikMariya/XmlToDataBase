package com.bsuir.Yuzhik.view;

public class View {

    public void showXmlPathInfo(){
        System.out.println("Enter path to xml file");
    }

    public void showXsdPathInfo(){
        System.out.println("Enter path to xsd file");
    }

    public void choiceInfo(){
        System.out.println("What do you want to do with xml data?");
        System.out.println("1 - insert into data base");
        System.out.println("2 - delete from data base");
    }

    public void showParseError(){
        System.out.println("Parse error");
    }

    public void showClassExcexption(){
        System.out.println("Class of driver not found");
    }

    public void showConnectionError(){
        System.out.println("Connection error");
    }

}
