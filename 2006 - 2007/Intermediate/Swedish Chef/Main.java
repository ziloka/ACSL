import java.io.*;

class Main {
  public static String getResult(String str) {
    return str.toLowerCase()
      .replaceAll("(?<=\\W)$", " BORK BORK BORK!")
      .replaceAll("the", "ZEE")
      .replaceAll("an", "UN")
      .replaceAll("au", "OO")
      // .replaceAll("a(?!\\w*e\\b)", "E")
      .replaceAll("a(?!\\b)", "E")
      .replaceAll("ow", "OO")
      .replaceAll("o", "U")
      .replaceAll("ir", "UR")
      .replaceAll("tion", "SHUN")
      // (?<=\s[^i]+)i regex, only works on .net and python (re package)
      .replaceFirst("(?!\\bi)i", "EE")
      // .replaceFirst("(?<!^)i", "EE")
      .replaceAll("en\\b", "EE")
      .replaceAll("f", "FF")
      .replaceAll("e\\b", "E-A")
      .replaceAll("(?!\\bu)u", "OO")
      // .replaceAll("(?<!^)u", "OO")
      .replaceAll("v", "F")
      .replaceAll("w", "V")
      .toUpperCase();
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
