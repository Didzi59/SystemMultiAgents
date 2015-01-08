package wator;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SMA {
	
	//private ArrayList<Agent> lag;
	private Environment env;
	private int chronon;
	private static final int LATENCY = 200;
	private static final int NB_SHARKS = 5;
	private static final int NB_FISH = 200;
	
	public SMA() {
		this.init();
		this.display();
	}
	
	public void init() {
		this.env = new Environment();
		for (int i = 0; i < NB_SHARKS ; i++) {
			Position pos = this.env.getRandomFreePosition();
			new Shark(this.env, pos);
		}
		for (int i = 0; i < NB_FISH ; i++) {
			Position pos = this.env.getRandomFreePosition();
			new Fish(this.env, pos);
		}
	}
	
	public void runOnce() {
		System.out.println("Chronon " + chronon);
		ArrayList<Agent> agents = this.env.getAgentsList();
		for (Agent agent : agents) {
			//Agent is not dead during this turn
			if (agent == this.env.getAgent(agent.getPos())) {
				agent.doIt();
			}
		}
		this.display();
	}
	
	public void run() {
		System.out.println("Run Wator");
		this.chronon = 0;
		while (true) {
			this.runOnce();
			this.chronon++;
		}
	}
	
	public void display() {
		this.env.display();
		try {
		    TimeUnit.MILLISECONDS.sleep(LATENCY);
		} catch (InterruptedException e) {
		    //Handle exception
		}
	}
}
