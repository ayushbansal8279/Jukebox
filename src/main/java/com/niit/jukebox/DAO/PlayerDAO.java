package com.niit.jukebox.DAO;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class PlayerDAO {

    public static Clip getClip(int songId) throws Exception{
        AudioInputStream ais= AudioSystem.getAudioInputStream(new File("src/main/resources/"+songId+".wav"));
        Clip clip=AudioSystem.getClip();
        clip.open(ais);
        return clip;
    }

    public static void playSong(Clip clip){
        clip.start();
    }

    public static void stopSong(Clip clip){
        clip.stop();
    }
}
