package team6;

/**
 * @author Keith LeBlanc 3575020
 */
import java.io.*;
import java.util.Scanner;

public class LevelSchema {
   private static final int GRADELIST = 5;
   private static String[] margins = new String[GRADELIST];

/**
* @return dir The path of the config file
*/
   public static File getDirectory(){
       File path = new File("marginsConfig.txt");
       File dir = null;
       Scanner sc = null;        
       
       try {
           sc = new Scanner(path);
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }
       
       String directoryPath;
       directoryPath = sc.nextLine();
       dir = new File(directoryPath);
       
       return dir;
   }

/**
* @param gradesSet The list of grades in the form of a string
*/
   public static void setMargins(String gradesSet) {
       gradesSet.trim();
       int i=0;

       try {
           for(int j=0; j<gradesSet.length(); j++) {
               margins[i]=gradesSet.charAt(j) + "";

               if(gradesSet.charAt(j) != ',') {
                   margins[i]=gradesSet.charAt(j) + "";
                   i++; 

                   if(i>GRADELIST-2) {
                       break;
                   }
               }
           }

           margins[i]=gradesSet.substring(8);
       }

       catch(NullPointerException e) {
           e.printStackTrace();
       }
   }
   
/**
* @param level The type of grade to be returned (0=fail, 1=marginal, 2=meets, 3=exceeds, 4=other)
* @return margins[level] The value of the specified grade
*/
   public static String getMargin(int level) {
       return margins[level];
   }
}