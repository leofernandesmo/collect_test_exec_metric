package aspecttesting.testingAspect.aspect;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import aspecttesting.testingAspect.domain.ExceptionInfo;
import aspecttesting.testingAspect.domain.ExceptionInfoStack;
import aspecttesting.testingAspect.domain.ExceptionMap;

@Aspect
public class MyStackAspect {

	private static Logger logger;
	private static FileHandler fh;
	private static List<String> testFilesProject = MyStackAspect.getAllTestFiles("./exception_testing_log/testfile_list.txt");
	private static ExceptionMap exceptionMap = new ExceptionMap(); 
	
	// Exemplo de Pointcut - Na execucao de um metodo.
	// @Pointcut("execution(public void
	// aspecttesting.testingAspect.MyStack.push(long))")
	// public void pushMethodCalled() {
	// }
	//
	// @Before("pushMethodCalled()")
	// public void logMethod(JoinPoint jp) {
	// String methodName = jp.getSignature().getName();
	// System.out.println(("Executing method: " + methodName));
	// }

	// Exemplo de Pointcut - Qualquer acao de uma classe que herde de Exception
	// @Pointcut("this(java.lang.Exception)")
	// public void stackFullExceptionClass() {
	// }
	//
	// @Before("stackFullExceptionClass()")
	// public void logMethod2(JoinPoint jp) {
	// if(jp.getKind().equals(JoinPoint.CONSTRUCTOR_EXECUTION)) {
	// System.out.println(("Class: " + jp.getSourceLocation().getFileName()));
	// StackTraceElement[] trace = Thread.currentThread().getStackTrace();
	// int i = 0;
	// for (StackTraceElement stackTraceElement : trace) {
	// System.out.println(i + " " + stackTraceElement.toString());
	// i++;
	// }
	// }
	// }

	// Exemplo de Pointcut - Qualquer classe deste pacote especificado.
	@Pointcut("call(* *.*(..))")
	public void anyCall() {
	}

	@AfterThrowing(value = "anyCall()", throwing = "t")
	public void anyCallThatRaiseException(JoinPoint jp, Throwable t) {
//		for (String tesfile : testFilesProject) {
//			String testfileName = tesfile.substring(tesfile.lastIndexOf("/")+1, tesfile.length());
//			if(testfileName.equals(jp.getSourceLocation().getFileName())){
				String info = "Location: " + jp.getSourceLocation().getWithinType().toString() + ":"
						+ jp.getSourceLocation().getLine();
				String location = jp.getSourceLocation().getFileName() + ":" + jp.getSourceLocation().getLine();
				addExceptionInfo(info, location, t);
				log(t.getClass().getName());
//			}
//		}
	}
	
	public synchronized static void addExceptionInfo(String info, String location, Throwable t) {
		ExceptionInfoStack excpInfoStack = new ExceptionInfoStack();
		for (StackTraceElement stackTraceElement : t.getStackTrace()) {			
			ExceptionInfo ei = new ExceptionInfo(stackTraceElement.toString(), t.getClass().getName());
			excpInfoStack.add(ei);
			if (stackTraceElement.toString().contains(location.trim())) {
				break;
			}
		}
		exceptionMap.addNewException(t.getClass().getName(), excpInfoStack);
	}

	public synchronized static void log(String exceptionClassName) {
		logger = Logger.getLogger(exceptionClassName);
		logger.setUseParentHandlers(false);
		try {

			String logDir = "./exception_testing_log/";			
			
			
				fh = new FileHandler(logDir + exceptionClassName + ".log", false);	
				
				logger.addHandler(fh);
			
						
			
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			logger.info(exceptionClassName + "\n" + exceptionMap.getExcetionInfoStackToPrint(exceptionClassName));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<String> getAllTestFiles(String fileName) {
		List<String> testFiles = new ArrayList<String>();
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				testFiles.add(scanner.next());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return testFiles;
	}

}
