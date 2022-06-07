// http://www.comscigate.com/contests/ACSL/VOL29/prog_sol/c3_int_freehold_java.htm

import java.io.*;

public class Reference {

  public static void main(String[] args) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader("Test.in"));
      String input;
      while ((input = reader.readLine()) != null) {
        System.out.printf("Input: %s\n", input);
        System.out.printf("Output: %s\n\n", translate(input));
      }
      reader.close();
    } catch(IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public static String translate(String eng) {

    int index = 0;
    String answer = "";
    boolean b = true, e, e2, step10 = false;
    char c2, c3, c4, cBefore;

    while (index < eng.length()) {
      int left = eng.length() - index;
      char c1 = eng.charAt(index);

      if (left > 1) c2 = eng.charAt(index + 1);
      else c2 = '•';
      if (left > 2) c3 = eng.charAt(index + 2);
      else c3 = '•';
      if (left > 3) c4 = eng.charAt(index + 3);
      else c4 = '•';
      if (index > 0) {
        cBefore = eng.charAt(index - 1);
        if (cBefore == ' ' || cBefore == '.') b = true;
        else b = false;
      }
      if (c2 == '•' || c2 == '.' || c2 == ' ') e = true;
      else e = false;
      if (c3 == '•' || c3 == '.' || c3 == ' ') e2 = true;
      else e2 = false;
      if (b == true) step10 = false;
      if (c1 == '.' || c1 == '!' || c1 == '?') {
        answer += String.valueOf(c1) + " BORK BORK BORK!";
        index++;
      } else if (c1 == 'T' && c2 == 'H' && c3 == 'E') {
        answer += "ZEE";
        index += 3;
      } else if (c1 == 'A' && c2 == 'N') {
        answer += "UN";
        index += 2;
      } else if (c1 == 'A' && c2 == 'U') {
        answer += "OO";
        index += 2;
      } else if (c1 == 'A' && e == false) {
        answer += "E";
        index++;
      } else if (c1 == 'O' && c2 == 'W') {
        answer += "OO";
        index += 2;
      } else if (c1 == 'T' && c2 == 'I' && c3 == 'O' && c4 == 'N') {
        answer += "SHUN";
        index += 4;
      } else if (c1 == 'I' && c2 == 'R') {
        answer += "UR";
        index += 2;
      } else if (c1 == 'O') {
        answer += "U";
        index++;
      } else if (c1 == 'I' && b == false && step10 == false) {
        answer += "EE";
        index++;
        step10 = true;
      } else if (c1 == 'E' && c2 == 'N' && e2 == true) {
        answer += "EE";
        index += 2;
      } else if (c1 == 'F') {
       answer += "FF";
        index++;
      } else if (c1 == 'E' && e == true) {
        answer += "E-A";
        index++;
      } else if (c1 == 'U' && b == false) {
        answer += "OO";
        index++;
      } else if (c1 == 'V') {
        answer += "F";
        index++;
      } else if (c1 == 'W') {
        answer += "V";
        index++;
      } else {
        index++;
        answer += c1;
      }
    }
    return answer;
  }

}
