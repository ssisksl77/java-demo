package designpattern.chainofresponsibility;

public class Soldier extends AbstractSlave{

	@Override
	boolean isMyOrder(CommandType commandType) {
		return commandType == CommandType.ATTACK;
	}

	@Override
	void doWork(CommandType comeAny) {
		System.out.println("네네 공격합니다~");
	}
}
