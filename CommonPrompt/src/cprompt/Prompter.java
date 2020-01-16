package cprompt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import cprompt.rule.RuleException;

public class Prompter {
	
	private LinkedList<Object> answers = new LinkedList<Object>();
	private PromptSet<?>[] prompts;
	private Scanner scan = new Scanner(System.in);
	
	
	public Prompter(PromptSet<?>... set) {
		this.prompts = set;
	}
	
	public LinkedList<Object> runPrompts() {
		answers.clear(); // reset prompts for looping prompts demands
		
		int i = 0;
		while(i < prompts.length) {
			System.out.println(prompts[i].getPromptText());
			String answer = scan.nextLine();
			Object ans;
			
			try {
				ans = parseAnswer(answer, prompts[i]);
			} catch (PromptCountException | NumberFormatException | RuleException e) {
				System.out.println("\n"+e.getMessage()+"\n");
				continue;
			}
			
			answers.addFirst(ans);
			i++;
		}
		
		return answers;
	}

	@SuppressWarnings("unchecked")
	private <T extends Object> Object parseAnswer(String answer, PromptSet<T> set) throws PromptCountException, NumberFormatException, RuleException {
		List<T> list = new ArrayList<T>();
		String temp = "";
		int i = 0;
		
		for(char c : answer.toCharArray()) {
			if(c == set.getSeparator() || i == answer.length()-1) {
				if(c != set.getSeparator()) temp += c;
					T t = (T)PromptSet.getCorrectConversion(temp.trim(), set);
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
			throw new PromptCountException("--> Not enough arguments supplied.\n--> Needed "+set.getArgsSize()+", got "+list.size()+" instead.");
		}
		
		return null;
	}

	public void showAnswers() {
		showAnswers("-------Answers-------",
					"---------------------");
	}
	
	
	public void showAnswers(String head, String tail) {
		System.out.println(head);
		System.out.println(answers);
		System.out.println(tail);
	}
	
	
	public LinkedList<Object> getAnswers(){
		return answers;
	}
	
	

}