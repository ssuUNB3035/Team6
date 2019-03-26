import java.io.*;

public class SchemaDriver {
    public static void main(String[] args) {
	    File gradeDirectory = null;
        gradeDirectory = LevelSchema.getDirectory();
        LevelSchema.setMargins(gradeDirectory + "");        

        System.out.println("\nFailing grade: " + LevelSchema.getMargin(0));
        System.out.println("Marginal grade: " + LevelSchema.getMargin(1));
        System.out.println("Passing grade: " + LevelSchema.getMargin(2));
        System.out.println("Exceptional grade: " + LevelSchema.getMargin(3));
        System.out.println("Other: " + LevelSchema.getMargin(4));

    }
}
