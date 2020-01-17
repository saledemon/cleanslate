package algo24.operations;

import org.javatuples.Pair;

public interface IOperation {
	
	public Stack operate(Pair<Integer, Integer> ops);
	
	default Pair<Integer, Integer> invert(Pair<Integer, Integer> ops) {
		return Pair.with(ops.getValue1(), ops.getValue0());
	}

}
