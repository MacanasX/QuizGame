package de.hda.fbi.db2;
import de.hda.fbi.db2.stud.entity.CSVReader;
import java.util.ArrayList;
import java.util.List;

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
  public static void main(String[] args) {
   // Controller controller = Controller.getInstance();
    CSVReader myreader = new CSVReader();
    List<String[]>test = new ArrayList<>();


    myreader.loadCsvFile(test);

    //TODO(stud): uncomment for lab01
   // controller.readCsv();

    //TODO(stud): uncomment for lab02
    //controller.persistData();

    //TODO(stud): uncomment for lab03, lab04 and lab05
    //controller.startMenu();
  }
}
