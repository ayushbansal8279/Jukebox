package com.niit.jukebox.model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Player
{

    // to store current position
    Long currentFrame;
    Clip clip;

    // current status of clip
    String status;

    AudioInputStream audioInputStream;
    String filePath;

    // constructor to initialize streams and clip
    public Player(int songId) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        this.filePath="src/main/resources/"+ songId +".wav";
        // create AudioInputStream object
        audioInputStream =
                AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public Clip getClip() {
        return clip;
    }
    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(int songId) {
        this.filePath = "src/main/resources/"+ songId +".wav";
    }

    public Long getCurrentFrame() {
        return currentFrame;
    }
    public void setCurrentFrame(Long currentFrame) {
        this.currentFrame = currentFrame;
    }

    public AudioInputStream getAudioInputStream() {
        return audioInputStream;
    }
    public void setAudioInputStream(AudioInputStream audioInputStream) {
        this.audioInputStream = audioInputStream;
    }

    @Override
    public String toString() {
        return "Player{" +
                "currentFrame=" + currentFrame +
                ", clip=" + clip +
                ", status='" + status + '\'' +
                ", audioInputStream=" + audioInputStream +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}