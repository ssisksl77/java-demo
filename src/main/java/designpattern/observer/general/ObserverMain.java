package designpattern.observer.general;

import designpattern.observer.general.observer.HorsemanObserver;
import designpattern.observer.general.observer.SoldierObserver;
import designpattern.observer.general.subject.Messenger;

/**
 * 
 * @author yuhnam
 * 	1. Subject interface 
 *  2. Observer interface 
 *  3. ConcreteSubject
 *  4. ConcreteObserver
 *  5. Test
 *  
 *  1. 장군이 공격하라는 말을 하면 
 *  2. Messenger가 해당 내용을 전부 전파한다.
 *  3. 장군은 자신의 군대에 대한 내용을 정확히 알 필요가 없다.
 */
public class ObserverMain {
	public static void main(String[] args) {
		Messenger messenger = new Messenger();
		General G = new General(messenger);
		
		messenger.attach(new SoldierObserver());
		messenger.attach(new HorsemanObserver());
		messenger.attach(new SoldierObserver());
		messenger.attach(new HorsemanObserver());
		
		G.attack();
	}
}	
