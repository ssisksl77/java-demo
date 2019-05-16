package designpattern.mediator.old;

public class MediatorMain {
	public static void main(String[] args) {
		Button b = new Button();
		Graph g = new Graph();
		TextBox tb = new TextBox();
		Watch w = new Watch();
		
		b.bind(g, tb, w);
		g.bind(b, tb, w);
		w.bind(b, g, tb);
		tb.bind(b, g, w);
		
//		b.changed();
//		g.changed();
//		w.changed();
//		tb.changed();
	}
	
}
