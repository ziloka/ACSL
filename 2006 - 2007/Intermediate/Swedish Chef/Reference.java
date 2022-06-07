// http://www.comscigate.com/contests/ACSL/VOL29/prog_sol/c3_jr_jefferson_java.htm

import java.io.*;

public class Reference {

  public static String getTranslation(String english) {
    String swedish = "";
    boolean ifound = false;
    for (int k = 0; k < english.length(); k++) {
      char test = english.charAt(k);
      if (test == 'A') {
        if (k != english.length() - 1 && english.charAt(k + 1) == 'N') {
          k++;
          swedish += "UN";
        } else if (k != english.length() - 1 && english.charAt(k + 1) == 'U') {
          k++;
          swedish += "OO";
        } else if (k != english.length() - 1) swedish += "E";
        else swedish += "" + test;
      } else if (test == 'O') {
        if (k != english.length() - 1 && english.charAt(k + 1) == 'W') {
          k++;
          swedish += "OO";
        } else if (k != english.length() - 1) swedish += "U";
        else swedish += "" + test;
      } else if (test == 'I') {
        if (k != 0 && ifound == false) {
          ifound = true;
          swedish += "EE";
        } else swedish += "" + test;
      } else if (test == 'E') {
        if (k == english.length() - 2 && english.charAt(k + 1) == 'N') {
          k++;
          swedish += "EE";
        } else if (k == english.length() - 1)
          swedish += "E-A";
        else if (k == 0) swedish += "I";
        else swedish += "" + test;
      } else if (test == 'U') {
        swedish += "OO";
      } else swedish += "" + test;
    }
    ifound = false;
    return swedish;
  }

  public static void main(String[] args) throws Exception {
    try {
      BufferedReader reader = new BufferedReader(new FileReader("Test.in"));
      String input;
      while ((input = reader.readLine()) != null) {
        System.out.printf("Input: %s\n", input);
        System.out.printf("Output: %s\n\n", getTranslation(input));
      }
      reader.close();
    } catch(IOException e) {
      System.out.println(e.getMessage());
    }
  }

}