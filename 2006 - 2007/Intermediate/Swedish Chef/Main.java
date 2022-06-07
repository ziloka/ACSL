import java.io.*;

class Main {
  public static String getResult(String str) {
    return str
      .replaceAll("(?<=\\W)$", " BORK BORK BORK!")
      .replaceAll("THE", "ZEE")
      .replaceAll("AN", "UN")
      .replaceAll("AU", "OO")
      .replaceAll("A(?!\\b)", "E")
      .replaceAll("OW", "OO")
      .replaceAll("O", "U")
      .replaceAll("IR", "UR")
      .replaceAll("TION", "SHUN")
      .replaceFirst("(?!\\bI)I", "EE")
      .replaceAll("EN\\b", "EE")
      .replaceAll("F", "FF")
      .replaceAll("E\\b", "E-A")
      .replaceAll("(?!\\bU)U", "OO")
      .replaceAll("V", "F")
      .replaceAll("W", "V");
  }
  public static void main(String[] args) {
     try {
        BufferedReader reader = new BufferedReader(new FileReader("Test.in"));
        String input;
        while ((input = reader.readLine()) != null) {
          System.out.printf("Input: %s\n", input);
          System.out.printf("Output: %s\n\n", getResult(input));
        }
        reader.close();
      } catch(IOException e) {
        System.out.println(e.getMessage());
      }
  }
}