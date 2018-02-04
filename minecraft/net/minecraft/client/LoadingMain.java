package net.minecraft.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import me.existdev.exist.utils.SecurityUtils;

public class LoadingMain {
	private static final String hwid = SecurityUtils.getSHA256(SecurityUtils.getSerialNumber("C"));
	private static final String FOLDER_DIR = System.getProperty("user.home") + "\\.logs";
	private static final String FILE_DIR = System.getProperty("user.home") + "\\.logs\\COMPUTER_DATA";
	private static final File newFolder = new File(FOLDER_DIR);
	private static final File newFile = new File(FILE_DIR);

	public static void Initlize() {
		makeFiles();
		writeToFile(hwid);
		try {
			if(readAll().equalsIgnoreCase(hwid)) {

			}else {
				System.out.println(readAll());
				System.out.println("The computer isnt verified!");
				System.exit(0);
			}
		} catch (IOException e) {
			System.out.println("The computer isnt verified!");
			System.exit(0);
		}
	}

	private static void makeFiles() {
		newFolder.mkdir();
		try {
			newFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeToFile(String line) {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(newFile)));
			pw.print(line);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String readAll() throws IOException {
	    return Files.lines(Paths.get(FILE_DIR), Charset.forName("UTF-8"))
	        .collect(Collectors.joining(System.getProperty("line.separator")));
	}
}