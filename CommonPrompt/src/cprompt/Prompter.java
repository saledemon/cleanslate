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
			
			Object ans = parseAnswer(answer, prompts[i]);
			if(ans == null) continue;
			else answers.addFirst(ans);
			i++;
		}
		
		return answers;
	}

	@SuppressWarnings("unchecked")
	private <T extends Object> Object parseAnswer(String answer, PromptSet<T> set) {
		List<T> list = new ArrayList<T>();
		String temp = "";
		int i = 0;
		
		for(char c : answer.toCharArray()) {
			if(c == set.getSeparator() || i == answer.length()-1) {
				if(c != set.getSeparator()) temp += c;
				
				try {
					T t = (T)PromptSet.getCorrectConversion(temp, set);
					set.validateRules(t);
					list.add(t);
					if (list.size() == set.getArgsSize()) {
						break;
					}
				} catch(NumberFormatException | RuleException e) {
					System.out.println("\n"+e.getMessage()+"\n");
					return null;
				}
					
				temp = "";
			} else {
				temp += c;
			}
			i++;
		}
		
		if(list.size() == 1) {
			return list.get(0);
		} else if(list.size() > 1) {
			return list;
		} else {
			throw new IndexOutOfBoundsException();
		}
		
	}
	
	

}
