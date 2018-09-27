package htmlParse;

import java.io.BufferedReader;
import java.io.FileReader;
public class file_store 
{
	int i;
	int n_return (String pth) throws Exception
	{
		i = 0;
		FileReader fr = new FileReader(pth);
		BufferedReader br = new BufferedReader(fr);
		while((br.readLine()) != null)
		{
			i++;
		}
		fr.close();
		return i;
	}
	String[] str_ret(String pth) throws Exception
	{
		String [] st = new String[i];
		for(int j=0;j<i;j++)
		{
			st[j] = "";
		}
		String s = null;
		int l=0;
		FileReader fr = new FileReader(pth);
		BufferedReader br  = new BufferedReader(fr);
		while((s = br.readLine()) != null) 
		{
			st[l] = s;
			l++;
		}
		return st;
		
	}

}