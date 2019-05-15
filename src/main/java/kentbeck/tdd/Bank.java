package kentbeck.tdd;

import java.util.HashMap;

import kentbeck.tdd.Money.CURRENCY;

public class Bank {
	private HashMap<Pair, Double> rates = new HashMap<>();
	
	public void addRate(CURRENCY from, CURRENCY to, double rate) {
		rates.put(new Pair(from, to), new Double(rate));
	}
	
	public double rate(CURRENCY from, CURRENCY to) {
		if (from.equals(to)) return 1;
		
		Double rate = rates.get(new Pair(from, to));
		return rate.doubleValue();
	}
	
	public Money reduce(Expression source, CURRENCY to) {
		return source.reduce(this, to);
	}
}
