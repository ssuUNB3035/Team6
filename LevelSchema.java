/**
 * @author Keith LeBlanc 3575020
 */
public class LevelSchema {
    private char failGrade, marginalGrade, meetGrade, exceedGrade;
    
    //TODO: pass in default bounds from config files

    private char failDefault = 'F';
    private char marginalDefault = 'D';
    private char meetDefault = 'C';
    private char exceedDefault = 'A';

    private String other;

    public LevelSchema(char[] gradeBounds) {
        this.failGrade = gradeBounds[0] == '\u0000' ? failDefault : gradeBounds[0];
        this.marginalGrade = gradeBounds[1] == '\u0000' ? marginalDefault : gradeBounds[1];
        this.meetGrade = gradeBounds[2] == '\u0000' ? meetDefault : gradeBounds[2];
        this.exceedGrade = gradeBounds[3] == '\u0000' ? exceedDefault : gradeBounds[3];
    }
    
    public LevelSchema(char[] gradeBounds, String other) {
        this.failGrade = gradeBounds[0] == '\u0000' ? failDefault : gradeBounds[0];
        this.marginalGrade = gradeBounds[1] == '\u0000' ? failDefault : gradeBounds[1];
        this.meetGrade = gradeBounds[2] == '\u0000' ? failDefault : gradeBounds[2];
        this.meetGrade = gradeBounds[3] == '\u0000' ? failDefault : gradeBounds[3];

        if(other != null) {
            this.other = other;
        }
        else {
            this.other = "";
        }
    }

    public char getFail() {
        return failGrade;
    }

    public char getMarginal() {
        return marginalGrade;
    }   
    
    public char getMeet() {
        return meetGrade;
    }   
    
    public char getExceed() {
        return exceedGrade;
    }   
    
    public String getOther() {
        return other;
    }
}