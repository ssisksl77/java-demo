package designpattern.chainofresponsibility;

public abstract class AbstractSlave {
	protected AbstractSlave nextSlave;
	
	
	public void setNextSlave(AbstractSlave nextSlave) {
		this.nextSlave = nextSlave;
	}
	
	abstract boolean isMyOrder(CommandType commandType);
	
	abstract void doWork(CommandType comeAny);
	
	public void order(CommandType commandType) {
		if (isMyOrder(commandType)) {
			doWork(commandType);
		}
		
		if (this.nextSlave != null) {
			this.nextSlave.order(commandType);
		}
	}
}
