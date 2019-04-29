package aspecttesting.testingAspect;

import aspecttesting.testingAspect.exceptions.StackEmptyException;
import aspecttesting.testingAspect.exceptions.StackFullException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        App app = new App();
        app.addElemToStack();

        app.removeElemFromStack();

        try {
            app.throwToCaller();
        } catch (StackFullException e) {

        }
        System.out.println("App.main");
    }


    private void addElemToStack() {
        MyStack m = new MyStack(2);
        try {
            m.push(10);
            m.push(20);
            m.push(30);
        } catch (StackFullException e) {
        }
    }

    private void removeElemFromStack() {
        MyStack m = new MyStack(2);
        try {
            m.pop();
            m.pop();
        } catch (StackEmptyException e) {
        }
    }

    private void throwToCaller() throws StackFullException {
        MyStack m = new MyStack(2);
        m.push(10);
        m.push(20);
        m.push(30);
    }
}
