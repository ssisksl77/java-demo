package designpattern.observer.general.observer;

import designpattern.observer.general.subject.Messenger;
import designpattern.observer.general.subject.Subject;

public class SoldierObserver implements Observer{
	String lastMessage = "";
	@Override
	public void update(Subject subject, Object obj) {
		if (subject instanceof Messenger) {
			String message = (String) obj; // 공격하라.
			System.out.println("Soldiers " + message);
			this.lastMessage = message;
		}
	}
}
