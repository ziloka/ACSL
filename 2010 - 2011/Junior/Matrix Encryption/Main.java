import java.io.*;
import java.util.Arrays;

class Main {
  public static void main(String[] args) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader("Test.in"));
      String abc = "abcdefghijklmnopqrstuvwxyz ";
      String digits = "123456789";
      String line;
      while ((line = reader.readLine()) != null) {
        String code = line.replaceAll(",", "");
        code = code.substring(0, code.length()-5);
        String encodeMatrixStr = line.substring(line.length()-8).replaceAll(",", "");
        int[] encodingMatrix = new int[4];
        for (int i = 0; i < encodeMatrixStr.length()-1; i++) {
          for (int j = 0; j < encodeMatrixStr.length()-1; j++) {
            if (encodingMatrix[j] == 0) {
              int num = Character.getNumericValue(encodeMatrixStr.charAt(i));
              if (encodeMatrixStr.length() < i) num = 27;
              encodingMatrix[i] = num;
              break;
            }
          }
        }
        int[][] matrix = new int[(int) (code.length()/2 + (double) 0.5)][2];
        // do matrix multiplication
        // | a b |   | e |   | ae + bf |
        // |     | x |   | = |         |
        // | c d |   | f |   | ce + df |
        for (int i = 0; i < code.length()-1; i+=2) {
          int first2dIndex = (int) ((double) i/2 + 0.5);
          int a = encodingMatrix[0];
          int b = encodingMatrix[1];
          int c = encodingMatrix[2];
          int d = encodingMatrix[3];
          int e = abc.indexOf(code.toLowerCase().charAt(i))+1;
          int f = abc.indexOf(code.toLowerCase().charAt(i+1))+1;
          matrix[first2dIndex][0] = (a*e) + (b*f);
          matrix[first2dIndex][1] = (c*e) + (d*f);
        }
        String output = "";
        for (int i = 0; i < code.length()/2; i++) {
          for (int j = 0; j < 2; j++) {
            int num = matrix[i][j];
            if (num > 27) num = num % 27;
            char letter = abc.charAt(num-1);
            output = output + letter;
          }
        }
        System.out.println(code.toUpperCase());
        System.out.println(output.toUpperCase() + "\n");
      }
      reader.close();
    } catch(IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
