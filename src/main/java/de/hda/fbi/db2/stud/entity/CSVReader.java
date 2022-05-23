package de.hda.fbi.db2.stud.entity;

import de.hda.fbi.db2.api.Lab01Data;

import de.hda.fbi.db2.controller.CsvDataReader;
import de.hda.fbi.db2.stud.entity.Category;
import de.hda.fbi.db2.stud.entity.Question;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader extends Lab01Data {

   private List<Category> myCategories = new ArrayList<>();
   private List <Question> myQuestions = new ArrayList<>();
  // private List <Answer> myAnswers = new ArrayList<>();


    @Override
    public List<?> getQuestions() {
        return myQuestions;
    }

    @Override
    public List<?> getCategories() {
        return myCategories;
    }


    @Override
    public void loadCsvFile(List<String[]> csvLines)  {
      List<String> Files = new ArrayList<>();
      try {
        Files = CsvDataReader.getAvailableFiles();
      } catch (IOException | URISyntaxException e) {
        e.printStackTrace();
      }

      try {
        csvLines = CsvDataReader.read(Files.get(0));
      } catch (IOException e) {
        e.printStackTrace();
      } catch (URISyntaxException e) {
        e.printStackTrace();
      }

    /*  for (String[] a:csvLines
      ) {
        for(int i = 0 ; i < 8 ; i++)
        {
          System.out.println(a[i]);

        }
      }
    */


          /*  BufferedReader myBufferedReader = new BufferedReader(new FileReader(filename));
            String line="";
            String splitBy = ";";
            String firstline = myBufferedReader.readLine();
            Integer categoryId = 0;
            System.out.println(firstline);

            while((line = myBufferedReader.readLine())!=null){

                csvLines.add(line.split(splitBy));

            }
            */

      //get all Categories from List and initialize new Categorie Objects
    //  String categorieExist;
      String[] column;
      String categorie;
      boolean categoryExists;

        for(int i = 1 ; i < csvLines.size(); i++) {

            column = csvLines.get(i);
            categorie = column[7];
            categoryExists = false;
            // System.out.println(column[7]);

            if (myCategories.isEmpty()) {
                Category category = new Category(myCategories.size() + 1, categorie);
                myCategories.add(category);
            }

            if (myCategories.size() > 0) {

                for (int k = 0; k < myCategories.size(); k++) {
                    System.out.println("akutelle categorie: " + categorie + " und die hinzugefügte Kategorie " + myCategories.get(k).getText());

                    categoryExists = categorie.equals(myCategories.get(k).getText());
                    if (!categoryExists) { //categorie.equals(myCategories.get(k).getText())
                        categoryExists = false;
                    } else {
                        categoryExists = true;
                        break;
                    }

                }

            }

            if (!categoryExists) {
                Category category2 = new Category(myCategories.size() + 1, categorie);
                myCategories.add(category2);
            }
            System.out.println("size from list: " + myCategories.size());
        }

        for (int i =0 ; i < myCategories.size(); i++){
            System.out.println("id:" + myCategories.get(i).getID() + " name: "+ myCategories.get(i).getText());
        }
        System.out.println("Anzahl der Kategorien: " + myCategories.size());


        //get all Answers from List and initialize new Answers Objects

        /*for(int i = 1 ; i < csvLines.size(); i++) {

            column = csvLines.get(i);

            Answer answers0 = new Answer(column[2]);
            Answer answers1 = new Answer(column[3]);
            Answer answers2 = new Answer(column[4]);
            Answer answers3 = new Answer(column[5]);
        }*/
            //ArrayList<Answer> myanswers = new ArrayList<>();
            //ArrayList<Question> myquestion = new ArrayList<>();



        //get all Answers and Questions from List and initialize new Objects
        ArrayList<Answer> myAnswers = new ArrayList<>();
        for(int i = 1 ; i < csvLines.size(); i++) {

            column = csvLines.get(i);
            String frage = column[1];
            String category = column [7];
            int correctAnswer = Integer.parseInt(column [6]);



            Answer answers1 = new Answer(column[2]);
            if(correctAnswer == 1) {
                answers1.setCorrectAnswer(true);
            }
            else{
                answers1.setCorrectAnswer(false);
            }
            myAnswers.add(answers1);

            Answer answers2 = new Answer(column[3]);
            if(correctAnswer == 2) {
                answers2.setCorrectAnswer(true);
            }
            else{
                answers2.setCorrectAnswer(false);
            }
            myAnswers.add(answers2);

            Answer answers3 = new Answer(column[4]);
            if(correctAnswer == 3) {
                answers3.setCorrectAnswer(true);
            }
            else{
                answers3.setCorrectAnswer(false);
            }
            myAnswers.add(answers3);

            Answer answers4 = new Answer(column[5]);
            if(correctAnswer == 4) {
                answers4.setCorrectAnswer(true);
            }
            else{
                answers4.setCorrectAnswer(false);
            }
            myAnswers.add(answers4);




           // Create Questions

            for(int j=0; j < myCategories.size(); j++) {
                if(category.equals(myCategories.get(j).getText())) {

                    Question question = new Question(myQuestions.size() + 1, frage, myCategories.get(j), myAnswers); //
                    //myQuestions.setMyAnswerList(myAnswers);
                    myQuestions.add(question);

                }
            }
        }

        //print questions
        for(int i=0; i < myQuestions.size(); i++) {
            System.out.println("id " + myQuestions.get(i).getID() + " Frage: " + myQuestions.get(i).getText() + " Kategorie: " + myQuestions.get(i).getCategory().getText() );
          //  for(int j=0; j < 4; j++) {
            //    myQuestions.get(i).getMyAnswerList(j);
        }

        //add Questions to questionList of Category
        /*for(int i=0; i < myCategories.size(); i++) {
            ArrayList<Question> questionList = new ArrayList<>();
            for(int j=0; j<myQuestions.size(); j++){
                if(myCategories.get(i).getText().equals(myQuestions.get(j).getCategory())){
                    questionList.add(myQuestions.get(j));
                }
            }
            myCategories.get(i).set(questionList);
        }*/







/*Category category2 = new Category(myCategories.size()+1, categorie);
                        myCategories.add(category2);
                        System.out.println("TEST");
                        break;/*

            }



            /*
              if(myCategories.isEmpty()) {
                Category category = new Category(this.myCategories.size()+1, categorie);
                myCategories.add(category);
              }
              else {
                boolean categoryExists= false;
                String text="";

                int index = 0;

                while(index < myCategories.size()) {
                  if(this.myCategories.get(index).getText().equalsIgnoreCase(categorie))
                  {

                    break;

                  }
                  else if (){
                    Category category = new Category(this.myCategories.size()+1, categorie);
                    myCategories.add(category);
                    break;

                  }

                 //System.out.println(text);
                  // System.out.println(column[7]);



                  //System.out.println(categoryDoesNotExists);
                  // System.out.println("text der Kategorie: " + myCategories.get(j).getText());
                }

              }
            } */
     // myCategories.get(0).printData();
     // myCategories.get(1).printData();
     // myCategories.get(2).printData();
     // myCategories.get(3).printData();

     // System.out.println("size from list: " + myCategories.size());
           /*
            String[] tmp;
            for(int i = 0 ; i < csvLines.size(); i++)
            {
                tmp=csvLines.get(i);

                for (String line2:tmp) {

                   Category category = new Category(categoryId,tmp[7]);
                    Question question = new Question((Integer.parseInt(tmp[0])),tmp[1],category);
                    Answer answers0 = new Answer(tmp[2]);
                    Answer answers1 = new Answer(tmp[3]);
                    Answer answers2 = new Answer(tmp[4]);
                    Answer answers3 = new Answer(tmp[5]);
                    ArrayList<Answer> myanswers = new ArrayList<>();
                    ArrayList<Question> myquestion = new ArrayList<>();


                    for(int answerCount = 1 ; answerCount < 5; answerCount++){

                        if(Integer.parseInt(tmp[6]) ==answerCount)
                            answers0.setCorrectAnswer(true);
                        if(Integer.parseInt(tmp[6]) ==answerCount)
                            answers1.setCorrectAnswer(true);
                        if(Integer.parseInt(tmp[6]) ==answerCount)
                            answers2.setCorrectAnswer(true);
                        if(Integer.parseInt(tmp[6]) ==answerCount)
                            answers3.setCorrectAnswer(true);

                    }
                    myanswers.add(answers0);
                    myanswers.add(answers1);
                    myanswers.add(answers2);
                    myanswers.add(answers3);
                    question.setMyAnswerList(myanswers);
                    myquestion.add(question);
                    category.setQuestionList(myquestion);
                    myCategories.add(category);

                    for(int u = 0 ; u < myCategories.size(); u++ )
                    {
                        myCategories.get(u).printData();

                    }



                }

            }
        }
        catch (IOException t) {
            t.printStackTrace();
        }


    }

 */

    }
}
