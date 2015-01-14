package view;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import wator.SMA;

/**
 * Cette classe représente la vue de l'environnement
 * @author Jérémy Bossut et Julia Leven
 */
public class WatorView extends JFrame {

	private static final long serialVersionUID = 1L;
    private BoardPanel boardPanel;
    private StatusPanel statusPanel;
	
	// L'environnement
	private SMA sma;
	
	
	/**
	 * Le constructeur représentant la vue de l'environnement
	 * @param env l'environnement que l'on souhaite afficher
	 */
	public WatorView(SMA sma){
		super("Wator");
		this.sma = sma;
		init();
	}
	
    
	/**
	 * Initialisation de la vue 
	 */
    public void init(){
        // Create panels
        boardPanel = new BoardPanel(this.sma.getEnv());
        boardPanel.setPreferredSize(new Dimension(800, 800));
        statusPanel = new StatusPanel(this.sma);
        statusPanel.setPreferredSize(new Dimension(200, 800));

        JPanel globalLayout = new JPanel();
        globalLayout.setLayout(new BoxLayout(globalLayout, BoxLayout.X_AXIS));
        // Add panels to this Frame
        globalLayout.add(boardPanel);
        globalLayout.add(statusPanel);
        this.setContentPane(globalLayout);
        this.setSize(new Dimension(1000, 800));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		this.sma.addObserver(this.boardPanel);
		this.sma.addObserver(this.statusPanel);
		this.setVisible(true);
    }
        
}
