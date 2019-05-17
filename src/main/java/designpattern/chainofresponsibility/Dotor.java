package designpattern.chainofresponsibility;

public class Dotor extends AbstractSlave{

	@Override
	boolean isMyOrder(CommandType commandType) {
		return commandType == CommandType.COME_ANY;
	}

	@Override
	void doWork(CommandType comeAny) {
		System.out.println("네네 달려갑니다~");
	}


}
