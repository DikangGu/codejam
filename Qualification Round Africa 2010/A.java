import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class A {

  static int []solveCase(Scanner scanner) throws IOException {
    int[] res = new int [2];
    int C = scanner.nextInt();
    int I = scanner.nextInt();
    Elements[] items = new Elements[I];
    for (int i = 0; i < I; i++) {
      items[i] = new Elements(scanner.nextInt(), i+1);
    }

    Arrays.sort(items);

    int left = 0;
    int right = I-1;

    while (left < right) {
      int totalValue = items[left].value + items[right].value;
      if (totalValue == C) {
        res[0] = items[left].index;
        res[1] = items[right].index;

        if (res[0] > res[1]) {
          int t = res[0];
          res[0] = res[1];
          res[1] = t;
        }
        break;
      } else if (totalValue > C) {
        right --;
      } else {
        left ++;
      }
    }

    return res;
  }

  public static class Elements implements Comparable {

    public int value;
    public int index;

    public Elements (int value, int index) {
      this.value = value;
      this.index = index;
    }

    @Override
    public int compareTo(Object o) {
      return this.value - ((Elements)o).value;
    }
  }

  public static void main(String[] args) throws IOException {

    Scanner scanner = new Scanner(new File(args[0]));

    BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));

    int numCase = scanner.nextInt();
    for (int i = 1; i <= numCase; i++) {
      int[] res = solveCase(scanner);
      String output = "Case #" + i + ": " + res[0] + " " + res[1];
      writer.write(output);
      writer.newLine();
    }
    writer.close();
  }
  
}
