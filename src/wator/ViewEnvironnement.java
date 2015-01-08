package wator;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ViewEnvironnement extends JFrame {

	private static final long serialVersionUID = 1L;
	
	Environment env;
	private JTextArea textArea;
	
	public ViewEnvironnement(Environment env){
		super();
		this.env = env;
		init();
	}
    
    public void init(){
        textArea = new JTextArea("", Environment.NB_ROWS, Environment.NB_COLS);
        textArea.setEditable(false);
        textArea.setFont(new Font("Courier", Font.PLAIN, 12));
        this.getContentPane().add(textArea);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    
    public void refresh(){
    	((JTextArea) this.getContentPane().getComponent(0)).setText(env.toString());
    }
    
}

