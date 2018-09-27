package comment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class comment {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader GV = new BufferedReader(new FileReader("D:\\test.java"));
		String line;
		int c=0;
		// TODO Auto-generated method stub
		while((line=GV.readLine())!=null)
		{
		if(line.trim().startsWith("//"))
		{
			continue;
		}
		else if(line.trim().startsWith("/*"))
		{
			while((line=GV.readLine())!=null)
			{
				if(line.trim().endsWith("*/"))
					break;
			}
			continue;
			}
		else if(line.trim().isEmpty())
			continue;
		c++;
		}
		System.out.println(c);
		}
		
	}

