public class Main {
  private static boolean[][] availableFields;
  private static boolean[][] finalFields;

  public static void main(String[] args) {
    if(!argumentsAreValid(args)) {
      System.out.println("Invalid arguments");
      System.exit(0);
    }

    availableFields = new boolean[3][3];
    int i = 0;
    for(int j = 0; j < 3; j++) {
      for(int k = 0; k < 3; k++ )  {
        switch (args[0].charAt(i)) {
          case '1':
            availableFields[j][k] = true;
            break;
          default:
            availableFields[j][k] = false;
            break;
        }
        ++i;
      }
    }

    int moves = Integer.parseInt(args[2]);
    int l = 0;
    finalFields = new boolean[3][3];
    for(int j = 0; j < 3; j++) {
      for (int k = 0; k < 3; k++) {
        // start a sequence from field at j,k if it was specified as a starting field
        if(args[1].charAt(l) == '1') {
          playStage(j, k, moves);
        }
        ++l;
      }
    }

    printResults();
  }

  private static boolean argumentsAreValid(String[] args) {
    return args.length == 3    // must have 3 arguments
    && args[0].matches("^[01]{9}$")   // arg 0 must be a sequence of 9 digits, containing only 0s and 1s
    && args[1].matches("^[01]{9}$")
    && args[2].matches("^[1-9][0-9]*$");  // arg 2 must be a positive integer
  }

  private static void playStage(int x, int y, int movesRemaining) {
    // check if starting point is available
    if(x >= 0 && x < 3 && y >= 0 && y < 3 && availableFields[x][y]) {
      if(movesRemaining > 0) {
        --movesRemaining;
        playStage(x+1, y, movesRemaining);
        playStage(x-1, y, movesRemaining);
        playStage(x, y+1, movesRemaining);
        playStage(x, y-1, movesRemaining);
      }
      // moves have been expended, current field is final field of sequence
      else {
        finalFields[x][y] = true;
      }
    }
  }

  private static void printResults() {
    System.out.println("_ unavailable field\nX used as final field\nO never used as final field\n");
    for(int j = 0; j < 3; j++) {
      for (int k = 0; k < 3; k++) {
        if(availableFields[j][k]) {
          if(finalFields[j][k]) {
            System.out.print("X");
          }
          else {
            System.out.print("O");
          }
        }
        else {
          System.out.print("_");
        }
      }
      System.out.println();
    }
  }
}