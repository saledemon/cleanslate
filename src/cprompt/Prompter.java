package cprompt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import cprompt.exception.PromptCountException;
import cprompt.exception.PromptQuitException;
import cprompt.rule.RuleException;

public class Prompter {
	
	private LinkedList<Object> answers = new LinkedList<Object>();
	private PromptSet<?>[] prompts;
	private Scanner scan = new Scanner(System.in);
	private String quitCommand;
	private boolean hasQuit = false;
	public static final String DEFAULT_QUIT_COMMAND = "~Quit";
		
	public Prompter(PromptSet<?>... set) {
		this(DEFAULT_QUIT_COMMAND, set);
	}
	
	public Prompter(String quitCommand, PromptSet<?>... set) {
		this.prompts = set;
		this.quitCommand = quitCommand;
	}
	
	public Object runPrompt(int prompt) {
		hasQuit = false;
		answers.clear();
		System.out.println(prompts[prompt].getPromptText());
		String answer = scan.nextLine();
		Object ans;
		
		try {
			ans = parseAnswer(answer, prompts[prompt]);
		} catch (PromptQuitException f) {
			System.out.println("QUIT");
			hasQuit = true;
			return null;
		} catch (PromptCountException | NumberFormatException | RuleException e) {
			System.out.println("\n"+e.getMessage()+"\n");
			runPrompt(prompt);
			return null;
		}
		
		return ans;
	}

	
	public LinkedList<Object> runPrompts() {
		
		int i = 0;
		while(i < prompts.length) {
				Object ans = runPrompt(i);
				if (ans != null) {
					answers.addFirst(ans);
				} else {
					return null;
				}
			i++;
		}
		
		return answers;
	}

	@SuppressWarnings("unchecked")
	private <T> Object parseAnswer(String answer, PromptSet<T> set) throws PromptCountException, 
																NumberFormatException, RuleException,
																PromptQuitException {
		List<T> list = new ArrayList<T>();
		String temp = "";
		int i = 0;
		
		if(answer.toLowerCase().contains(quitCommand.toLowerCase())) {
			throw new PromptQuitException();
		}
		
		for(char c : answer.trim().toCharArray()) {
			if(c == set.getSeparator() || i == answer.length()-1) {
				if (c!=set.getSeparator()) temp += c;
					T t = set.getConversion(temp.trim());
					if (t == null) {
						temp = "";
						i++;
						continue;
					}
					set.validateRules(t);
					list.add(t);
					if (list.size() == set.getArgsSize()) {
						break;
					}
				temp = "";
			} else {
				temp += c;
			}
			i++;
		}
		
		if (set.getArgsSize() == -1 || list.size() == set.getArgsSize()) {
		
			if(list.size() == 1) {
				return list.get(0);
			} else if(list.size() > 1) {
				return list;
			}
		
		} else {
			throw new PromptCountException("--> Not enough arguments supplied.\n--> Needed "+set.getArgsSize()
												+ ", got "+list.size()+" instead.");
		}
		
		return null;
	}

	public void showAnswers() {
		showAnswers("-------Answers-------",
					"---------------------");
	}
	
	
	public void showAnswers(String head, String tail) {
		if (answers != null) {
			System.out.println(head);
			System.out.println(answers);
			System.out.println(tail);
		} 
	}
	
	
	public LinkedList<Object> getAnswers(){
		return answers;
	}

	public boolean hasQuit() {
		return hasQuit;
	}
	
	

}
