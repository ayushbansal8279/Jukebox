package com.niit.jukebox.Service;

import com.niit.jukebox.dao.PlayListDAO;
import com.niit.jukebox.model.JukeException;

import java.util.Hashtable;

public class PlaylistService {

    public boolean isPlaylistAvailable(String playlistName,Hashtable<String,Integer> playlist){
        boolean res=false;
        if(playlist.containsKey(playlistName.toLowerCase().trim())){
            res=true;
        }
        return res;
    }

    public boolean addPlaylist(String playlistName, Hashtable<String,Integer> playlist) throws JukeException,Exception{
        boolean res=false;
        if(isPlaylistAvailable(playlistName,playlist)){
            throw new JukeException("playlist name already taken.");
        }
        else {
                res = PlayListDAO.createPlayList(playlistName.toLowerCase());
        }
        return res;
    }

    public Hashtable<String,Integer> getAllPlaylist() throws Exception{
        return PlayListDAO.viewPlaylist();
    }
}