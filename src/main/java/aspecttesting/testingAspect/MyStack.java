package aspecttesting.testingAspect;

public class MyStack {
	private int maxSize;
	private long[] stackArray;
	private int top;

	public MyStack(int s) {
		maxSize = s;
		stackArray = new long[maxSize];
		top = -1;
	}

	public void push(long j) throws StackFullException {
		if (isFull()) {
			throw new StackFullException("Stack is full");
		}
		stackArray[++top] = j;
	}

	public long pop() throws StackEmptyException {
		if (isEmpty()) {
			throw new StackEmptyException();
		}
		return stackArray[top--];
	}

	public long peek() {
		return stackArray[top];
	}

	public boolean isEmpty() {
		return (top == -1);
	}

	private boolean isFull() {
		return (top == maxSize - 1);
	}

}
