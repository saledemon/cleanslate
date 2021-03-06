package cprompt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cprompt.rule.Rule;
import cprompt.rule.RuleException;

public abstract class PromptSet<T> {
	
	private String promptText;
	private Class<T> inputType;
	private char separator;
	private int argsSize;
	private List<Rule<T>> rules;

	//support String and Integer inputType
	@SafeVarargs
	public PromptSet(String promptText, Class<T> inputType, char separator, Rule<T>... rules) {
		this.promptText = promptText;
		this.separator = separator;
		this.inputType = inputType;
		this.argsSize = parsePromptTextForTild();
		this.rules = new ArrayList<Rule<T>>(Arrays.asList(rules));
	}
	
	private int parsePromptTextForTild() {
		
		Pattern pattern = Pattern.compile("~\\d+");
		Matcher matcher = pattern.matcher(promptText);
		int number = -1;

        if(matcher.find()) {
        	
        	number = Integer.parseInt(promptText.substring(matcher.start()+1, matcher.end()));
        	
        	if (matcher.start() == 0) {
        		promptText = promptText.replaceFirst("~\\d+", "");
        	} else {
        		promptText = promptText.replace("~", "");
        	}
        }

		return number <= 0 ? -1 : number;
	}

	
	public String getPromptText() {
		return promptText;
	}


	public Class<T> getInputType() {
		return inputType;
	}


	public char getSeparator() {
		return separator;
	}
	
	public void setArgsSize(int argsSize) {
		this.argsSize = argsSize;
	}
	
	public int getArgsSize() {
		return argsSize;
	}

	public void validateRules(T val) throws RuleException {
		for (Rule<T> rule : rules) {
			rule.isValid(val);
		}
	}

	public abstract T getConversion(String s);
	
}
