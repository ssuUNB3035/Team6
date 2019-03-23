import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.*;

public class GUI {
		private String fileID;
		private static JTextField txtfield;
		private static JButton uploadButton;
		private JFileChooser chooser;
		
	    public static void main(String args[]){
	       JFrame frame = new JFrame("Student Transcript Analyser");
	       JPanel panel = new JPanel();
	       frame.setSize(750,500);
	       frame.add(panel);
	       txtfield = new JTextField(30);
	       uploadButton = new JButton("Upload Transcript");
	       uploadButton.setBounds(50, 150, 100, 30);
	       panel.add(txtfield);
	       panel.add(uploadButton);
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setVisible(true);
	    }
	    
	    public void actionPerformed(ActionEvent e){
	        if (e.getSource() == uploadButton){  
	        	//TO ADD: CONFIGURATION PATH FROM Transcript Reader?
	        }
	        	/*
	        	 * Possible Implementation.
	            chooser = new JFileChooser(new File(System.getProperty("User/uwesh"))); //Downloads Directory as default
	            chooser.setDialogTitle("Select Location");
	            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	            chooser.setAcceptAllFileFilterUsed(false);

				if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){ 
	                fileID = chooser.getSelectedFile().getPath();
	                txtfield.setText(fileID);
	            }
	        }*/
	    }
}