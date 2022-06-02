import java.io.*;

public class Main {
  
    public static String processDefault(String s, String val) {
      String out = "";
      for (int k=0; k < s.length()-val.length(); k++) out += "*";
      return out += val;
    }
  
    public static String processComma(String s, String val) {
      String out = "";
      String str = "";
      String ands = s.substring(0, s.indexOf(",")) + s.substring(s.indexOf(",")+1, s.length());
      if (val.length() > 9) {
        for (int i=0; i < val.length(); i++) {
          if(i == val.length() - 9) {
            str = val.substring(0, i) + "," + val.substring(i);
          }
        }
      } else {
        str = val;
      }
      for (int i=0; i < ands.length() - val.length(); i++) out += "*";
      return out += str;
    }

    public static String processNegativeSign(String s, String val) {
      int valLen = val.length()-1;
      int sLen = s.length()-1;
      String result = "";
      for (int i = 0; i < sLen-valLen; i++) result += "*";
      result += val.substring(1, val.length());      
      return result += "-";
    }
  
    public static String processPeriod(String s, String val) {
      String intHalf = val.substring(0, val.indexOf("."));
      int intSize = s.substring(0, s.indexOf(".")).length();
      String p1 = "";
      for (int k = 0; k < intSize; k++) p1 += "&";
      String decHalf = val.substring(val.indexOf(".") + 1);
      String decHalf2 = s.substring(s.indexOf(".") + 1);

      String out = processDefault(p1, intHalf);
      int round = 0;
      int num = 0;
      int check = 0;
      if (decHalf2.length()-decHalf.length() < 0) {
        round = decHalf2.length();
        num = Integer.parseInt(decHalf.substring(0, round));
        check = Integer.parseInt(decHalf.charAt(round) + "");
        if (check >= 5) num++;
        out += "." + num;
      } else {
        out += "." + decHalf;
        for (int k=0; k < decHalf2.length() - decHalf.length(); k++) out += "0";
      }
      return out;
    }
  
    public static String processDollarSign(String s, String val) {
      String newS = s.substring(0, s.indexOf("$")) + s.substring(s.indexOf("$") + 1);
      String help = "";
      if (newS.indexOf(".") == -1) help = processDefault("&" + newS, val);
      else help = processPeriod("&" + newS, val);
      if (s.charAt(0) == '*') return help.substring(0, help.lastIndexOf("*")-1) + "$" + help.substring(help.lastIndexOf("*") + 1);
      else return "$" + help.substring(help.lastIndexOf("*") + 1);
    }
  
    public static String findMethod(String s, String val) {
      if (s.indexOf("$") >= 0) return processDollarSign(s, val);
      else if (s.indexOf("-") >= 0) return processNegativeSign(s, val);
      else if (s.indexOf(",") >= 0) return processComma(s, val);
      else if (s.indexOf(".") >= 0) return processPeriod(s, val);
      else return processDefault(s, val);
    }
  
    public static void main(String[] args) {
      try {
        BufferedReader reader = new BufferedReader(new FileReader("Test.in"));
        String input;
        while ((input = reader.readLine()) != null) {
          System.out.printf("%s\n\n", findMethod(input.substring(0, input.indexOf(", ")), input.substring(input.indexOf(", ")+2)));
        }
        reader.close();
      } catch(IOException e) {
        System.out.println(e.getMessage());
      }
    }
}