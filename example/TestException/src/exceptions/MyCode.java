package exceptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

 public class MyCode {

	public void dealWith(String logName) throws LogDoesNotExistException, IOException {

		File logFile = new File(System.getProperty("user.home") + File.pathSeparator + logName);
		try {
			checkExistenceOf(logFile);
		} catch (IOException e) {
			throw new LogDoesNotExistException();
		}

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(logFile));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		} finally {
			br.close();
		}
	}

	private void checkExistenceOf(File logFile) throws IOException {
		if (!logFile.exists()) {
			throw new IOException("File does not exist");
		}
	}

 }


