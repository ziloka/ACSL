import java.io.*;

class Main {
  public static void main(String[] args) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader("Test.in"));
      String abc = "abcdefghijklmnopqrstuvwxyz ";
      String line;
      while ((line = reader.readLine()) != null) {
        String code = line.trim().replaceAll(",", "").replaceAll("0", "").replaceAll("1", "").replaceAll("2", "").replaceAll("3", "").replaceAll("4", "").replaceAll("5", "").replaceAll("6", "").replaceAll("7", "").replaceAll("8", "").replaceAll("9", "");
        String encodeMatrixStr = line.substring(line.length()-8).replaceAll(",", "").replace(" ", "");
        if (code.length() % 2 == 0) code = code + " ";
        System.out.println(code.trim().toUpperCase());
        
        int[] encodingMatrix = new int[4];
        for (int i = 0; i < encodeMatrixStr.length(); i++) {
          for (int j = 0; j < encodeMatrixStr.length(); j++) {
            if (encodingMatrix[j] == 0) {
              int num = Character.getNumericValue(encodeMatrixStr.charAt(i));
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
        int a = encodingMatrix[0];
        int b = encodingMatrix[1];
        int c = encodingMatrix[2];
        int d = encodingMatrix[3];
        for (int i = 0; i < code.trim().length(); i+=2) {
          int first2dIndex = (int) i/2;
          int e = abc.indexOf(code.toLowerCase().charAt(i))+1;
          int f;
          if (i+1 == code.length() && (i+1) % 2 == 1) f = 27;
          else f = abc.indexOf(code.toLowerCase().charAt(i+1))+1;
          matrix[first2dIndex][0] = (a*e) + (b*f);
          matrix[first2dIndex][1] = (c*e) + (d*f);
        }
        
        String output = "";
        for (int i = 0; i < matrix.length; i++) {
          for (int j = 0; j < matrix[i].length; j++) {
            int num = matrix[i][j];
            if (num > 27) num = num % 27;
            if (num != 0) num = num - 1;
            char letter = abc.charAt(num);
            output = output + letter;
          }
        }

        System.out.println(output.toUpperCase() + "\n");
      }
      reader.close();
    } catch(IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
