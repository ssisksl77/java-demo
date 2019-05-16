package designpattern.mediator.better;

// 객체가 서로 연결되지 않고 Director가 전부 관장하는 것이 포인트!
public class WidgetDisplayDirector implements DisplayDirector {
	
	private ButtonWidget buttonWidget;
	private GraphWidget graphtWidget;
	private TextBoxWidget textBoxWidget;
	private WatchWidget watchWidget;
	
	public WidgetDisplayDirector() {
		this.buttonWidget = new ButtonWidget(this);
		this.graphtWidget = new GraphWidget(this);
		this.textBoxWidget = new TextBoxWidget(this);
		this.watchWidget = new WatchWidget(this);
	}

	@Override
	public void widgetChanged(Widget widget) {
		if (widget instanceof ButtonWidget) {
			graphtWidget.update("Button Changed!");
			textBoxWidget.update("Hey textBox Button Changed!");
		}
	}

	public ButtonWidget getButtonWidget() {
		return buttonWidget;
	}
	
}
