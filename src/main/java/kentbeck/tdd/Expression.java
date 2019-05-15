package kentbeck.tdd;

import kentbeck.tdd.Money.CURRENCY;

public interface Expression {
	Money reduce(Bank bank, CURRENCY to);
	Expression plus(Expression addend);
	Expression times(int muliplier);
}
