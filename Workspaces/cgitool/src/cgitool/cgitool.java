package cgitool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class cgitool {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
int cp=0;
int c=0;
String line=null;
		BufferedWriter output = new BufferedWriter(new FileWriter("d:\\graphviz.gv"));
		output.write("digraph d {");
		output.newLine();
		final File folder = new File("C:\\Users\\Anubhav\\Workspaces\\test1\\WebContent");
		for (final File fileEntry : folder.listFiles()) {
	        if(fileEntry.getName().endsWith(".cgi")){
	        	BufferedReader jspFile = new BufferedReader(new FileReader(folder.getAbsoluteFile()+"\\"+fileEntry.getName()));
	        	output.write(cp+1+" " + "[label=\""+ fileEntry.getName()+ "\"];");
	        	while((line = jspFile.readLine()) != null){
	        		c++;
	        	if((line.trim().startsWith("<!--"))||line.trim().startsWith("#"))
	        	{
	        		continue;
	        	}
	        	else
	        	{
	        		
	        	}
	        	}
	        	}
	        }
		output.write("}");
		output.close();
	}

}
