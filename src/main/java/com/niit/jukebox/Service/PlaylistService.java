package com.niit.jukebox.Service;

import com.niit.jukebox.dao.PlayListDAO;

import java.util.Hashtable;

public class PlaylistService {

    public static boolean addPlaylist(String playlistName, Hashtable<String,Integer> playlist) throws Exception{
        boolean res=false;
        boolean isPlaylistPresent =playlist.containsKey(playlistName);
        if(!isPlaylistPresent){
            res=PlayListDAO.createPlayList(playlistName);
        }
        return res;
    }

    public static Hashtable<String,Integer> getAllPlaylist() throws Exception{
        return PlayListDAO.viewPlaylist();
    }

}
