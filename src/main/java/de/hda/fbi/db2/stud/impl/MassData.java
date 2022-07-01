package de.hda.fbi.db2.stud.impl;

import de.hda.fbi.db2.api.Lab04MassData;
import de.hda.fbi.db2.stud.entity.Category;
import de.hda.fbi.db2.stud.entity.Game;
import de.hda.fbi.db2.stud.entity.Player;
import de.hda.fbi.db2.stud.entity.Question;

import javax.persistence.EntityManager;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MassData extends Lab04MassData {

    private EntityManager em = null;
    @Override
    public void createMassData() {

            LocalTime start = LocalTime.now();
            Random rand = new Random();

          //  List<Category> resultL = (List<Category>) lab01Data.getCategories();
        em = lab02EntityManager.getEntityManager();
        em.getTransaction().begin();
            for (int i = 0; i < 100; i++) {
                Player p = (Player)lab03Game.getOrCreatePlayer("player " + i); //(Player) this.lab03Game.getOrCreatePlayer(playerName);
                for (int j = 0; j < 100; j++) {


                    ArrayList<Question> q = (ArrayList<Question>)lab03Game.getQuestions(lab01Data.getCategories(), 2);
                    Game currentGame = (Game)lab03Game.createGame(p, q );
                    lab03Game.playGame(currentGame);

                 //  lab03Game.persistGame(currentGame);
                   em.persist(currentGame);
                   em.merge(p);

                }

                if(i % 20 == 0 && i > 0){
                    em.flush();
                    em.clear();
                }
        }
                em.flush();
                em.clear();
                em.getTransaction().commit();
                em.close();




                LocalTime end = LocalTime.now();
                long between = ChronoUnit.MILLIS.between(start, end);
                int minutes = (int) ((between / 1000) / 60);
                int seconds = (int) ((between / 1000) % 60);
                System.out.println("The duration is: " + minutes + " minutes and " + seconds + " seconds");
            }
           /* em.getTransaction().commit();
            em.close();*/


    public void init() {
        System.out.println("test!!");
    }
}


