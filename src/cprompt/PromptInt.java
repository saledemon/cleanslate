package cprompt;

import cprompt.rule.Rule;

public class PromptInt extends PromptSet<Integer> {

	@SafeVarargs
	public PromptInt(String promptText, char separator, Rule<Integer>... rules) {
		super(promptText, Integer.class, separator, rules);
	}
	
	@SafeVarargs
	public PromptInt(String promptText, Rule<Integer>... rules) {
		super(promptText, Integer.class, ' ', rules);
	}

	@Override
	public Integer getConversion(String s) {
		try {
			if (s.isBlank()) {
				return null;
			}
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("--> Input must be an integer.\n--> Got "+s+" instead.");
		}
	}

}
