import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class A1 {

  private static char[][] grid;
  private static int N;
  private static int K;
  private static int[] mx = {1, 1, 1, 0};
  private static int[] my = {-1, 0, 1, 1};

  private static final char EMPTY = '.';

  /*
  private static boolean ifWin(char c) {
    String pattern = "";
    for (int i = 0; i < K; i++) {
      pattern += c;
    }

    char[] tmp = new char[N];

    // check the rows
    for (int i = 0; i < N; i++) {
      String row = String.valueOf(grid[i]);
      if (row.contains(pattern)) {
        return true;
      }
    }

    // check the cols
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        tmp[j] = grid[j][i];
        String col = String.valueOf(tmp);
        if (col.contains(pattern)) {
          return true;
        }
      }
    }

    // check the diagonal
    for (int i = 0; i < N; i++) {
      int j = 0;
      for (; j <= i ; j++) {
        tmp[j] = grid[j][i-j];
      }
      for (; j < N; j++) {
        tmp[j] = EMPTY;
      }

      String diag = String.valueOf(tmp);
      if (diag.contains(pattern)) {
        return true;
      }
    }

    for (int j = 1; j < N; j++) {
      int i = N - 1;
      for (; i >= j; i--) {
        tmp[N - 1 - i] = grid[i][N - 1 + j - i];
      }
      for (; i >= 0; i--) {
        tmp[N - 1 - i] = EMPTY;
      }

      String diag = String.valueOf(tmp);
      if (diag.contains(pattern)) {
        return true;
      }
    }

    for (int j = 0; j < N; j++) {
      int i = 0;
      for (; i < N - j; i ++) {
        tmp[i] = grid[i][i+j];
      }
      for (; i < N; i++) {
        tmp[i] = EMPTY;
      }

      String diag = String.valueOf(tmp);
      if (diag.contains(pattern)) {
        return true;
      }
    }

    for (int i = 1; i < N; i++) {
      int j = 0;
      for (; j < N - i; j++) {
        tmp[j] = grid[i + j][j];
      }
      for (; j < N; j++) {
        tmp[j] = EMPTY;
      }

      String diag = String.valueOf(tmp);
      if (diag.contains(pattern)) {
        return true;
      }
    }

    return false;
  }     */

  private static String ifWin() {
    boolean red = false;
    boolean blue = false;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (grid[i][j] == EMPTY) {
          continue;
        }

        char c = grid[i][j];
        for (int p = 0; p < 4; p++) {
          int mi = i;
          int mj = j;
          int q = 0;
          for (; q < K; q++) {
            if (mi < 0 || mi >= N || mj < 0 || mj >= N) {
              break;
            }
            if (grid[mi][mj] != c) {
              break;
            }

            mi += mx[p];
            mj += my[p];
          }

          if (q == K) {
            if (c == 'R') {
              red = true;
            } else if (c == 'B') {
              blue = true;
            }
          }
        }
      }
    }

    if (red && blue) {
      return "Both";
    } else if (red) {
      return "Red";
    } else if (blue) {
      return "Blue";
    } else {
      return "Neither";
    }
  }

  private static void rotate() {
    char[][] newGrid = new char[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        newGrid[j][N-1-i] = grid[i][j];
      }
    }
    grid = newGrid;
  }

  private static void gravity() {
    for (int i = 0; i < N; i++) {
      // for each column
      int p = N-1;
      int q = N-1;
      while (p >= 0) {
        if (grid[p][i] == EMPTY) {
          p --;
          continue;
        }
        if (p != q) {
          grid[q][i] = grid[p][i];
        }
        p --;
        q --;
      }

      while (q >= 0) {
        grid[q][i] = EMPTY;
        q --;
      }
    }
  }

  private static String solveCase(Scanner scanner) {
    N = scanner.nextInt();
    K = scanner.nextInt();
    scanner.nextLine();

    grid = new char[N][N];
    for (int i = 0; i < N; i++) {
      String line = scanner.nextLine();
      for (int j = 0; j < N; j++) {
        grid[i][j] = line.charAt(j);
      }
    }

    rotate();
    gravity();
    return ifWin();
  }

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(new File(args[0]));

    BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));

    int numCase = scanner.nextInt();
    for (int i = 1; i <= numCase; i++) {
      String res = solveCase(scanner);
      String output = "Case #" + i + ": " + res;
      writer.write(output);
      writer.newLine();
    }
    writer.close();
  }
}