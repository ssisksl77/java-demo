package designpattern.observer.general.observer;

import designpattern.observer.general.subject.Messenger;
import designpattern.observer.general.subject.Subject;

public class HorsemanObserver implements Observer {

	String lastMessage = "";
	
	// pull 
	@Override
	public void update(Subject subject, Object obj) {
		if (subject instanceof Messenger) {
			String message = ((Messenger) subject).getMessage();
			System.out.println("Horsemans " + message);
			this.lastMessage = message;
		}
	}
}
