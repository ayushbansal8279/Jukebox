package com.niit.jukebox.Service;

import com.niit.jukebox.methods.SongsConnection;
import com.niit.jukebox.methods.SongsDAO;
import com.niit.jukebox.model.Songs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SongsService {

    // to check whether a song is already present in catlog or not
    private static boolean isSongAvailable(Songs song) throws Exception {
        List<Songs> allSongs=SongsDAO.displayCatlog();
        String name=song.getSongName();
        boolean flag = false;
        for (Songs currentSong:allSongs){
            if(currentSong.getSongName().equals(name)){
                flag=true;
                break;
            }
        }
        return flag;
    }

    // to add a song in catlog
    public static boolean addSong(Songs song) throws Exception {
        int res=0;
        if (!isSongAvailable(song)) {
            res=SongsDAO.insertSong(song);
        }
        return (res==1);
    }

    public  static void selectAll(List<Songs> songList)throws Exception{
        if(songList!=null) {
            System.out.println("---------------------------------------------------------------------------------------------------");
            for (Songs s : songList) {
                System.out.format("|  %-25s%-20s%-20s%-20s%f  |\n", s.getSongName(), s.getArtist(), s.getGenre(), s.getAlbum(), s.getDuration());
                System.out.println("---------------------------------------------------------------------------------------------------");
            }
        }
    }

    public static Songs getOneSong(List<Songs> allSongs,String songName)throws Exception{
        Songs selectedSong=null;
        if(!allSongs.isEmpty() && songName!=null) {
            Iterator<Songs> ite = allSongs.iterator();
            for (Songs song : allSongs) {
                if (song.getSongName().equals(songName)) {
                    selectedSong = new Songs(song.getSongName(), song.getArtist(), song.getGenre(), song.getAlbum(), song.getDuration());
                }
            }
        }
        return selectedSong;
    }

    public static List<Songs> songsByAlbum(List<Songs> allSongs,String albumName) {
        List<Songs> albumCatorizedList =null;
        if(!allSongs.isEmpty() && albumName!=null) {
            Iterator<Songs> ite = allSongs.iterator();
            Songs currentSong;
            while (ite.hasNext()) {
                currentSong = ite.next();
                if (currentSong.getAlbum().equals(albumName)) {
                    albumCatorizedList.add(currentSong);
                }
            }
        }
        return albumCatorizedList;
    }

    public static List<Songs> songsByArtist(List<Songs> allSongs,String artistName) {
        List<Songs> artistCatorizedList = null;
        if(!allSongs.isEmpty() && artistName!=null) {
            Iterator<Songs> ite = allSongs.iterator();
            Songs currentSong;
            while (ite.hasNext()) {
                currentSong = ite.next();
                if (currentSong.getArtist().contains(artistName)) {
                    artistCatorizedList.add(currentSong);
                }
            }
        }
        return artistCatorizedList;
    }

    public static List<Songs> songsByGenre(List<Songs> allSongs,String genre) {
        List<Songs> genreCatorizedList =null;
        if(!allSongs.isEmpty() && genre!=null) {
            Iterator<Songs> ite = allSongs.iterator();
            Songs currentSong;
            while (ite.hasNext()) {
                currentSong = ite.next();
                if (currentSong.getGenre().equals(genre)) {
                    genreCatorizedList.add(currentSong);
                }
            }
        }
        return genreCatorizedList;
    }

}
