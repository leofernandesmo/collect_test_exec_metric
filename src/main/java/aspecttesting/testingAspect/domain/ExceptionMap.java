package aspecttesting.testingAspect.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExceptionMap {
	
	private Map<String, List<ExceptionInfoStack>> exceptionMap;

	public ExceptionMap() {
		super();
		this.exceptionMap = new HashMap<String, List<ExceptionInfoStack>>();
	}
	
	public void addNewException(String key, ExceptionInfoStack newExceptionInfoStack) {
		
		if(this.exceptionMap.containsKey(key)) {			
			List<ExceptionInfoStack> oldList = this.exceptionMap.get(key);
			List<ExceptionInfoStack> newList = new ArrayList<ExceptionInfoStack>(oldList);
			boolean added = false;
			for (ExceptionInfoStack oldExceptionInfoStack : oldList) {
				if (newExceptionInfoStack.isSupersetOf(oldExceptionInfoStack)) {
					int i = newList.indexOf(oldExceptionInfoStack);
					newList.remove(i);
					newList.add(i, newExceptionInfoStack);
					added = true;
				} 
			}
			if(!added) {
				newList.add(newExceptionInfoStack);
			}
			this.exceptionMap.put(key, newList);
		} else {
			List<ExceptionInfoStack> newList = new ArrayList<ExceptionInfoStack>();
			newList.add(newExceptionInfoStack);
			this.exceptionMap.put(key, newList);
		}
	}
	
	public String getExcetionInfoStackToPrint(String key) {
		StringBuilder sb = new StringBuilder();
		if(this.exceptionMap.containsKey(key)) {
			List<ExceptionInfoStack> oldList = this.exceptionMap.get(key);
			for (ExceptionInfoStack exceptionInfoStack : oldList) {
				sb.append(exceptionInfoStack.toString());
				sb.append("----------------------------\n");
			}
		}
		return sb.toString();
	}
	
	

}
