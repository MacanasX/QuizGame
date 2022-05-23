package de.hda.fbi.db2;
import de.hda.fbi.db2.controller.CsvDataReader;
import de.hda.fbi.db2.stud.entity.CSVReader;
import java.io.IOException;
import java.net.URISyntaxException;
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
  public static void main(String[] args) throws IOException, URISyntaxException {
   // Controller controller = Controller.getInstance();
    CSVReader myreader = new CSVReader();
    List<String[]>test = new ArrayList<>();
   // String file =CsvDataReader.getAvailableFiles();
   // CsvDataReader.read((CsvDataReader.getAvailableFiles());


    myreader.loadCsvFile(test);

    //TODO(stud): uncomment for lab01
   // controller.readCsv();

    //TODO(stud): uncomment for lab02
    //controller.persistData();

    //TODO(stud): uncomment for lab03, lab04 and lab05
    //controller.startMenu();
  }
}
