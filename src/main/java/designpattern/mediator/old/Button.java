package designpattern.mediator.old;

public class Button {
	private Graph graph;
	private TextBox textBox;
	private Watch watch;
	
	void bind(Graph graph, TextBox textBox, Watch watch) {
		this.graph = graph;
		this.textBox = textBox;
		this.watch = watch;
	}
	
	void changed() {
		graph.update("Button Changed!");
		textBox.update("Hey textBox Button Changed!");
	}
	
	void update(String message) {
		System.out.println("I'm Button. I've got [" + message + "]");
	}
}
