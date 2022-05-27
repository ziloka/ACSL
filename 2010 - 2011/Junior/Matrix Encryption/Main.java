import java.io.*;

class Main {
  public static void main(String[] args) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader("Test.in"));
      String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
      String input;
      while ((input = reader.readLine()) != null) {
        String code = input.substring(0, input.length()-8).replaceAll(",", "").trim();
        String matrixEncoding = input.substring(input.length()-8).replaceAll(",", "").trim();
        if (code.length() % 2 == 1) code = code + " ";

        int a = Character.getNumericValue(matrixEncoding.charAt(0));
        int b = Character.getNumericValue(matrixEncoding.charAt(1));
        int c = Character.getNumericValue(matrixEncoding.charAt(2));
        int d = Character.getNumericValue(matrixEncoding.charAt(3));

        String output = "";
        for (int i = 0; i < ((int) (code.length()+1)/2); i++) {
          int e = abc.indexOf(code.charAt(i*2))+1;
          int f = abc.indexOf(code.charAt(i*2+1))+1;
          output = output + abc.charAt((((a*e) + (b*f))-1) % 27);
          output = output + abc.charAt((((c*e) + (d*f))-1) % 27);
        }

        System.out.printf("Input: %s\n", input);
        System.out.printf("Output: %s\n\n", output);
      }
      reader.close();
    } catch(IOException e) {
      System.out.println(e.getMessage());
    }
  }
}