package designpattern.mediator.better;

public class GraphWidget implements Widget {
	private DisplayDirector director;
	
	public GraphWidget(DisplayDirector director) {
		super();
		this.director = director;
	}

	@Override
	public void changed() {
		director.widgetChanged(this);
	}

	@Override
	public void update(Object obj) {
		System.out.println("I'm GraphWidget. I've got [" + obj + "]");
	}

}
