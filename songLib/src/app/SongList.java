//Made by Eli Thorpe and Austin Suarez

package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SongList {
	public ObservableList<Song> songList;
	
	public SongList() {
		songList = FXCollections.observableArrayList();
	}
	
	public boolean add(String title, String artist, String album, String year) {
		Song song = new Song(title, artist, album, year);
		return songList.add(song);
	}
	
	public boolean delete(Song s) {
		if(songList.contains(s)) {
			songList.remove(s);
			return true;
		}
		return false;
	}
	
	public boolean edit(Song s, String title, String artist, String album, String year) {
		if(title == null || artist == null) {
			return false;
		}
		s.setTitle(title);
		s.setArtist(artist);
		s.setAlbum(album);
		s.setYear(year);
		return true;
	}
	
	public static boolean sort(ObservableList<Song> songs) {
		if(songs.isEmpty()) {
			return false;
		}
		for(int i=0; i<songs.size(); i++) {
			for(int j=i+1; j<songs.size(); j++) {
				int cc=0;
				String c1 = songs.get(i).getTitle().toLowerCase();
				String c2 = songs.get(j).getTitle().toLowerCase();
				if(c1.equals(c2)) {
					c1 = songs.get(i).getArtist().toLowerCase();
					c2 = songs.get(j).getArtist().toLowerCase();
				}
				while(c1.charAt(cc) == c2.charAt(cc)) {
					cc++;
				}
				if(c1.charAt(cc) > c2.charAt(cc)) {
					Song temp = songs.get(i);
					songs.set(i, songs.get(j));
					songs.set(j, temp);
				}
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		return songList.toString();
	}
	public ObservableList<Song> getList(){
		return songList;
	}
	public void setList(ObservableList<Song> songList) {
		this.songList = songList;
	}
	public boolean hasSong(String title, String artist) {
		for(int i=0; i<songList.size(); i++) {
			if(songList.get(i).getTitle().equals(title) && songList.get(i).getArtist().equals(artist)) {
				return true;
			}
		}
		return false;
	}
	
}
