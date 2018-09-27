import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;

public class frontend {
	JFrame f;
	JSplitPane splitPane;
	JPanel leftComponent,rightComponent;
	JLabel label,lbl;
	JFileChooser jf;
	File input;
	ImageIcon imageIcon;
	Image img;
	frontend(){
		lbl=new JLabel();
		f=new JFrame();
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		leftComponent = new JPanel(new GridBagLayout());
		rightComponent = new JPanel(new BorderLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		label=new JLabel("Select GV File : ");
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(0,20,0,0);
		leftComponent.add(label,c);
		
		final JTextField text=new JTextField();
	    c.gridx=0;
	    c.gridy=2;
	    c.gridwidth=3;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.insets=new Insets(20,0,0,0); 
	    leftComponent.add(text,c);
	    
	    JButton button=new JButton("Browse");
	    c.gridx=0;
	    c.gridy=1;
	    c.gridwidth=3;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    leftComponent.add(button,c);
	    
	    button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jf = new JFileChooser();
				jf.showOpenDialog(f);
				text.setText(jf.getSelectedFile().getAbsolutePath());
				
				String[] cmd={"dot.exe","-Tpng","-O",jf.getSelectedFile().getAbsolutePath()};
				Runtime rt=Runtime.getRuntime();
				try {
					rt.exec(cmd);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					input = new File(jf.getSelectedFile().getAbsolutePath()+".png");
					img=ImageIO.read(input);
					imageIcon = new ImageIcon(img);
					
			        lbl.setIcon(imageIcon);
			        JScrollPane scrollPane = new JScrollPane(lbl);
			        scrollPane.setPreferredSize(rightComponent.getPreferredSize());
			        rightComponent.add(scrollPane, BorderLayout.CENTER);			        
			        f.invalidate();
			        f.validate();
			        f.repaint();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	    // Create a left-right split pane
	    JSplitPane hpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftComponent, rightComponent);
	    hpane.setDividerLocation(350);
		f.add(hpane);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String args[])
	{
	new frontend();
	}

}
