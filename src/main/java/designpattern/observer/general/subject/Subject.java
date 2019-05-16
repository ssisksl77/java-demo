package designpattern.observer.general.subject;

import designpattern.observer.general.observer.Observer;

public interface Subject {
	public void attach(Observer o);
	public void detach(Observer o);
	public void notifyAllObservers();
}
