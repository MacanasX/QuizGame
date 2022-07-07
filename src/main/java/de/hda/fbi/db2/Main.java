package de.hda.fbi.db2;

import de.hda.fbi.db2.controller.Controller;
import java.io.IOException;
import java.net.URISyntaxException;


/**
 * Main Class.
 *
 * @author L. Koehler
 */
public class Main {

  /**
   * Main Method and Entry-Point.
   *
   * @param args Command-Line Arguments.
   */
  public static void main(String[] args) throws IOException, URISyntaxException {
    Controller controller = Controller.getInstance();

    //TODO(stud): uncomment for lab01
    //controller.readCsv();

    //TODO(stud): uncomment for lab02
    controller.persistData();

    //TODO(stud): uncomment for lab03, lab04 and lab05
    controller.startMenu();
  }
}
