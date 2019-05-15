package kentbeck.tdd;

import kentbeck.tdd.Money.CURRENCY;

public class Sum implements Expression {
	private Expression augent;
	private Expression addend;
	
	public Sum(Expression augent, Expression addend) {
		this.augent = augent;
		this.addend = addend;
	}

	// 어떻게 보면 파싱트리를 만드는 느낌인데
	@Override
	public Money reduce(Bank bank, CURRENCY to) {
		int amount = augent.reduce(bank, to).amount + addend.reduce(bank, to).amount;
		return new Money(amount, to);
	}
	
	@Override
	public Expression plus(Expression addend) {
		return new Sum(this, addend);
	}

	@Override
	public Expression times(int multiplier) {
		return new Sum(augent.times(multiplier), addend.times(multiplier));
	}
}
