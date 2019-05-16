package designpattern.observer.general.subject;

import java.util.ArrayList;
import java.util.List;

import designpattern.observer.general.observer.Observer;
public class Messenger implements Subject{
	List<Observer> observers = new ArrayList<>();
	String message = "";
	
	@Override
	public void attach(Observer o) {
		observers.add(o);
	}

	@Override
	public void detach(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyAllObservers() {
		for (Observer o : observers) {
			o.update(this, "whatever message");
		}
	}
	
	public void notifyAttack() {
		this.message = "attack";
		notifyAllObservers();
	}
	
	public String getMessage() {
		return this.message;
	}
}
