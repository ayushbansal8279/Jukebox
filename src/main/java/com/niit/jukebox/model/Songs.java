package com.niit.jukebox.model;

public class Songs {

    // attributes
    private int songId;
    private String songName;
    private String artist;
    private String genre;
    private String album;
    private float duration;

    // constructor
    public Songs(){}

    public Songs(int songId, String songName, String artist, String genre, String album, float duration) {
        this.songId = songId;
        this.songName = songName;
        this.artist = artist;
        this.genre = genre;
        this.album = album;
        this.duration = duration;
    }

    public Songs(String songName, String artist, String genre, String album, float duration) {
        this.songName = songName;
        this.artist = artist;
        this.genre = genre;
        this.album = album;
        this.duration = duration;
    }

    // getters & setters
    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    // toString
    @Override
    public String toString() {
        return "Songs{" +
                "songId=" + songId +
                ", songName='" + songName + '\'' +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                ", album='" + album + '\'' +
                ", duration=" + duration +
                '}';
    }
}

