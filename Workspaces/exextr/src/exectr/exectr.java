package exectr;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class exectr  {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String line;
		int c=0;
		int ctr=0;
		BufferedWriter output = new BufferedWriter(new FileWriter("d:\\newfile1.java"));
		BufferedReader javaFile = new BufferedReader(new FileReader("d:\\newfile.java"));
		output.write("import java.io.BufferedWriter;");
		output.newLine();
		output.write("import java.io.File;");
		output.newLine();
		output.write("import java.io.FileWriter;");
		output.newLine();
		output.write("import java.io.IOException;");
		output.newLine();
		while((line = javaFile.readLine()) != null){
			
			
			if(line.contains("main"))
				{c=1;
				output.write(line);
				output.append(" throws IOException");
				output.newLine();
				while((line = javaFile.readLine()) != null)
				{output.write(line);
				output.newLine();
					if(line.contains("{"))
					{
						//line=javaFile.readLine();
						output.newLine();
						output.write("BufferedWriter output = new BufferedWriter(new FileWriter(\"d:\\\\newfile1.txt\"));");
					break;
					}
				}
				ctr++;
				output.newLine();
				continue;
				}
			//output.newLine();
			if(c==0)
			{output.write(line);
				continue;
			}
			if(line.contains("{"))
				{ctr++;
				output.write(line);
				output.newLine();
				continue;
				}
			else if(line.contains("}"))
				{
				if(ctr==1)
					output.write("output.close();");
				ctr--;
				output.write(line);
				output.newLine();
				continue;
				}
			if(line.contains("if") || line.contains("else") || line.contains("while"))
			{output.write(line);
			output.newLine();
				continue;
			}
			if(ctr==0)
			{
				c=0;
				continue;
			}
		//output.write(line);
		
			//output.newLine();
			output.write(line);
			output.write("output.write(String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()));");
			output.write("output.newLine();");
			output.newLine();
		}
		javaFile.close();
		output.close();
		String[] cmd={"javac","newfile1.java"};
		/*Runtime rt=Runtime.getRuntime();
		try {
			rt.exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] cmd1={"java","newfile"};
		// rt=Runtime.getRuntime();
		try {
			rt.exec(cmd1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
