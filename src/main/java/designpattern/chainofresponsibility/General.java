package designpattern.chainofresponsibility;

public class General {
	AbstractSlave mySlaves;
	
	public General(AbstractSlave mySlaves) {
		super();
		this.mySlaves = mySlaves;
	}

	void comeAny() {
		System.out.println("게 누구 없느냐!");
		mySlaves.order(CommandType.COME_ANY);
	}
	
	void attack() {
		System.out.println("공격하라!");
		mySlaves.order(CommandType.ATTACK);
	}
}