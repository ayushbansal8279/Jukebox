package com.niit.jukebox.Service;

import com.niit.jukebox.DAO.PlayerDAO;

import javax.sound.sampled.Clip;

public class PlayerService {

    public Clip getClip(int songId) throws Exception{
        return PlayerDAO.getClip(songId);
    }

    public void playSong(Clip clip){
        PlayerDAO.playSong(clip);
    }

    public void stopSong(Clip clip){
        PlayerDAO.stopSong(clip);
    }
}
