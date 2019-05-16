package designpattern.mediator.better;

public class ButtonWidget implements Widget {
	private DisplayDirector director;
	
	public ButtonWidget(DisplayDirector director) {
		super();
		this.director = director;
	}

	@Override
	public void changed() {
		director.widgetChanged(this);
	}

	@Override
	public void update(Object obj) {
		System.out.println("I'm ButtonWidget. I've got [" + obj + "]");
	}

}
