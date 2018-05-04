import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.derby.impl.sql.catalog.SYSPERMSRowFactory;

public class TsetFileChoose {
	public static void main(String[] args) {
		
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jFileChooser.showOpenDialog(null);
		
		String filePathAndName = jFileChooser.getSelectedFile().getPath();
		System.out.println(filePathAndName+"\\a.txt");
	}

}
