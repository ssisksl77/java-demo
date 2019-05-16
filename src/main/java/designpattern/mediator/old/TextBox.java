package designpattern.mediator.old;

public class TextBox {
	private Button button;
	private Graph graph;
	private Watch watch;
	
	void bind(Button button, Graph graph, Watch watch) {
		this.button = button;
		this.graph = graph;
		this.watch = watch;
	}
	
	void changed() {
		button.update("Now TextBox is changing");
	}
	
	void update(String message) {
		System.out.println("I'm TextBox. I've got [" + message + "]");
	}
}
