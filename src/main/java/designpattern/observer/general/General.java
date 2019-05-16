package designpattern.observer.general;

import designpattern.observer.general.subject.Messenger;

public class General {
	Messenger messenger;

	public General(Messenger messenger) {
		this.messenger = messenger;
	}
	
	public void attack() {
		System.out.println("공격하라!");
		messenger.notifyAttack();
	}
}
