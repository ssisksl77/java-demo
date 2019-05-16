package designpattern.mediator.better;

public class TextBoxWidget implements Widget {
	private DisplayDirector director;
	
	public TextBoxWidget(DisplayDirector director) {
		super();
		this.director = director;
	}

	@Override
	public void changed() {
		director.widgetChanged(this);
	}

	@Override
	public void update(Object obj) {
		System.out.println("I'm TextBoxWidget. I've got [" + obj + "]");
	}

}
