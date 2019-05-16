package designpattern.observer.general.observer;

import designpattern.observer.general.subject.Subject;

public interface Observer {
	void update(Subject subject, Object obj); // obj for push, subject for pull
}