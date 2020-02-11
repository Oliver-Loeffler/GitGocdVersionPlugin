package net.raumzeitfalle.gradle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Git {
	
	public String describe() {
		try {
			return execDescribe();
		} catch (IOException e) {
			return errorMessage();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return errorMessage();
		}
	}

	private String errorMessage() {
		return "Invocation of 'git' failed.";
	}
	
	private String execDescribe() throws IOException, InterruptedException {
		ProcessBuilder builder = new ProcessBuilder("git","describe", "--tags", "--long");
		
		Process process = builder.start();
		
		int exitCode = process.waitFor();
		
		String stdOut = readStream(process.getInputStream());
		String stdErr = readStream(process.getErrorStream());
		
		if (exitCode != 0) {
			return stdErr;
		}
		return stdOut;
	}
	
	
	private String readStream(InputStream inputStream) {
		
		InputStreamReader isr = new InputStreamReader(inputStream);
		BufferedReader br = new BufferedReader(isr);
		
		return br.lines().collect(Collectors.joining(System.lineSeparator()));
		
	}
}
