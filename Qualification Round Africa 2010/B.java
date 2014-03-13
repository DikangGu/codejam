import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by dikang on 3/12/14.
 */
public class B {

  private static String solveCase(Scanner scanner) {
    String res = "";

    String line = scanner.nextLine();
    String[] words = line.split(" ");
    for (int i = 0; i < words.length; i++) {
      res = words[i] + " " + res;
    }
    res = res.trim();

    return res;
  }

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(new File(args[0]));

    BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));

    int numCase = scanner.nextInt();
    scanner.nextLine();
    for (int i = 1; i <= numCase; i++) {
      String res = solveCase(scanner);
      String output = "Case #" + i + ": " + res;
      writer.write(output);
      writer.newLine();
    }
    writer.close();
  }

}
