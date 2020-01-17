package algo24.operations;

import org.javatuples.Pair;

public class Minus implements IOperation {
	
	private boolean invert = false;
	
	public Minus(boolean invert) {
		this.invert = invert;
	}

	@Override
	public Stack operate(Pair<Integer, Integer> ops) {
		if (invert) ops = invert(ops);
		int result = ops.getValue0()-ops.getValue1();
		String str = ops.getValue0()+" - "+ops.getValue1()+" = "+result;
		return new Stack(str, result);
	}

}
