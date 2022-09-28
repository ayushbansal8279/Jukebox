package com.niit.jukebox.Service;

import com.niit.jukebox.dao.SongsDAO;
import com.niit.jukebox.model.JukeException;
import com.niit.jukebox.model.Songs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SongsService {

    // to check whether a song is already present in catlog or not
    public boolean isSongAvailable(List<Songs> songList,Songs song) throws JukeException {
        boolean flag = false;
        if(song==null){
            throw new JukeException("Song is null.");
        }
        else {
            String name = song.getSongName();
            for (Songs currentSong : songList) {
                if (currentSong.getSongName().equals(name)) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    // to add a song in catlog
    public boolean addSong(Songs song,List<Songs>songsList) throws JukeException,Exception {
        int res = 0;
        if(song!=null) {
            if (!isSongAvailable(songsList, song)) {
                res = SongsDAO.insertSong(song);
            }
            else {
                throw new JukeException("Song already available.");
            }
        }
        else {
            throw new JukeException("song is null.");
        }
        return (res==1);
    }

    public void selectAll(List<Songs> songList)throws JukeException{
        if(songList!=null) {
            System.out.format("  %-10s%-25s%-20s%-20s%-20s%s   \n","Song Id","Song Name","Artist Name", "Genre", "Album","Duration");
            System.out.println("-------------------------------------------------------------------------------------------------------------");
            for (Songs s : songList) {
                System.out.format("  %-10d%-25s%-20s%-20s%-20s%f  \n",s.getSongId(), s.getSongName(), s.getArtist(), s.getGenre(), s.getAlbum(), s.getDuration());
                System.out.println("-------------------------------------------------------------------------------------------------------------");
            }
        }
    }

    public Songs getOneSong(List<Songs> allSongs,String songName)throws JukeException{
        Songs selectedSong=null;
        if(songName==null){
            throw new JukeException("Song name is null.");
        }
        else {
                for (Songs song : allSongs) {
                    if (song.getSongName().equals(songName)) {
                        selectedSong = new Songs(song.getSongName(), song.getArtist(), song.getGenre(), song.getAlbum(), song.getDuration());
                    }
                }
            }
        return selectedSong;
    }

    public List<Songs> getAllSongs()throws Exception{
        List<Songs> allSongsList=SongsDAO.displayCatlog();
        return allSongsList;
    }

    public List<Songs> songsByAlbum(List<Songs> allSongs,String albumName) throws JukeException{
        List<Songs> albumCatorizedList;
        if(!allSongs.isEmpty() && albumName!=null) {
            albumCatorizedList=new ArrayList<>();
            Iterator<Songs> ite = allSongs.iterator();
            Songs currentSong;
            while (ite.hasNext()) {
                currentSong = ite.next();
                if (currentSong.getAlbum().equals(albumName)) {
                    albumCatorizedList.add(currentSong);
                }
            }
        }
        else {
            throw new JukeException("song list is empty or album name is null.");
        }
        return albumCatorizedList;
    }

    public List<Songs> songsByArtist(List<Songs> allSongs,String artistName) throws JukeException{
        List<Songs> artistCatorizedList;
        if(!allSongs.isEmpty() && artistName!=null) {
            artistCatorizedList=new ArrayList<>();
            Iterator<Songs> ite = allSongs.iterator();
            Songs currentSong;
            while (ite.hasNext()) {
                currentSong = ite.next();
                if (currentSong.getArtist().contains(artistName)) {
                    artistCatorizedList.add(currentSong);
                }
            }
        }
        else {
            throw new JukeException("artist is null");
        }
        return artistCatorizedList;
    }

    public List<Songs> songsByGenre(List<Songs> allSongs,String genre)throws JukeException {
        List<Songs> genreCatorizedList;
        if(!allSongs.isEmpty() && genre!=null) {
            genreCatorizedList=new ArrayList<>();
            Iterator<Songs> ite = allSongs.iterator();
            Songs currentSong;
            while (ite.hasNext()) {
                currentSong = ite.next();
                if (currentSong.getGenre().equals(genre)) {
                    genreCatorizedList.add(currentSong);
                }
            }
        }
        else {
            throw new JukeException("genre is null");
        }
        return genreCatorizedList;
    }

}
