package kentbeck.tdd;

import kentbeck.tdd.Money.CURRENCY;

public class Pair {
	private CURRENCY from;
	private CURRENCY to;
	
	Pair(CURRENCY from, CURRENCY to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
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
		Pair other = (Pair) obj;
		if (from != other.from)
			return false;
		if (to != other.to)
			return false;
		return true;
	}
}
