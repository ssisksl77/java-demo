package designpattern.mediator.better;

/**
 * GOF에서는 파사드와의 차이를 파사드는 단방향 중재자는 양방향이라고 함.
 * 그리고 중재자는 옵저버 패턴과도 유사하다고 함. 유사한 것처럼 보임 연결되는 객체가 서로 보이지 않기 때문이다.
 * 
 * 그런데 차이는 옵저버는 중재자와 달리 각자 할일이 분리된다.
 * 중재자는 한 곳으로 로직이 어느정도 집중되어 어떻게 돌아가는지 한눈에 보이지만
 * 옵저버는 그렇지 않다.
 * @author yuhnam
 */
public class MediatorMain {
	public static void main(String[] args) {
		WidgetDisplayDirector director = new WidgetDisplayDirector();
		ButtonWidget buttonWidget = director.getButtonWidget();
		
		buttonWidget.changed();
	}
}
