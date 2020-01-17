package algo24.operations;

import org.javatuples.Pair;

public class Divide implements IOperation {

	private boolean invert = false;
	
	public Divide(boolean invert) {
		this.invert = invert;
	}
	
	@Override
	public Stack operate(Pair<Integer, Integer> ops) {
		try {
			if(invert) ops = invert(ops);
			
			if(ops.getValue0() % ops.getValue1() != 0) throw new ArithmeticException();
			
			int result = ops.getValue0()/ops.getValue1();
			String str = ops.getValue0()+" / "+ops.getValue1()+" = "+result;
			return new Stack(str, result);
		} catch(ArithmeticException e) {
			return null;
		}
	}

}
