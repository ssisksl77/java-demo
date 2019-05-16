package designpattern.mediator.better;

public class WatchWidget implements Widget {
	private DisplayDirector director;
	
	public WatchWidget(DisplayDirector director) {
		super();
		this.director = director;
	}

	@Override
	public void changed() {
		director.widgetChanged(this);
	}

	@Override
	public void update(Object obj) {
		System.out.println("I'm WatchWidget. I've got [" + obj + "]");
	}

}
