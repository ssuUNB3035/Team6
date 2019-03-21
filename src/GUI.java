import javax.swing.*;

public class GUI {

	    public static void main(String args[]){
	       JFrame frame = new JFrame("Student Transcript Analyser");
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setSize(750,500);
	       JButton uploadButton = new JButton("Upload Transcript");
	       uploadButton.setBounds(50, 150, 100, 30);
	       frame.add(uploadButton);
	       frame.setVisible(true);
	    }
}
