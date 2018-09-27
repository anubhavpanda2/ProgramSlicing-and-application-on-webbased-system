package algorithm;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
public class algorithm {
	// static
		//int[][] mat;
		//static int [] node;
		//static int i;
	/**
	 * @param args
	 * @throws throws IOException 
	 */
		 static
			int[][] mat;
			static int [] node;
			static int i;

			public static void main(String[] args) throws NumberFormatException, IOException {
				// TODO Auto-generated method st
				
				BufferedReader GV = new BufferedReader(new FileReader("D:\\graphviznew.gv"));
				String line;
				Map<Integer,Integer> paramin=new HashMap<Integer,Integer>();
				Map<Integer,Integer> paramout=new HashMap<Integer,Integer>();
			 i=0;
				 int t;
				 
				String linearr[],liner;
				while((line=GV.readLine())!=null)
				{
					if(line.contains("->"))
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
					}
				}
					i++;
					//System.out.println(i);
				 mat=new int[i][i];
				 node=new int[i];
			 GV = new BufferedReader(new FileReader("D:\\graphviznew.gv"));
				String nodes[];
				String snew;
//				int i=0;
						while((line=GV.readLine())!=null)
						{
							if(line.contains("->"))
							{
							nodes=line.replaceAll(" ", "").split("->");
							if(line.contains("["))
							{
								nodes[1]=nodes[1].split("\\[")[0];
							}
						if(line.contains("red"))
							{
							snew=line.split("label")[1];
							//System.out.println(snew);
							snew=snew.split("]")[0];
							snew=snew.split("=")[1];
							snew=snew.trim();
							System.out.println(snew);
							mat[Integer.parseInt(nodes[0])][Integer.parseInt(nodes[1])]=2;
							paramout.put(Integer.parseInt(nodes[1]),Integer.valueOf(snew));
							System.out.println(Integer.parseInt(nodes[1])+"  "+Integer.valueOf(snew));
							}
						else if(line.contains("brown"))
						{
							mat[Integer.parseInt(nodes[0])][Integer.parseInt(nodes[1])]=3;
							snew=line.split("label")[1];
							//System.out.println(snew);
							snew=snew.split("]")[0];
							snew=snew.split("=")[1];
							snew=snew.trim();
							paramin.put(Integer.parseInt(nodes[0]),Integer.valueOf(snew));
						}
						else if(line.contains("green"))
						{
							mat[Integer.parseInt(nodes[0])][Integer.parseInt(nodes[1])]=4;
							
							////System.out.println("paramin"+Integer.parseInt(nodes[0])+Integer.valueOf(snew))
						}
						else
							mat[Integer.parseInt(nodes[0])][Integer.parseInt(nodes[1])]=1;
	//System.out.println(line);
							}
						}
						GV.close();
						long starttime=System.nanoTime();
						//test
						BufferedWriter out = new BufferedWriter(new FileWriter("D://lol.txt"));
						for(int j=0;j<i;j++)
						{
							for(int k=0;k<i;k++)
							{
								out.write(String.valueOf(mat[j][k]));
							}
							out.newLine();
						}
						out.close();
						//test
					//	int[] w1=new int[i];
						//int[] w2=new int[i];
						//int[] s=new int[i];
						 Deque<Integer> w1 = new ArrayDeque<Integer>();
						 Deque<Integer> w2 = new ArrayDeque<Integer>();
						 Deque<Integer> s = new ArrayDeque<Integer>();
						 Deque<Integer> s1 = new ArrayDeque<Integer>();
						int sc=18;
						//phase1
						w1.push(sc);
						s.push(sc);
						if(paramout.containsKey(sc))
						{
							s1.push(paramout.get(sc));
							System.out.println(paramout.get(sc));
						}
						int temp;
						int temp1;
						do
						{
							temp=w1.pop();
							System.out.println("w1"+ temp);
							for (int j=1;j<i;j++)
							{
								temp1=mat[j][temp];
								if(temp1>0)
								{
								if(!s.contains(j))
								{
									s.push(j);
									if(temp1!=2)
										{
										w1.push(j);
										}
									else
									{
										System.out.println("hi"+j);
										w2.push(j);
										if(paramout.containsKey(j))
										{
											s1.push(paramout.get(j));
											System.out.println(paramout.get(j));
										}
									}
										
								}
								}	
							}
						}while(!w1.isEmpty());
						//phase2
						int temp2=0;
						int temp3=0;
						do
						{
							if(!w2.isEmpty())
							temp2=w2.pop();
							//System.out.println("w2"+temp);
							for(int j=1; j<i ;j++)
							{
								
								temp1=mat[j][temp2];
								
								if(temp1>0)
								{
									//System.out.println("w21b "+temp1+ " "+j);
									if(!s.contains(j))
									{
										if(temp1!=3 && temp1 !=4)
										{w2.push(j);
										s.push(j);
										}
										else if(temp1==3)
										{
											if(paramin.containsKey(j)&&s1.contains(paramin.get(j)))
											{temp3=paramin.get(j);
												System.out.println(paramin.get(j));
												w2.push(j);
												s.push(j);
											}
										}
									}
									
								}
							}
							//System.out.println(w2.size());
						}while(!w2.isEmpty());
						//
					/*	s1=s;
						while(!s1.isEmpty())
						{
							//System.out.println(s1.pop());
						}
						
						*/
						//
			BufferedWriter outnew = new BufferedWriter(new FileWriter("D://graphvizslicenew.gv"));
			GV=new BufferedReader(new FileReader("D:\\graphviznew.gv"));
			String nno;
			int tmp;
//			String line;
			while((line=GV.readLine())!=null)
			{
			if(!line.contains("->") && !line.contains("{") && !line.contains("}")  && line.contains("label"))	
			{
				nno=line.split("\\[")[0];
				//System.out.println(nno);
				nno=nno.trim();
				tmp=Integer.valueOf(nno);
				if(s.contains(tmp))
				{
					////System.out.println("hello"+tmp);
					if(line.contains("]"))
					{
					line=line.replace("]", ",style=filled,fillcolor=cyan]");
					////System.out.println(line);
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
			//System.out.println(endtime-starttime);
			}
}