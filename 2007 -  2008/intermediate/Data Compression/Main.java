import java.io.*;

class Main {
  public static void main(String[] args) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader("Test.in"));
      String line = reader.readLine();
      while (line != null) {
        System.out.println(line);
        String line2 = line;

        int counter = 0;
        String[] punc = {",", "\\.", "!", "\\?", ";", ":", "\"", "\'"};
        int[] puncNums= {0, 0, 0, 0, 0, 0, 0, 0};

        while(counter < 8) {
          puncNums[counter] = line2.length() - line2.replaceAll(punc[counter], "").length();
          line2 = line2.replaceAll(punc[counter], "");
          counter += 1;
        }

        String firstWord;
        while(!line2.equals("")) {
          int spaceIndex = line2.indexOf(' ');
          if(line2.contains(" ")) firstWord = line2.substring(0, spaceIndex);
          else firstWord = line2.substring(0, line2.length());
          
          int count = 0;
          while (line2.contains(firstWord)) {
            count += 1;
            line2 = line2.replaceFirst(firstWord, "").trim();
          }
          System.out.print(count + firstWord);
        }
        String[] puncNames = {",", ".", "!", "?", ";", ":", "\"", "\'"};
        for (int i = 0; i < 8; i++) if (puncNums[i] != 0) System.out.print(puncNums[i] + puncNames[i]);
        System.out.println("\n");
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
}
