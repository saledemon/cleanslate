package algo24.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.javatuples.Pair;

public class Operations {
	
	private LinkedList<Stack> currentStack = new LinkedList<Stack>();
	private int aim;
	private List<Integer> operands;
	private LinkedList<Integer> removedOpsQueue = new LinkedList<Integer>();
	
	public Operations(int aim) {
		this.aim = aim;
	}
	
	public static final IOperation[] OPS = {
			new Plus(),
			new Minus(false),
			new Minus(true),
			new Time(),
			new Divide(false),
			new Divide(true),
	};

	public List<Stack> solve(Integer cards[]) {
		operands = new ArrayList<Integer>(Arrays.asList(cards));
		List<Stack> answer = backtrack(operands);
		return answer;
	}
	
	private List<Pair<Integer, Integer>> completedPairs = new ArrayList<Pair<Integer, Integer>>();
	private int c = 0;
	private int level = 0;
	
	private List<Stack> backtrack(List<Integer> operands) {
		
		List<Pair<Integer, Integer>> pairs = pairsOf(operands); 
		
		for(Pair<Integer, Integer> p : pairs) {
			if(currentStack.size() == 0) {
				completedPairs.add(p);
			}
			for(IOperation op: Operations.OPS) {
				c++;
				Stack stackResult = op.operate(p);
				
				if(stackResult != null) { // si le résultat est null, passe à la prochaine itération
					if(operands.size() == 2) {
						if(stackResult.getResult() == this.aim) {
							currentStack.add(stackResult);
							return this.currentStack;
						}
					} else if(operands.size() > 2) {
						currentStack.add(stackResult);
						updateOperands(p, stackResult.getResult());
						List<Stack> totalStack = backtrack(operands);
						if(totalStack != null) {
							return totalStack; // if null, goes to next instance
						}
					}
				}
			}
		}
		
		try {
			if(!completedPairs.equals(pairs)) {
				operands.remove(removedOpsQueue.removeLast());
				operands.add(removedOpsQueue.removeLast());
				operands.add(removedOpsQueue.removeLast());
				currentStack.removeLast();
				return null;
			}
		} catch(NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("RemoveOpsQueueSize: "+removedOpsQueue.size());
			System.out.println("CompletedPairs : "+completedPairs);
			System.out.println("Level : "+level);
		}

		return null;
	}
	
	public void updateOperands(Pair<Integer, Integer> pairToRemove, int resultToAdd) {
		operands.remove(pairToRemove.getValue0());
		operands.remove(pairToRemove.getValue1());
		operands.add(resultToAdd);
		removedOpsQueue.addLast(pairToRemove.getValue0());
		removedOpsQueue.addLast(pairToRemove.getValue1());
		removedOpsQueue.addLast(resultToAdd); // result to remove first
	}

	public List<Integer> opsWithout(Pair<Integer, Integer> pairToRemove){
		operands.remove(pairToRemove.getValue0());
		operands.remove(pairToRemove.getValue1());
		removedOpsQueue.addLast(pairToRemove.getValue0());
		removedOpsQueue.addLast(pairToRemove.getValue1());
		return operands;
	}
	
	
	public List<Pair<Integer, Integer>> pairsOf(List<Integer> numbers){
		
		List<Pair<Integer, Integer>> pairs = new ArrayList<Pair<Integer, Integer>>();
		
		for (int i = 0; i < numbers.size()-1; i++) {
			for (int j = 1; j < numbers.size(); j++) {
				if(j > i) {
					pairs.add(new Pair<Integer, Integer>(numbers.get(i), numbers.get(j)));
				}
			}
		}
		
		return pairs;
	}
	
	
	/*
	 * Getter / Setter
	 */

	public void setAim(int aim) {
		this.aim = aim;
	}
}
