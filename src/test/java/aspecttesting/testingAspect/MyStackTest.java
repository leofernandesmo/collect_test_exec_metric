package aspecttesting.testingAspect;

//JUnit4
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;

public class MyStackTest {

	@Test
	public void shouldInsertAndRemoveWithoutProblem() throws Exception {
		MyStack stack = new MyStack(5);
		stack.push(100);
		assertEquals(100, stack.pop());
		stack.push(200);
		stack.push(100);
		stack.push(50);
		assertEquals(50, stack.pop());
	}

	@Test
	public void shouldReturnValueWithoutDelete() throws Exception {
		MyStack stack = new MyStack(5);
		stack.push(100);
		assertEquals(100, stack.peek());
		assertFalse(stack.isEmpty());
	}

	@Test(expected = StackFullException.class)
	public void shouldRaiseStackFullException() throws Exception {
		MyStack stack = new MyStack(5);
		stack.push(100);
		stack.push(200);
		stack.push(300);
		stack.push(400);
		stack.push(500);
		stack.push(600);
		fail();
	}
	
	@Test(expected = StackFullException.class)
	public void shouldRaiseStackFullException2() throws Exception {
		MyStack stack = new MyStack(2);
		stack.push(100);
		stack.push(200);
		stack.push(300);
		fail();
	}
	
	@Test(expected = StackEmptyException.class)
	public void shouldRaiseStackEmptyException() throws Exception {
		MyStack stack = new MyStack(2);
		stack.push(100);
		stack.pop();
		stack.pop();
		fail();
	}
}
