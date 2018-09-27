package htmlParse;

import java.io.BufferedReader;
import java.io.FileReader;
public class file_read 
{
	int i;
	int n_return (String pth) throws Exception
	{
		i = 0;
		String temp;
		FileReader fr = new FileReader(pth);
		BufferedReader br = new BufferedReader(fr);
		while((temp = br.readLine()) != null)
		{
			if(temp.contains("->"))
				i++;
		}
		fr.close();
		return i;
	}
	data_type[] dat_return(String pth) throws Exception
	{
		data_type [] dat = new data_type[i];
		for(int j=0;j<i;j++)
		{
			dat[j] = new data_type();
		}
		String s = null;
		int k=0,l=0;
		FileReader fr = new FileReader(pth);
		BufferedReader br  = new BufferedReader(fr);
		while((s = br.readLine()) != null) 
		{
			k = 0;
			String temp[] = {"",""};
			if(s.contains("->"))
			{
				for(int j=0;j<s.length();j++)
				{
					if(s.charAt(j)!='-' && s.charAt(j)!='>' && s.charAt(j)!=' ' && s.charAt(j)!='[')
					{
						temp[k] += Character.toString(s.charAt(j));
					}
					else if(s.charAt(j)=='-' && s.charAt(j+1)=='>')
						k++;
					else if(s.charAt(j) == '[')
						break;
				}
				dat[l].src = temp[0];
				dat[l].dest = temp[1];
				System.out.println("src : "+dat[l].src+" : dest : "+dat[l].dest);
				l++;
			}
		}
		return dat;
	}
}

