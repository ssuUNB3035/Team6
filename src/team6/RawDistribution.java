package team6;

public class RawDistribution {
	
	
	public boolean isFail(String grade) {
		if(grade.equals(LevelSchema.getMargin(0)) || grade.compareTo(LevelSchema.getMargin(0)) == -1) {
			return true;
		}
		return false;
	}
}
