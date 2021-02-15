package app;

public class Song {
	private String title, artist, album, year;
	
	public Song(String title, String artist) {
		this.title = title;
		this.artist = artist;
	}
	public Song(String title, String artist, String album, String year) {
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	
	public String getTitle() {
		return title;
	}
	public String getArtist() {
		return artist;
	}
	public String getAlbum() {
		return album;
	}
	public String getYear() {
		return year;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	@Override
	public String toString() {
		return title + " - " + artist;
	}
	public String toFile(Song s) {
		if(s.getAlbum()==null && s.getYear()==null) {
			return title + ";" + artist + ";;";			
		}
		return title + ";" + artist + ";" + album + ";" + year; 
	}
}
