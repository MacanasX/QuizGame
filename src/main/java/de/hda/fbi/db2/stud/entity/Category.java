package de.hda.fbi.db2.stud.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;


public class Category {

    private Integer ID;
    private String text;
    private ArrayList<Question> questionList;

    Category(Integer ID, String text){
        this.ID= ID;
        this.text= text;
        questionList = null;

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

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }
    public void printData(){

        System.out.println("Meine ID " + this.ID +" , Mein Text " + this.text +" \n" );
        for(int i = 0 ; i < this.questionList.size();i++)
        {
            questionList.get(i).printData();

        }

    }


}
