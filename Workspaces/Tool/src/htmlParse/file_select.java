package htmlParse;

import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
public class file_select 
{
	String pth() throws FileNotFoundException
	{
	    JFileChooser chooser=new  JFileChooser();
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) 
	    {
	    	String st= (chooser.getSelectedFile()).toString();
	    	return st;
	    }
	    return "";
	}

}
