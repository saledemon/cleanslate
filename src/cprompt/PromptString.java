package cprompt;

import cprompt.rule.Rule;

public class PromptString extends PromptSet<String> {
	
	 /**
     * <p>
     * Create a prompt that will return any number of String with default separator ' '.
     * </p>
     * 
     * @param promptText the message to be displayed
     * @param rules set of rules for this prompt's answers
     */
	@SafeVarargs
	public PromptString(String promptText, Rule<String>... rules) {
		super(promptText, String.class, ' ', rules);
	}
	
	/**
     * <p>
     * Create a prompt that will return any number of String with given separator.
     * </p>
     * 
     * @param promptText the message to be displayed
     * @param separator the separator between each argument of the user input
     * @param rules set of rules for this prompt's answers
     */
	@SafeVarargs
	public PromptString(String promptText, char separator, Rule<String>... rules) {
		super(promptText, String.class, separator, rules);
	}

	@Override
	public String getConversion(String s) {
		return s;
	}

}
