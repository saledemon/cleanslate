package algo24;

import java.util.List;

import algo24.operations.Operations;
import algo24.operations.Stack;
import cprompt.PromptSet;
import cprompt.Prompter;
import cprompt.rule.RangeRule;

public class Main {
	
	public static void main(String[] args) {
		PromptSet<Integer> set = new PromptSet<Integer>("Enter 4 cards: ", Integer.class, ' ', new RangeRule(1, 13, true));
		Prompter prompter = new Prompter(set);
		Operations ops = new Operations(24);
		
		while(true) {
			Integer[] cards = (Integer[])prompter.runPrompts().toArray();
			List<Stack> stack = ops.solve(cards);
			System.out.println("You've Picked : "+cards);
			if (stack == null) System.out.println("...no answer...");
			else System.out.println("-------Answer------\n"+stack);
		}
	}

}
