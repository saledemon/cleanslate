package cprompt.rule;

public class RangeRule implements Rule<Integer> {

	private int lowBound;
	private int topBound;
	private boolean inclusiveLowBound;
	private boolean inclusiveTopBound;
	private char brack0 = ']';
	private char brack1 = '[';
	
	//By default, exclusive bounds
	public RangeRule(int lowBound, int topBound) {
		this.lowBound = lowBound;
		this.topBound = topBound;
	}

	public RangeRule(int lowBound, int topBound, boolean inclusiveLowBound, boolean inclusiveTopBound) {
		this.lowBound = lowBound;
		this.topBound = topBound;
		this.inclusiveLowBound = inclusiveLowBound;
		this.inclusiveTopBound = inclusiveTopBound;
		if(inclusiveLowBound) brack0='[';
		if(inclusiveTopBound) brack1=']';
	}
	
	public RangeRule(int lowBound, int topBound, boolean bothInclusive) {
		this(lowBound, topBound, bothInclusive, bothInclusive);
	}



	@Override
	public boolean isValid(Integer val) throws RuleException {
		
		boolean firstArg = inclusiveLowBound ? val >= lowBound : val > lowBound;
		boolean secondArg = inclusiveTopBound ? val <= topBound : val < topBound;
		
		if(firstArg && secondArg) {
			return true;
		} else {
			throw new RuleException("--> Input must be in the interval "+brack0+lowBound+", "+topBound+brack1+".\n--> Got "+val+" instead.");
		}
	}

}
