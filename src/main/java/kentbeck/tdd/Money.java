package kentbeck.tdd;

import java.math.BigDecimal;

public class Money implements Expression {
	protected int amount;
	protected CURRENCY currency; // 달러/프랑
	public enum CURRENCY {
		USD, CHF
	}
	
	public Money(int amount, CURRENCY currency) {
		this.amount = amount;
		this.currency = currency;
	}
	
	public static Money dollar(int amount) {
		return new Money(amount, CURRENCY.USD);
	}
	
	public static Money franc(int amount) {
		return new Money(amount, CURRENCY.CHF);
	}

	@Override
	public Money reduce(Bank bank, CURRENCY to) {
		double rate = bank.rate(this.currency, to);
		BigDecimal rateDecimal = BigDecimal.valueOf(rate);
		BigDecimal amountDecimal = BigDecimal.valueOf(amount);
		
		return new Money(amountDecimal.divide(rateDecimal, 2).intValue(), this.currency);
	}

	@Override
	public Expression plus(Expression addend) {
		return new Sum(this, addend);
	}

	@Override
	public Expression times(int multiplier) {
		return new Money(this.amount * multiplier, this.currency);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result
				+ ((currency == null) ? 0 : currency.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		if (amount != other.amount)
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Money [amount=" + amount + ", currency=" + currency + "]";
	}
	
	public static void main(String[] args) {
		Bank bank = new Bank();
		bank.addRate(CURRENCY.CHF, CURRENCY.USD, 2);
		bank.addRate(CURRENCY.USD, CURRENCY.CHF, 0.5);
		Money fiveDollar = new Money(5, CURRENCY.USD);
		Money tenFranc = new Money(10, CURRENCY.CHF);
		
		Sum sum = new Sum(fiveDollar, tenFranc);
		
		Money reducedMoneyToUSD = sum.reduce(bank, CURRENCY.USD);
		
		System.out.println(reducedMoneyToUSD);  // 10
		
		Money reducedMoneyToCHF = sum.reduce(bank, CURRENCY.CHF);
		System.out.println(reducedMoneyToCHF);
	}
}
