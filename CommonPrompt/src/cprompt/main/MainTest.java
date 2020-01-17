package cprompt.main;

import cprompt.PromptInt;
import cprompt.PromptString;
import cprompt.Prompter;
import cprompt.rule.RangeRule;

public class MainTest {

	public static void main(String[] args) {

		//Quit option
		//Infinite prompt (or number of iteration prompt)
		//PromptGroups... I guess
		Prompter cp = new Prompter(
				new PromptInt("Entrez ~4 nombres ?", new RangeRule(1, 13, true)),
				new PromptString("~2Entrez les noms de vos parents ?", ',')
				);
		
		while(!cp.hasQuit()) {
			cp.runPrompts();
			cp.showAnswers();
		}
	}
}
