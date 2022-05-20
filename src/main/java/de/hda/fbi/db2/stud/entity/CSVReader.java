package de.hda.fbi.db2.stud.entity;

import de.hda.fbi.db2.api.Lab01Data;

import de.hda.fbi.db2.stud.entity.Category;
import de.hda.fbi.db2.stud.entity.Question;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader extends Lab01Data {

   private List<Category> myCategories = new ArrayList<>();


    @Override
    public List<?> getQuestions() {
        return null;
    }

    @Override
    public List<?> getCategories() {
        return null;
    }

    @Override
    public void loadCsvFile(List<String[]> csvLines) {

        String filename = "C:\\Users\\Luca\\IdeaProjects\\ZugB_Fr2x5-Bargache_Berneiser_Gross\\src\\main\\resources\\Wissenstest_sample200.csv";

        try {

            BufferedReader myBufferedReader = new BufferedReader(new FileReader(filename));
            String line="";
            String splitBy = ";";
            String firstline = myBufferedReader.readLine();
            Integer categoryId = 0;
            System.out.println(firstline);

            while((line = myBufferedReader.readLine())!=null){

                csvLines.add(line.split(splitBy));




            }
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
}
