package de.hda.fbi.db2.stud.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;


public class Question {

    private Integer ID;
    private String text;
    private Category category;
    private ArrayList<Answer> myAnswerList;

    Question(Integer ID, String text, Category category){
        this.ID= ID;
        this.text=text;
        this.category=category;
        this.myAnswerList=null;

    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArrayList<Answer> getMyAnswerList() {
        return myAnswerList;
    }

    public void setMyAnswerList(ArrayList<Answer> myAnswerList) {
        this.myAnswerList = myAnswerList;
    }

    public void printData(){

        System.out.println("Meine ID " + this.ID +" , Mein Text " + this.text +" , Meine Kategorie " + this.category + " \n" );
        for(int i = 0 ; i < this.myAnswerList.size();i++)
        {
            myAnswerList.get(i).printData();

        }

    }




}
