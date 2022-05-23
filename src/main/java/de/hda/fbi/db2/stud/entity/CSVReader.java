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

      String categorieExist;
      String[] column;


            for(int i = 1 ; i < csvLines.size(); i++) {

              column = csvLines.get(i);
              String categorie =column[7];
            // System.out.println(column[7]);

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
            }
     // myCategories.get(0).printData();
     // myCategories.get(1).printData();
     // myCategories.get(2).printData();
     // myCategories.get(3).printData();

      System.out.println("size from list: " + myCategories.size());
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

    }}
