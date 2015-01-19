package core.view;

import core.Environment;
import core.SMA;

import java.awt.GridLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusPanel extends JPanel implements Observer{
	
private static final long serialVersionUID = 1L;
	
    private SMA sma;
    private JLabel chrononLabel;
    private Map<String, JLabel> counterLabels;
    private JLabel extraLabel;

    public StatusPanel(SMA sma) {
        super();
        this.sma = sma;

        // Create Labels
        chrononLabel = new JLabel(String.valueOf(sma.getChronon()));
        counterLabels = new HashMap<String, JLabel>();

        // Add labels to Panel
        GridLayout layout = new GridLayout(0, 2);
        setLayout(layout);
        add(new JLabel("Chronon:"));
        add(chrononLabel);

        Environment env = sma.getEnv();
        Map<String, Integer> counter = env.getNameNbAgent();

        for(String name : counter.keySet()) {
            add(new JLabel(name+":"));
            counterLabels.put(name, new JLabel(String.valueOf(counter.get(name))));
            add(counterLabels.get(name));
        }

        env.addStatusPanelLabels(this);
    }

    public JLabel getExtraLabel() {
        return this.extraLabel;
    }

    public void setExtraLabel(JLabel label) {
        this.extraLabel = label;
    }
    
	public void update(final Observable o, final Object arg) {
        chrononLabel.setText(String.valueOf(sma.getChronon()));
        Environment env = sma.getEnv();
        Map<String, Integer> counter = env.getNameNbAgent();

        // Update each JLabel
        for(String name : counter.keySet()) {
            counterLabels.get(name).setText(String.valueOf(counter.get(name)));
        }
        env.updateStatusPanel(this);
    }

}
