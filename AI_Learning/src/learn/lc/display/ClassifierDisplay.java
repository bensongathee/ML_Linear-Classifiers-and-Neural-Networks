package learn.lc.display;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;

public class ClassifierDisplay extends JFrame{
	private double x = 0;
	private double y = 0;
	private double x2 = 0;
	private double y2 = 250;
	Graphics g;
	
	public ClassifierDisplay(String name){
		this.setTitle(name);
		this.setPreferredSize(new Dimension(500, 500));		
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.pack();
	    this.setVisible(true);
	    g = getGraphics();
	    }
	public void addPoint(double x, double y){
		this.x = x * 500;
		this.y = y * 500;
		paint(g);
	  }
	  
	public void paint(Graphics g) {
		g.drawLine((int)x,545-(int)y, (int)x2, 545-(int)y2);
		x2=x;
		y2=y;
	  }
}