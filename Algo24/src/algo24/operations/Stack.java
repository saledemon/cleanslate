package algo24.operations;

public class Stack {
	
	private String stackOperation = "";
	private int stackResult;
	
	public Stack(String stackOperation, int stackResult) {
		this.stackOperation = stackOperation;
		this.stackResult = stackResult;
	}
	
	@Override
	public String toString() {
		return stackOperation;
	}

	public int getResult() {
		return stackResult;
	}

}
