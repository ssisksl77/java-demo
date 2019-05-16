package designpattern.mediator.old;

public class Watch {
	private Button button;
	private Graph graph;
	private TextBox textBox;
	
	void bind(Button button, Graph graph, TextBox textBox) {
		this.button = button;
		this.graph = graph;
		this.textBox = textBox;
	}
	
	void changed() {
		button.update("Hey time is changed!");
		graph.update("change Time");
	}
	
	void event(String message) {
		System.out.println("I'm Watch. I've got [" + message + "]");
	}
}
