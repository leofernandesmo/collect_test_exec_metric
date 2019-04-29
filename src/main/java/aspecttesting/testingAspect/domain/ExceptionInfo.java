package aspecttesting.testingAspect.domain;

public class ExceptionInfo {
	
	private String exceptionClass;
	private String fileName;
	private String fullClassName;
	private String methodName;
	private Integer line;	
		
	public ExceptionInfo(String fullDescription, String exceptionClass) {
		super();
		String part0 = fullDescription.substring(0, fullDescription.indexOf("("));
		String className = part0.substring(0, part0.lastIndexOf("."));
		String method = part0.substring(part0.lastIndexOf(".") + 1, part0.length());
		
		String part1 = fullDescription.substring(fullDescription.indexOf("(") + 1, fullDescription.indexOf(")"));
		String fileName = part1.substring(0, part1.indexOf(":"));
		String lineNumber = part1.substring(part1.indexOf(":") + 1, part1.length());	
		
		this.exceptionClass = exceptionClass;
		this.fileName = fileName;
		this.fullClassName = className;
		this.methodName = method;
		this.line = Integer.parseInt(lineNumber);
	}
	
	public String getExceptionClass() {
		return exceptionClass;
	}
	public void setExceptionClass(String classType) {
		this.exceptionClass = classType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFullClassName() {
		return fullClassName;
	}
	public void setFullClassName(String fullClassName) {
		this.fullClassName = fullClassName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Integer getLine() {
		return line;
	}
	public void setLine(Integer line) {
		this.line = line;
	}
	
	//BasicMonthOfYearDateTimeField.java:309
	public String getLocation() {
		return fileName + ":" + line;
	}
	
	//org.joda.time.field.FieldUtils.verifyValueBounds(FieldUtils.java:257)
	public String getFullDescription() {
		return fullClassName + "." + methodName + "(" + fileName + ":" + line + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ExceptionInfo) {
			ExceptionInfo newExcp = (ExceptionInfo)obj;
			return this.getFullDescription().equals(newExcp.getFullDescription());
		}
		return false;
	}
}
