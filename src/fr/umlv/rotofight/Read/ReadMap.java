package fr.umlv.rotofight.Read;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.lang.Integer;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadMap {
	/**
	 * Private constructor of ReadMap, should not be used.
	 */
	private ReadMap() {
		throw new RuntimeException("Cannot instantiate ReadMap class.");
	}
	/**
	 * Reads the line given char by char to construct the final map.
	 * @param line
	 * 		line to be read.
	 * @return
	 * 		ArrayList representing the line read.
	 */
	public static ArrayList<Integer> readLine(char[] line) {
		ArrayList<Integer> mapLine = new ArrayList<Integer>();
		for (int i = 0; i < line.length; i++ ) {
			switch(line[i]) {
			case('W'): mapLine.add(1); break;
			case(' '): mapLine.add(0); break;
			case('O'): mapLine.add(2); break; 
			case('T'): mapLine.add(3); break;
			default: mapLine.add(-1); break;
			}
		}
		return mapLine;
	}
	/**
	 * Gets the length of the first line read.
	 * @param r
	 * 		Reader to be read.
	 * @return
	 * 		the length of the first line read.
	 * @throws IOException
	 */
	private static int getLineLength(Reader r) throws IOException {
		int count = 0;
		int data;
		r.mark(4096);
		while((data = r.read()) != -1 && data != '\n') {
			count += 1;
		}
		r.reset();
		return count;
	}
	/**
	 * Gets the array of characters defined by a line.
	 * @param lineLength
	 * @param r
	 * @return
	 * @throws IOException
	 */
	public static char[] readerToLine(final int lineLength, Reader r) throws IOException {	
		char[] data = new char[lineLength];
		if(r.read(data) == -1) {
			return new char[] {'\0'};
		}
		r.read();
		return data;
	}
	/**
	 * Constructs a map according to map data loaded from the given file path.
	 * @param filename
	 * 		The path to the file to load.
	 * @return
	 * 		The map created from this extraction.
	 */
	public static ArrayList<ArrayList<Integer>> read(Path file){	
		try(Reader reader = Files.newBufferedReader(file)) {
			final int lineLength = getLineLength(reader);
			char[] line = readerToLine(lineLength, reader);
			ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
			while(line != null && line[0] != '\0') {
				map.add(readLine(line));
				line = readerToLine(lineLength, reader);
			}
			reader.close();
			return map;
		} catch (IOException exception)
	    {
	        System.out.println ("Error occured during map reading: " + exception.getMessage());
	    }
		return null;	
	}
}




