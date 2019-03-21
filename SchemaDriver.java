import java.util.Scanner;

public class SchemaDriver {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[] mark = new char[4];
        for(int i=0; i<4; i++) {
            mark[i] = input.next().charAt(0);
        }

        LevelSchema makeGrades = new LevelSchema(mark);

        System.out.println("Failing grade: " + makeGrades.getFail());
        System.out.println("Marginal grade: " + makeGrades.getMarginal());
        System.out.println("Passing grade: " + makeGrades.getMeet());
        System.out.println("Exceptional grade: " + makeGrades.getExceed());
    }
}