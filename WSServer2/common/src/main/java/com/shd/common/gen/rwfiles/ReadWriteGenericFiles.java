package com.shd.common.gen.rwfiles;


import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Stream; 

public class ReadWriteGenericFiles {

	public static void main(String[] args) {
		// Test Cases
		Path filePath = Paths.get("C:\\websocket\\TestWebSocket\\src\\com\\reqrespjsonfiles\\Req22.json");
		filePath = Paths.get("C:\\websocket\\TestWebSocket\\src\\com\\reqrespjsonfiles\\Resp22.json");
		String jsonStrFromFile = readLineByLineInOneStrFun.apply(filePath);
		filePath = Paths.get("C:\\websocket\\TestWebSocket\\src\\com\\reqrespjsonfiles\\Resp22WriteBack.json");
		writeOneStrToFileFun.accept(filePath, jsonStrFromFile);
//		String wStr = null;
//		writeOneStrToFileFun.accept(filePath, wStr);
//		wStr = readLineByLineInOneStrFun.apply(filePath);

		// Function interface referring to a method Function<String, String> fun =
		// FunctionInterfaceExample::show

	}

	public static void writeOneStrToFile(Path filePath, String stringToWrite) {
		// Path path = Paths.get("D:/test.txt");
		// 1st way
		// String str1 = "Using newBufferedWriter method!\n";
		// biConsumerTry.accept(100, "");

		try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
			writer.write(stringToWrite, 0, stringToWrite.length());
			writer.close();
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
	}

	public static BiConsumer<Path, String> writeOneStrToFileFun = (filePath, stringToWrite) -> {
		try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
			writer.write(stringToWrite, 0, stringToWrite.length());
			writer.close();
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
	};
	public static Function<Path, String> readLineByLineInOneStrFun = (Path filePath) -> {
		StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(filePath, StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentBuilder.toString();
	};

	public static String readLineByLineInOneStr(Path filePath) {
		StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(filePath, StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentBuilder.toString();
	}

}
