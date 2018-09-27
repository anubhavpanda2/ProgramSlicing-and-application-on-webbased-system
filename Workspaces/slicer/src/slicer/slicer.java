package slicer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class slicer {
	 static
	int[][] mat;
	static int [] node;
	static int i;
public void slicecomputation(int sc)
{
//	int sc=15;
	if(node[sc]!=1)
	{
	node[sc]=1;
	for(int j=1;j<i;j++)
	{
		if(mat[j][sc]==1)
		{
			slicecomputation(j);
		}
	}
	}
}
	public static void main(String[] args) throws IOException {
		
		BufferedReader GV = new BufferedReader(new FileReader("D:\\graphviz.gv"));
String line;
 i=0;
 int t;
String linearr[],liner;
		while((line=GV.readLine())!=null)
		{
			/*if(line.contains("->"))
			{
				linearr=line.replaceAll(" ", "").split("->");
				if(line.contains("["))
				{
					linearr[1]=linearr[1].split("\\[")[0];
				}
				if((t=Integer.parseInt(linearr[0]))>i)
				{
					i=t;
				}
				if((t=Integer.parseInt(linearr[1]))>i)
				{
					i=t;
				}
			}*/
			if(line.contains("shape"))
			{	liner= line.split("\\[")[0];
				if((t=Integer.valueOf(liner))>i)
			i=t;
			}
		}
			i++;
			System.out.println(i);
			
		 mat=new int[i][i];
		 node=new int[i];
	 GV = new BufferedReader(new FileReader("D:\\graphviz.gv"));
		String nodes[];
//		int i=0;
				while((line=GV.readLine())!=null)
				{
					if(line.contains("->"))
					{
					nodes=line.replaceAll(" ", "").split("->");
					if(line.contains("["))
					{
						nodes[1]=nodes[1].split("\\[")[0];
					}
					mat[Integer.parseInt(nodes[0])][Integer.parseInt(nodes[1])]=1;
					}
				}
				GV.close();
				long starttime=System.nanoTime();
			/*	BufferedWriter out = new BufferedWriter(new FileWriter("D://lol.txt"));
				for(int j=0;j<i;j++)
				{
					for(int k=0;k<i;k++)
					{
						out.write(String.valueOf(mat[j][k]));
					}
					out.newLine();
				}*/
				
				slicer s=new slicer();
				s.slicecomputation(29);
				
		/*		for(int j=0;j<i;j++)
				{
					out.write(String.valueOf(node[j]));
				out.newLine();
				}
				out.close();
				*/
				BufferedWriter outnew = new BufferedWriter(new FileWriter("D://graphvizslice.gv"));
				GV=new BufferedReader(new FileReader("D:\\graphviz.gv"));
				String nno;
				int tmp;
				while((line=GV.readLine())!=null)
				{
				if(line.contains("shape"))	
				{
					nno=line.split("\\[")[0];
					
					tmp=Integer.valueOf(nno);
					if(node[tmp]==1)
					{
						System.out.println("hello"+tmp);
						if(line.contains("]"))
						{
						line=line.replace("]", ",style=filled,fillcolor=cyan]");
						System.out.println(line);
						}
						outnew.write(line);
						outnew.newLine();
					}
					else
					{
						outnew.write(line);
						outnew.newLine();
					}
				}
				else
				{
					outnew.write(line);
					outnew.newLine();
				}
				}
				outnew.close();
				long endtime=System.nanoTime();
				System.out.println(endtime-starttime);
				}
	}