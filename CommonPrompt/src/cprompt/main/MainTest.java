package cprompt.main;

import java.util.LinkedList;

import cprompt.PromptInt;
import cprompt.PromptString;
import cprompt.Prompter;
import cprompt.rule.RangeRule;

public class MainTest {

	public static void main(String[] args) {
		
		// Remove spaces after comma in input
		// Number of arguments
		Prompter cp = new Prompter(
				new PromptInt("Entrez ~4 nombres ?", new RangeRule(1, 13, true)),
				new PromptString("~2Entrez les noms de vos parents ?", ',')
				);
		
		//cp.showAnswers(true);
		
		LinkedList<Object> answers;
		
		while(true) {
			answers = cp.runPrompts();
			
			System.out.println("----Answers----");
			System.out.println(answers);
			System.out.println("---------------");
		}
	}
}
