class Main {
  
   public static void main(String[] args) {
      double[][] inputArr = {
//      AB BC AC DE EF DF 
        {2, 3, 4, 2, 3, 4}, // DEF
        {2, 3, 4, 4, 3, 2}, // DFE
        {2, 3, 4, 3, 2, 4}, // FED
        {2, 3, 4, 2, 3, 5}, // NONE
        {2, 3.5, 4, 2, 4, 3.5} // EDF
      };
      
      for(int i = 0; i < 5; i++) {
        double[] arr = inputArr[i];
        double AB = arr[0];
        double BC = arr[1];
        double AC = arr[2];
        double DE = arr[3];
        double EF = arr[4];
        double DF = arr[5];

         if( legal(AB, BC, AC) && legal(DE, EF, DF) )
            findProportionality(AB, BC, AC, DE, EF, DF);
         else
            System.out.println("NONE");  
      }

   }
   
   public static boolean legal(double x, double y, double z) {
      double max = Math.max(Math.max(x, y), z) ;
 
      if (max == x) if (max < y + z) return true;  // assumes max == x
      if (max == y) if (max < x + z) return true;  // assumes max == y 
      if (max == z) if (max < x + y) return true;  // assumes max == z   
      return false;
   }
   
   public static void findProportionality(double AB, double BC, double AC, double DE, double EF, double DF) { 
       if      ( AB/DE == BC/EF && AB/DE == AC/DF) System.out.println("DEF");
       else if ( AB/DE == BC/DF && AB/DE == AC/EF) System.out.println("EDF");
      
       else if ( AB/EF == BC/DE && AB/EF == AC/DF) System.out.println("FED");
       else if ( AB/EF == BC/DF && AB/EF == AC/DE) System.out.println("EFD");
       
       else if ( AB/DF == BC/DE && AB/DF == AC/EF) System.out.println("FDE");
       else if ( AB/DF == BC/EF && AB/DF == AC/DE) System.out.println("DFE");
       
       else System.out.println("NONE");      
   }
   
}
