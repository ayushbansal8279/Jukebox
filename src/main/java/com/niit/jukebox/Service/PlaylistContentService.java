package com.niit.jukebox.Service;

import com.niit.jukebox.dao.PlayListContentDAO;
import com.niit.jukebox.model.JukeException;
import com.niit.jukebox.model.Songs;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class PlaylistContentService {

    public boolean isSongAvailableInPlaylist(String playlistName,String songName,Hashtable<String,Integer> playlist,List<Songs> songlist)throws Exception{
        boolean res=false;
        List<Songs> songInPlaylist=playlistContent(playlistName,playlist,songlist);
        for(Songs song:songInPlaylist){
            if(song.getSongName().equals(songName)){
                res=true;
                break;
            }
        }
        return res;
    }

    public boolean addSongToPlaylist(List<Songs> songList, Hashtable<String,Integer> playlist,String playlistName,String songName)throws JukeException,Exception{
        boolean res;
        if(isSongAvailableInPlaylist(playlistName,songName,playlist,songList)){
            throw new JukeException("Song already available in playlist");
        }
        else {
            int playlistId = 0;
            int songId = 0;
            if (playlistName != null && songName != null) {

                boolean songAvailable = false;
                for (Songs currentSong : songList) {
                    if (currentSong.getSongName().equals(songName)) {
                        songId = currentSong.getSongId();
                        songAvailable = true;
                        break;
                    }
                }

                if (!playlist.containsKey(playlistName) || !songAvailable) {
                    throw new JukeException("Either song or playlist not available");
                } else {
                    playlistId = playlist.get(playlistName);
                    res = PlayListContentDAO.addSongToAPlaylist(playlistId, songId);
                }
            } else {
                throw new JukeException("Data is null.");
            }
        }
            return res;
    }

    public boolean addAlbumToPlaylist(List<Songs> songList, Hashtable<String,Integer> playlist,String playlistName,String albumName)throws Exception{
        boolean res=false;
        if (playlistName != null && albumName != null) {
            if(playlist.containsKey(playlistName)) {
                int playlistId=playlist.get(playlistName);
                ArrayList<Integer> songIdList = new ArrayList<>();
                Iterator<Songs> ite = songList.iterator();
                while (ite.hasNext()) {
                    Songs song = ite.next();
                    if (song.getAlbum().equals(albumName)) {
                        if (!isSongAvailableInPlaylist(playlistName, song.getSongName(), playlist, songList)) {
                            songIdList.add(song.getSongId());
                            }
                        }
                    }
                Iterator<Integer> ite2 = songIdList.iterator();
                int songId;
                while (ite2.hasNext()) {
                    songId = ite2.next();
                    PlayListContentDAO.addSongToAPlaylist(playlistId, songId);
                }
                res = true;
            }
            else {
                throw new JukeException("Playlist doesn't exist");
            }
        }
        else {
            throw new JukeException("Data is null");
        }
        return res;
    }

    public List<Songs> playlistContent(String playlistName,Hashtable<String,Integer> playlist,List<Songs> songlist)throws Exception{
        List<Songs> songListInPlaylist=new ArrayList();
        if(playlistName!=null){
            if(playlist.containsKey(playlistName)){
            List<Integer> songIdList=new ArrayList<>();
            int playlistId=playlist.get(playlistName);
            songIdList=PlayListContentDAO.viewSongsInAPlaylist(playlistId);

                for (int id: songIdList){
                    for(Songs song:songlist){
                        if(song.getSongId()==id){
                            songListInPlaylist.add(song);
                            break;
                        }
                    }
                }
            }
            else {
                throw new JukeException("no such playlist found.");
            }
        }
        else {
            throw new JukeException("null value found");
        }
        return songListInPlaylist;
    }
}