package com.niit.jukebox.Service;

import com.niit.jukebox.model.Player;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;

public class PlayerService {

    public void playSong(Player player) {
        player.getClip().start();
        player.setStatus("play");
    }

    public void stopSong(Player player) {
        player.getClip().stop();
        player.setStatus("stop");
    }

    public void pauseSong(Player player)throws Exception
    {
        if (player.getStatus().equals("paused"))
        {
            throw new Exception("audio is already paused");}
        else {
            player.setCurrentFrame(player.getClip().getMicrosecondPosition());
            player.getClip().stop();
            player.setStatus("paused");
        }
    }

    public void resumeSong(Player player) throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        if (player.getStatus().equals("play"))
        {
            throw new EOFException("Audio is already "+
                    "being played");
        }
        else {
        player.getClip().close();
            player.setAudioInputStream(AudioSystem.getAudioInputStream(
                    new File(player.getFilePath()).getAbsoluteFile()));
            player.getClip().open(player.getAudioInputStream());
            player.getClip().loop(Clip.LOOP_CONTINUOUSLY);
        player.getClip().setMicrosecondPosition(player.getCurrentFrame());
        playSong(player);
        }
    }

}
