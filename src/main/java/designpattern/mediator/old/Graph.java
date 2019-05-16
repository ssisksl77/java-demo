package designpattern.mediator.old;

public class Graph {
	private Button button;
	private TextBox textBox;
	private Watch watch;
	
	void bind(Button button, TextBox textBox, Watch watch) {
		this.button = button;
		this.textBox = textBox;
		this.watch = watch;
	}
	
	void changed() {
		textBox.update("Graph updated");
	}
	
	void update(String message) {
		System.out.println("I'm Graph. I've got [" + message + "]");
	}
}
