package htmlParse;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class parse {


	public static void main(String[] args) throws IOException {
		int cp=0;
		int c=0;
		long starttime=0;
		long endtime=0;
		starttime=System.nanoTime();
		Map<String,Integer> actionMap=new HashMap<String,Integer>();
		Map<String,Integer> filenameMap=new HashMap<String,Integer>();
		Map<String,Integer> exitMap=new HashMap<String,Integer>();
		Map<String,Integer> reqMap=new HashMap<String,Integer>();
		Map<String,Integer> paramin=new HashMap<String,Integer>();
		BufferedWriter output = new BufferedWriter(new FileWriter("d:\\graphviz.gv"));
		output.write("digraph d {");
		output.newLine();
		
		final File folder = new File("C:\\Users\\Anubhav\\Workspaces\\minmax\\WebContent");
		for (final File fileEntry : folder.listFiles()) {
	        if(fileEntry.getName().endsWith(".jsp")){
		filenameMap.put(fileEntry.getName(), cp+1);
		int store = 0;
		Map<String,Integer> funcMap=new HashMap<String,Integer>();
		Map<String,Integer> varMap=new HashMap<String,Integer>();
		Map<String,Integer> retMap=new HashMap<String,Integer>();
		Map<String,Integer> funcRetMap=new HashMap<String,Integer>();
		Map<String,Integer> inputval=new HashMap<String,Integer>();
		Map<String,Integer> scriptvarMap=new HashMap<String,Integer>();
		funcMap.clear();
		varMap.clear();
		retMap.clear();
		funcRetMap.clear();
		inputval.clear();
		scriptvarMap.clear();
		BufferedReader jspFile = new BufferedReader(new FileReader(folder.getAbsoluteFile()+"\\"+fileEntry.getName()));
		
		output.write(cp+1+" " + "[label=\""+ fileEntry.getName()+ "\"];");
		output.newLine();
		output.write(cp+1+"[shape=polygon,style=filled,peripheries=2,fillcolor=yellow,color=black,sides=4];");
		output.newLine();
		
		String line;
		
		int cform=0,cinput=0,cscript=0,cif=0,celse=0,caout=0;
		String formName = null;
		while((line = jspFile.readLine()) != null){
			
			c++;
			if((line.trim().startsWith("<!--"))||line.trim().startsWith("//"))
				continue;
			if(line.startsWith("<form")){
			String snew = null; 
					snew=line.split("action=\"")[1];snew=snew.split("\"")[0];
					actionMap.put(snew, c);
				cform=c;
				if(line.contains("name"))
					formName=line.split("name=\"")[1].split("\"")[0];
				output.write(String.valueOf(c)+" " + "[label=\""+ "<form> ("+String.valueOf(c-cp)+")" + "\"];");
				output.newLine();
				output.write(String.valueOf(c)+"[shape=ellipse,color=black,sides=4];");
				output.newLine();
				output.write(cp+1+" -> "+String.valueOf(cform));
				output.newLine();
			}
			else if(line.startsWith("<input")){
				cinput=c;
				output.write(String.valueOf(c)+" " + "[label=\""+ "<input> ("+String.valueOf(c-cp)+")" + "\"];");
				output.newLine();
				output.write(String.valueOf(c)+"[shape=ellipse,color=black,sides=4];");
				output.newLine();
				output.write(String.valueOf(cform)+" -> "+String.valueOf(cinput));
				output.newLine();
				
				if(line.contains("name")){
					String name=line.split("name=\"")[1].split("\"")[0];
					name=formName+"_"+name;
					paramin.put(name, cinput);
					if(varMap.containsKey(name)){
						System.out.println(varMap.get(name));
					}
					if(varMap.get(name)!=null){
						output.write(String.valueOf(cinput)+" -> "+String.valueOf(varMap.get(name)));
						output.write(" [style=dashed, penwidth=2 color=brown]");
		   			    output.newLine();
					}
				}
				
				if(line.toLowerCase().contains("onclick")){
					String words[]=line.split(" ");
					String func = null;
					for(String word:words){
						if(word.contains("()"))
								func=word;
					}
					if(func!=null)
					{
					func=func.split("\"")[0];
					int cfunc=funcMap.get(func);
					
					output.write(String.valueOf(cinput)+" -> "+String.valueOf(cfunc));
					output.write(" [style=dotted, penwidth=2]");
	   			    output.newLine();
	   			    
	   			    funcRetMap.put(func, cinput);
					}
				}
			}
			else if(line.startsWith("<script")){
				String name = null;
				while(((line = jspFile.readLine()) != null)&&!(line.startsWith("</script"))){
					c++;
					int st;
					String	lname1;
					for(int i=0;i<scriptvarMap.size();i++)
					{
						
						lname1=(String) scriptvarMap.keySet().toArray()[i];
						System.out.println(scriptvarMap.size()+"hello"+lname1);
						//String name1=line.split();
						if(line.contains("document.forms"))
						{
							
						}
						else
						{
						if(line.contains(lname1))
						{ System.out.println(c+lname1);
						int chk,chk1;
						chk=line.lastIndexOf(lname1);
						chk1=line.indexOf(lname1);
						//System.out.println(chk+"anubhav");
						//System.out.println(chk1+"anubhav");
						//System.out.println(chk1);
						if(!Character.isLetter(line.charAt(chk+1))  && !Character.isLetter(line.charAt(chk-1)))
						{				//System.out.println();
							 st = scriptvarMap.get(lname1);
					//	System.out.println(st);
						//	if(store!=c)
							//	output.newLine();
							output.write(st+" -> "+String.valueOf(c));
							output.write(" [style=dashed, penwidth=2]");
							output.newLine();
							store=c;
						}
						}
						}
					}
				
					if(line.contains("return")&&name!=null){
						retMap.put((name+"_"+String.valueOf(c)), c);
					}
					
					if(line.startsWith("function")){
						cscript=c;
						caout--;
						String words[]=line.split(" ");
						name=words[1];
						funcMap.put(name, cscript);
						output.write(String.valueOf(c)+" " + "[label=\""+ name+"("+String.valueOf(c-cp)+")" + "\"];");
						output.newLine();
						output.write(String.valueOf(c)+"[shape=ellipse,color=black,sides=4];");
						output.newLine();
						output.write(cp+1+" -> "+String.valueOf(cscript));
						output.newLine();
					}
					else if(line.trim().startsWith("if")){
						cif=c;
						output.write(String.valueOf(c)+" " + "[label=\""+ "if(" +String.valueOf(c-cp)+")"+ "\"];");
						output.newLine();
						output.write(String.valueOf(c)+"[shape=ellipse,color=black,sides=4];");
						output.newLine();
						output.write(String.valueOf(cscript)+" -> "+String.valueOf(cif));
						output.newLine();
						line=jspFile.readLine();
						c++;
						if(line.trim().startsWith("{")){
							while(((line = jspFile.readLine()) != null)&&!(line.trim().startsWith("}"))){
								c++;
								output.write(String.valueOf(c)+" " + "[label=\""+ String.valueOf(c-cp) + "\"];");
								output.newLine();
								output.write(String.valueOf(c)+"[shape=ellipse,color=black,sides=4];");
								output.newLine();
								output.write(String.valueOf(cif)+" -> "+String.valueOf(c));
								output.newLine();
								
								if(line.contains("return")&&name!=null){
									retMap.put((name+"_"+String.valueOf(c)), c);
								}
							}
							c++;
						}
						else {
							output.write(String.valueOf(c)+" " + "[label=\""+ String.valueOf(c-cp) + "\"];");
							output.newLine();
							output.write(String.valueOf(c)+"[shape=ellipse,color=black,sides=4];");
							output.newLine();
							output.write(String.valueOf(cif)+" -> "+String.valueOf(c));
							output.newLine();
							
							if(line.contains("return")&&name!=null){
								retMap.put((name+"_"+String.valueOf(c)), c);
							}
						}
						
					}
					else if(line.trim().startsWith("else")){
						celse=c;
						output.write(String.valueOf(c)+" " + "[label=\""+ "else(" +String.valueOf(c-cp)+")"+ "\"];");
						output.newLine();
						output.write(String.valueOf(c)+"[shape=ellipse,color=black,sides=4];");
						output.newLine();
						output.write(String.valueOf(cif)+" -> "+String.valueOf(celse));
						output.newLine();
						line=jspFile.readLine();
						c++;
						if(line.trim().startsWith("{")){
							while(((line = jspFile.readLine()) != null)&&!(line.trim().startsWith("}"))){
								c++;
								output.write(String.valueOf(c)+" " + "[label=\""+ String.valueOf(c-cp) + "\"];");
								output.newLine();
								output.write(String.valueOf(c)+"[shape=ellipse,color=black,sides=4];");
								output.newLine();
								output.write(String.valueOf(celse)+" -> "+String.valueOf(c));
								output.newLine();
								
								if(line.contains("return")&&name!=null){
									retMap.put((name+"_"+String.valueOf(c)), c);
								}
							}
							c++;
						}
						else {
							output.write(String.valueOf(c)+" " + "[label=\""+ String.valueOf(c-cp) + "\"];");
							output.newLine();
							output.write(String.valueOf(c)+"[shape=ellipse,color=black,sides=4];");
							output.newLine();
							output.write(String.valueOf(celse)+" -> "+String.valueOf(c));
							output.newLine();
							
							if(line.contains("return")&&name!=null){
								retMap.put((name+"_"+String.valueOf(c)), c);
							}
						}
					}
					else if(line.startsWith("}")){
						
					}
					else {
						output.write(String.valueOf(c)+" " + "[label=\""+ String.valueOf(c-cp) + "\"];");
						output.newLine();
						output.write(String.valueOf(c)+"[shape=ellipse,color=black,sides=4];");
						output.newLine();
						output.write(String.valueOf(cscript)+" -> "+String.valueOf(c));
						output.newLine();
					}
					
					if(line.contains("document.forms")){
						String words[]=line.split("\"");
						String key=words[1]+"_"+words[3];
						varMap.put(key, c);
						String varscript=line.trim().split(" ")[1];
						System.out.println(varscript+"anubhavpandascript");
						scriptvarMap.put(varscript, c);
					}
				}
				c++;
			}
			else if(line.trim().startsWith("<%") && c>cp+1)
			{
				//c++;
				int cjsp=c;
				System.out.println(String.valueOf(c));
				//caout--;
				//String words[]=line.split(" ");
				//name="script";
		//		funcMap.put(name, cscript);
				output.write(String.valueOf(c)+" " + "[label=\""+ "jsp"+"("+String.valueOf(c-cp)+")" + "\"];");
				output.newLine();
				output.write(String.valueOf(c)+"[shape=ellipse,color=black,sides=4];");
				output.newLine();
				output.write(cp+1+" -> "+String.valueOf(cjsp));
				output.newLine();
				while(((line = jspFile.readLine()) != null)&&!(line.contains("%>")))
				{
					c++;
					if(line.contains("="))
					{
						String s1=line.split("=")[0];
						if(!inputval.containsKey(s1))
						{
							inputval.put(s1, c);
						}
					}
					if(line.contains("if")||line.contains("for"))
					{
						//else if(line.trim().startsWith("if")){
							cif=c;
							if(line.contains("if"))
							output.write(String.valueOf(c)+" " + "[label=\""+ "if(" +String.valueOf(c-cp)+")"+ "\"];");
							else if(line.contains("for")) 
								output.write(String.valueOf(c)+" " + "[label=\""+ "for(" +String.valueOf(c-cp)+")"+ "\"];");
							output.newLine();
							output.write(String.valueOf(c)+"[shape=ellipse,color=black,sides=4];");
							output.newLine();
							output.write(String.valueOf(cjsp)+" -> "+String.valueOf(cif));
							output.newLine();
							line=jspFile.readLine();
							c++;
							if(line.trim().startsWith("{")){
								while(((line = jspFile.readLine()) != null)&&!(line.trim().startsWith("}"))){
									c++;
									output.write(String.valueOf(c)+" " + "[label=\""+ String.valueOf(c-cp) + "\"];");
									output.newLine();
									output.write(String.valueOf(c)+"[shape=ellipse,color=black,sides=4];");
									output.newLine();
									output.write(String.valueOf(cif)+" -> "+String.valueOf(c));
									output.newLine();
									
									if(line.contains("response.sendRedirect"))
									{
										String var1=null;
										var1=line.split("\\?")[1];
										String var2[]=var1.split("&");
										for(String var3:var2)
										{
											var3=var3.split("=")[0];
											exitMap.put(var3+"*"+String.valueOf(c),c);
										}
									}
									
								}
								c++;
							}
							else {
								output.write(String.valueOf(c)+" " + "[label=\""+ String.valueOf(c-cp) + "\"];");
								output.newLine();
								output.write(String.valueOf(c)+"[shape=ellipse,color=black,sides=4];");
								output.newLine();
								output.write(String.valueOf(cif)+" -> "+String.valueOf(c));
								output.newLine();
								if(line.contains("response.sendRedirect"))
								{
									String var1=null;
									var1=line.split("\\?")[1];
									String var2[]=var1.split("&");
									for(String var3:var2)
									{
										var3=var3.split("=")[0];
										exitMap.put(var3+"*"+String.valueOf(c),c);
									}
								}
						//		if(line.contains("return")&&name!=null){
							//		retMap.put((name+"_"+String.valueOf(c)), c);
								//}
							}
						
					}
					else if(line.contains("else"))
					{
						celse=c;
						output.write(String.valueOf(c)+" " + "[label=\""+ "else(" +String.valueOf(c-cp)+")"+ "\"];");
						output.newLine();
						output.write(String.valueOf(c)+"[shape=ellipse,color=black,sides=4];");
						output.newLine();
						output.write(String.valueOf(cif)+" -> "+String.valueOf(celse));
						output.newLine();
						line=jspFile.readLine();
						c++;
						if(line.trim().startsWith("{")){
							while(((line = jspFile.readLine()) != null)&&!(line.trim().startsWith("}"))){
								c++;
								output.write(String.valueOf(c)+" " + "[label=\""+ String.valueOf(c-cp) + "\"];");
								output.newLine();
								output.write(String.valueOf(c)+"[shape=ellipse,color=black,sides=4];");
								output.newLine();
								output.write(String.valueOf(celse)+" -> "+String.valueOf(c));
								output.newLine();
								if(line.contains("response.sendRedirect"))
								{
									String var1=null;
									var1=line.split("\\?")[1];
									String var2[]=var1.split("&");
									for(String var3:var2)
									{
										var3=var3.split("=")[0];
										exitMap.put(var3+"*"+String.valueOf(c),c);
									}
								}
							}
						//		if(line.contains("return")&&name!=null){
							//		retMap.put((name+"_"+String.valueOf(c)), c);
						}
						if(line.contains("response.sendRedirect"))
						{
							String var1=null;
							var1=line.split("\\?")[1];
							String var2[]=var1.split("&");
							for(String var3:var2)
							{
								var3=var3.split("=")[0];
								exitMap.put(var3+"*"+String.valueOf(c),c);
							}
						}
					}
							
					else if(line.contains("request.getParameter"))
					{ //String name;
						
						String name[]=line.split("=");
						
					inputval.put(name[0],c);
					System.out.println(name[0]+"anubhavpandanew12");
					String var45=line.split("\"")[1];
					
					
					reqMap.put(var45, c);
						//inputval.put("", c);
						output.write(String.valueOf(c)+" " + "[label=\""+"("+String.valueOf(c-cp)+")" + "\"];");
						output.newLine();
						output.write(String.valueOf(c)+"[shape=ellipse,color=black,sides=4];");
						output.newLine();
						output.write(cjsp+" -> "+String.valueOf(c));
						output.newLine();
					}
					else if(line.contains("response.sendRedirect"))
					{
						output.write(String.valueOf(c)+" " + "[label=\""+"("+String.valueOf(c-cp)+")" + "\"];");
						output.newLine();
						output.write(String.valueOf(c)+"[shape=ellipse,color=black,sides=4];");
						output.newLine();
						output.write(cjsp+" -> "+String.valueOf(c));
						output.newLine();
						
							String var1=null;
							var1=line.split("\\?")[1];
							String var2[]=var1.split("&");
							for(String var3:var2)
							{
								var3=var3.split("=")[0];
								exitMap.put(var3+"*"+String.valueOf(c),c);
							}
						
					}
					else
					{
						output.write(String.valueOf(c)+" " + "[label=\""+"("+String.valueOf(c-cp)+")" + "\"];");
						output.newLine();
						output.write(String.valueOf(c)+"[shape=ellipse,color=black,sides=4];");
						output.newLine();
						output.write(cjsp+" -> "+String.valueOf(c));
						output.newLine();
					}
					int st;
					String lname1="hgkgjjfdjfglg";
					//System.out.println(inputval.size());
					int i=0;
					for( i=0;i<inputval.size();i++)
					{
						
						lname1=(String) inputval.keySet().toArray()[i];
						System.out.println(lname1+"abcd");
						//String name1=line.split();
						if(inputval.get(lname1)==c)
						{
							
						}
						else
						{
						if(line.contains(lname1))
						{ System.out.println(c+lname1);
						int chk,chk1;
						chk=line.lastIndexOf(lname1);
						chk1=line.indexOf(lname1);
						System.out.println(chk);
						System.out.println(chk1);
						System.out.println(chk1);
						if(!Character.isLetter(line.charAt(chk+1))  && !Character.isLetter(line.charAt(chk1-1)))
						{				//System.out.println();
							st=inputval.get(lname1);
						System.out.println(st+"anubhavpanda206"+lname1);
						//	if(store!=c)
							//	output.newLine();
							output.write(st+" -> "+String.valueOf(c));
							output.write(" [style=dashed, penwidth=2]");
							output.newLine();
							store=c;
						}
						}
						}
					}
				}
				
				c++;
			}
		}
		if(!retMap.isEmpty()){
			String fname="abc";
			String fname2=fname;
			for(int i=0;i<retMap.size();i++){
				fname=(String) retMap.keySet().toArray()[i];
				int cRet=retMap.get(fname);
				fname=fname.split("_")[0];
				//System.out.println(fname);
				if(!fname2.equals(fname)){
					c++;
					cp++;
					caout=c;
					output.write(caout+" " + "[label=\""+ fname+" # A out" + "\"];");
					output.newLine();
					output.write(caout+"[shape=ellipse,color=black,sides=4];");
					output.newLine();
					
					int top=funcMap.get(fname);
					output.write(String.valueOf(top)+" -> "+String.valueOf(caout));
					output.newLine();
					if(!funcRetMap.isEmpty())
					{int bottom=funcRetMap.get(fname);
					output.write(String.valueOf(caout)+" -> "+String.valueOf(bottom));
					output.write(" [style=dashed, penwidth=2 color=red]");
					output.newLine();
					}
				}
				output.write(String.valueOf(cRet)+" -> "+String.valueOf(caout));
				output.write(" [style=dashed, penwidth=2 color=red]");
				output.newLine();
				fname2=fname;
				//System.out.println(fname2);
			}
		}
		jspFile.close();
		cp=c;
//		Desktop dk=Desktop.getDesktop();
	//	dk.open(new File("C:\\Users\\Ankit\\Desktop\\graph.gv"));
}
		}
		for (String snew1 : actionMap.keySet()) {
			int in=actionMap.get(snew1);
			int out=filenameMap.get(snew1);
			output.write(String.valueOf(in)+" -> "+String.valueOf(out));
			output.write(" [style=dotted, penwidth=2 ]");
			output.newLine();
		}
		for (String snew11 : exitMap.keySet()) {
			int in=exitMap.get(snew11);
			snew11=snew11.split("\\*")[0];
			int out=reqMap.get(snew11);
			output.write(String.valueOf(in)+" -> "+String.valueOf(out));
			output.write(" [style=dashed, penwidth=2 color=red ]");
			output.newLine();
			System.out.print(snew11);
		}
		if(reqMap.size()>0)
		{
		for(String sip:paramin.keySet())
		{
			try
			{ 
				System.out.println("final"+sip);
				if(reqMap.get(sip.split("_")[1])!=null)
				{output.write(String.valueOf(paramin.get(sip))+" -> "+String.valueOf(reqMap.get(sip.split("_")[1])));
				output.write(" [style=dashed, penwidth=2 color=brown]");
   			    output.newLine();
				}
			} catch(Exception e){
		}
		}
		}
		output.write("}");
		
		output.close();
		data_store ds = new data_store();
		String outFolderPath="d:";
        try {
			ds.ds_str(outFolderPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        endtime=System.nanoTime();
        System.out.println(endtime-starttime);
	}
}
