package htmlParse;

import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Stack;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class data_store 
{long starttime=0;
long endtime=0;
	@SuppressWarnings("unused")
	public void ds_str(String path) throws Exception 
	{
		file_read fr = new file_read();
		file_select fs = new file_select();
		file_store fst = new file_store();
		String pth = path + "//graphviz.gv";
		int no = fr.n_return(pth);
		data_type [] dat = fr.dat_return(pth);
		int fsn  = fst.n_return(pth);
		String [] fil = fst.str_ret(pth);
		String dst;
		Stack <String> sl_stk = new Stack <String>();
		Stack <String> sl_res = new Stack <String>();
		System.out.println(no);
		JTextField field1 = new JTextField();
		JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("the node:"));
        panel.add(field1);
        String s="Slicing";
        int result = JOptionPane.showConfirmDialog(null, panel, s ,JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) 
        {//int temp=15;
        	starttime=System.nanoTime();
            dst = String.valueOf(33);//field1.getText();
            sl_stk.add(dst);
            String temp;
            int j = 0;
            while(!sl_stk.isEmpty() && j<=no)
            {
            	j++;
            	temp = sl_stk.pop();
            	for(int i=0;i<no;i++)
            	{
            		if(dat[i].dest.equals(temp))
            		{
            			if(sl_stk.contains(dat[i].src) || sl_res.contains(dat[i].src))
            				continue;
            			else
            			{
            				//System.out.print(dat[i].src);
            				sl_res.add(dat[i].src);
            				sl_stk.add(dat[i].src);
            			}
            		}
            	}
            }
            System.out.println(sl_res);
            BufferedWriter output = new BufferedWriter(new FileWriter(path+"\\graphviz1.gv"));
            for(int i=0;i<fsn-1;i++)
            {
            	output.write(fil[i]+"\n");
            }
            for(String sr:sl_res)
            {
            	output.write(sr + " [style=filled,peripheries=2,fillcolor=cyan,color=black];\n");
            }
            output.write(dst + " [style=filled,peripheries=2,fillcolor=cyan,color=black];\n");
            output.write("}");
            output.close();
        }
        else 
        {
            System.out.println("Cancelled");
        }
        endtime=System.nanoTime();
        System.out.println(endtime-starttime);
	}
}
