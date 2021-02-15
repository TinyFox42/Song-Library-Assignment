package app;

import app.SongList;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import javafx.collections.*;

public class LoadFile {
	public static ObservableList<Song> readFile(String f)
	throws Exception {
		ObservableList<Song> songs = FXCollections.observableArrayList();
		Path path = Paths.get(f);
		BufferedReader br = Files.newBufferedReader(path, StandardCharsets.US_ASCII);
		String info = br.readLine();
			
		while(info != null) {
			String[] Details = info.split(";");
			Song song = new Song(Details[0], Details[1],
					Details[2], Details[3]);
			songs.add(song);
			info = br.readLine();
		}
		
		SongList.sort(songs);
		
		return songs;
	}
	public static void updateFile(ObservableList<Song> songs)
	throws IOException {
		FileWriter fw = new FileWriter("songs.csv");
		for(Song s:songs) {
			fw.append(s.toFile() + "\n");
		}
		fw.flush();
		fw.close();
		SongList.sort(songs);
	}
}
