package org.joda.time;

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

@Aspect
public class ExceptionAspect {

    @Pointcut("call(* *.*(..))")
	public void anyCall() {
	}

	@AfterThrowing(value = "anyCall()", throwing = "t")
	public void anyCallThatRaiseException(JoinPoint jp, Throwable t) {
		for (String tesfile : testFilesProject) {

                    String testfileName = tesfile.substring(tesfile.lastIndexOf("/")+1, tesfile.length());
		    if(testfileName.equals(jp.getSourceLocation().getFileName())){
				String info = "Location: " + jp.getSourceLocation().getWithinType().toString() + ":"
						+ jp.getSourceLocation().getLine();
				String location = jp.getSourceLocation().getFileName() + ":" + jp.getSourceLocation().getLine();
				log(info, location, t);
			}
		}
	}

	private static Logger logger;
	private static FileHandler fh;
	private static List<String> testFilesProject = getAllTestFiles("./exception_testing_log/testfile_list.txt");

	public static void log(String info, String location, Throwable t) {
		logger = Logger.getLogger(t.getClass().getName());
		logger.setUseParentHandlers(false);
		try {

			String logDir = "./exception_testing_log/";
			new File(logDir).mkdirs();

			if (logger.getHandlers() == null || logger.getHandlers().length == 0) {
				fh = new FileHandler(logDir + t.getClass().getName() + ".log", true);
				logger.addHandler(fh);
			}

			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			StringBuilder strTrace = new StringBuilder();
			for (StackTraceElement stackTraceElement : t.getStackTrace()) {
				strTrace.append(stackTraceElement + "\n");
				if (stackTraceElement.toString().contains(location.trim())) {
					break;
				}
			}
			logger.info(location + "\n" + strTrace.toString());
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
