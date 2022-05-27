import java.io.*;
import java.util.Arrays;

class Main {
  public static void main(String[] args) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader("Test.in"));
      String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
      String line;
      while ((line = reader.readLine()) != null) {
        String code = line.substring(0, line.length()-8).replaceAll(",", "").trim();
        String encodeMatrixStr = line.substring(line.length()-8).replaceAll(",", "").trim();
        if (code.length() % 2 == 1) code = code + " ";

        int a = Character.getNumericValue(encodeMatrixStr.charAt(0));
        int b = Character.getNumericValue(encodeMatrixStr.charAt(1));
        int c = Character.getNumericValue(encodeMatrixStr.charAt(2));
        int d = Character.getNumericValue(encodeMatrixStr.charAt(3));
        int[][] matrix = new int[(int) (code.length()+1)/2][2];
        // do matrix multiplication
        // | a b |   | e |   | ae + bf |
        // |     | x |   | = |         |
        // | c d |   | f |   | ce + df |
        for (int i = 0; i < matrix.length; i++) {
          int e = abc.indexOf(code.charAt(i*2))+1;
          int f = abc.indexOf(code.charAt(i*2+1))+1;
          matrix[i][0] = (a*e) + (b*f);
          matrix[i][1] = (c*e) + (d*f);
        }
        
        String output = "";
        for (int i = 0; i < matrix.length; i++) {
          for (int j = 0; j < matrix[i].length; j++) {
            int num = matrix[i][j];
            if (num > 27) num = num % 27;
            if (num != 0) num = num - 1;
            output = output + abc.charAt(num);
          }
        }
        System.out.println(output + "\n");
      }
      reader.close();
    } catch(IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
