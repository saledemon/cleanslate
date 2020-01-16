package cprompt.rule;

public interface Rule<T> {
	
	public boolean isValid(T val) throws RuleException;

}
