package algo24.operations;

import org.javatuples.Pair;

public class Time implements IOperation {
	
	@Override
	public Stack operate(Pair<Integer, Integer> ops) {
		int result = ops.getValue0()*ops.getValue1();
		String str = ops.getValue0()+" * "+ops.getValue1()+" = "+result;
		return new Stack(str, result);
	}

}
