package com.jjjsoftware.dev.poc.io.FilePathHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.LinkedMultiValueMap;

@SpringBootApplication
public class FilePathHandlerApplication implements CommandLineRunner {
	
	private LinkedMultiValueMap<String, List<Long>> directoryNameLengthMapByName = new LinkedMultiValueMap<>();
	private LinkedMultiValueMap<Long, List<String>> directoryNameLengthMapByLength = new LinkedMultiValueMap<>();
	private LinkedMultiValueMap<String, List<Long>> fileNameLengthMapByName = new LinkedMultiValueMap<>();
	private LinkedMultiValueMap<Long, List<String>> fileNameLengthMapByLength = new LinkedMultiValueMap<>();
	private LinkedMultiValueMap<String, List<Long>> canonicalLengthMapByName = new LinkedMultiValueMap<>();
	private LinkedMultiValueMap<Long, List<String>> canonicalLengthMapByLength = new LinkedMultiValueMap<>();

	private int fileCount = 0;
	private String directoryNameLongest = "";
	private int directoryNameLongestLength = 0;
	private String fileNameLongest = "";
	private int fileNameLongestLength = 0;
	private String canonicalPathLongest = "";
	private int canonicalPathLongestLength = 0;
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(FilePathHandlerApplication.class);
		app.setBannerMode(Mode.OFF);
		SpringApplication.exit(app.run(args));
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO traverse file path and save result to file (or variable)
		// TODO analyze the result
		
		String dir = "E:\\jimmy-stuffs\\me\\backup\\kingston-usb-drive\\jimmy-stuffs";
		Files.walk(new File(dir).toPath())
				.forEach(new Consumer<Path>() {
					public void accept(Path path) {
						try {
							File file = path.toFile();
							if (file.isDirectory()) {
//								System.out.println(file.getName());
								if (file.getName().length() >= directoryNameLongestLength) {
									directoryNameLongest = file.getName();
									directoryNameLongestLength = file.getName().length();
								}
							} else if (file.isFile()) {
//								System.out.println(file.getName());
								if (file.getName().length() >= fileNameLongestLength) {
									fileNameLongest = file.getName();
									fileNameLongestLength = file.getName().length();
								}
							}
							if (file.getCanonicalPath().length() >= canonicalPathLongestLength) {
								canonicalPathLongest = file.getCanonicalPath();
								canonicalPathLongestLength = file.getCanonicalPath().length();
							}
//							String canonicalPath = file.getCanonicalPath();
//							if (canonicalPath.length() >= 255) {
//								System.out.println(canonicalPath.length() + " : " + canonicalPath);
//								fileCount++;
//							} else if (canonicalPath.length() >= 128) {
//								System.out.println(canonicalPath.length() + " : " + canonicalPath);
//								fileCount++;
//							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
		System.out.println("directoryNameLongest = " + directoryNameLongest);
		System.out.println("directoryNameLongestLength = " + directoryNameLongestLength);
		System.out.println("fileNameLongest = " + fileNameLongest);
		System.out.println("fileNameLongestLength = " + fileNameLongestLength);
		System.out.println("canonicalPathLongest = " + canonicalPathLongest);
		System.out.println("canonicalPathLongestLength = " + canonicalPathLongestLength);
		System.out.println("fileCount = " + fileCount);
	}
}
