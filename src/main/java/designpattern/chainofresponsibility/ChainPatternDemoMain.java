package designpattern.chainofresponsibility;

/**
 * 책임 연쇄 패턴 데모 
 * 메시지를 보내는 객체와 이를 받아 처리하는 객체들 간의 결합도를 없애기 위한 패턴 
 * 하나의 요청에 대한 처리가 반드시 한 객체에서만 되지 않고, 여러 객체에서 그 처리 기회를 주려는 것. (by GoF) 
 * @author yuhnam
 *
 */
public class ChainPatternDemoMain {

	public static void main(String[] args) {
		AbstractSlave doctor = new Dotor();
		AbstractSlave soldier = new Soldier();
		doctor.setNextSlave(soldier);
		
		General G = new General(doctor);
		G.comeAny();
		G.attack();
		
	}
}
