package aspecttesting.testingAspect;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		MyStack m = new MyStack(1);
		try {
			m.push(10);
			m.push(20);
		} catch (StackFullException e) {
			
		}
		try {
			m.push(10);
			m.push(20);
		} catch (StackFullException e) {
			
		}
		try {
			m.push(10);
			m.push(20);
		} catch (StackFullException e) {
			
		}
		
		try {
			m.pop();
			m.pop();
			
		} catch (StackEmptyException e) {
			
		}
		
		System.out.println(m.peek());
	}
}
