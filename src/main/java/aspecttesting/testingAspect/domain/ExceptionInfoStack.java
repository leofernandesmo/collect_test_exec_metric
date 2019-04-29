package aspecttesting.testingAspect.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExceptionInfoStack {

	private List<ExceptionInfo> exceptionInfoStack;

	public ExceptionInfoStack() {
		super();
		this.exceptionInfoStack = new ArrayList<ExceptionInfo>();
	}

	public List<ExceptionInfo> getExceptionInfoStack() {
		return exceptionInfoStack;
	}

	public void setExceptionInfoStack(List<ExceptionInfo> exceptionInfoStack) {
		this.exceptionInfoStack = exceptionInfoStack;
	}

	public void add(ExceptionInfo ei) {
		this.exceptionInfoStack.add(ei);
	}

	public boolean isSubsetOf(ExceptionInfoStack otherStack) {
		Set<ExceptionInfo> setOne = new HashSet<ExceptionInfo>(getExceptionInfoStack());
		Set<ExceptionInfo> setTwo = new HashSet<ExceptionInfo>(otherStack.getExceptionInfoStack());
		return setTwo.containsAll(setOne);
	}

	public boolean isSupersetOf(ExceptionInfoStack otherStack) {
		Set<ExceptionInfo> setOne = new HashSet<ExceptionInfo>(getExceptionInfoStack());
		Set<ExceptionInfo> setTwo = new HashSet<ExceptionInfo>(otherStack.getExceptionInfoStack());
		boolean isEqual = true;
		for (ExceptionInfo exceptionInfo : otherStack.getExceptionInfoStack()) {
			if (!getExceptionInfoStack().contains(exceptionInfo)) {
				isEqual = false;
			}
		}
		return isEqual;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (ExceptionInfo exceptionInfo : exceptionInfoStack) {
			sb.append(exceptionInfo.getFullDescription() + " \n");
		}
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ExceptionInfoStack) {
			ExceptionInfoStack newEIO = (ExceptionInfoStack) obj;
			return this.exceptionInfoStack.equals(newEIO.getExceptionInfoStack());
		}
		return false;
	}

}
