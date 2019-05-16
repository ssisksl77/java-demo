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
