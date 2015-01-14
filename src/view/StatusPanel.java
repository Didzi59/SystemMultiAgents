package view;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import wator.Environment;
import wator.SMA;

public class StatusPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	
    private SMA sma;
    private JLabel chrononsLabel;
    private JLabel fishLabel;
    private JLabel sharksLabel;

    public StatusPanel(SMA sma) {
        super();
        this.sma = sma;
        Environment environment = sma.getEnv();

        // Create Labels
        chrononsLabel = new JLabel(String.valueOf(sma.getChronon()));
        fishLabel = new JLabel(String.valueOf(environment.getNbFish()));
        sharksLabel = new JLabel(String.valueOf(environment.getNbShark()));

        // Add labels to Panel
        GridLayout layout = new GridLayout(0, 2);
        setLayout(layout);
        add(new JLabel("Chronons:"));
        add(chrononsLabel);
        add(new JLabel("Fish:"));
        add(fishLabel);
        add(new JLabel("Sharks:"));
        add(sharksLabel);
    }
    
	@Override
	public void update(final Observable o, final Object arg) {
        Environment environment = sma.getEnv();
        chrononsLabel.setText(String.valueOf(sma.getChronon()));
        fishLabel.setText(String.valueOf(environment.getNbFish()));
        sharksLabel.setText(String.valueOf(environment.getNbShark()));
    }

}
