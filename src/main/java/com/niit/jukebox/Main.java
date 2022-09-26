package com.niit.jukebox;

import com.niit.jukebox.Service.SongsService;
import com.niit.jukebox.methods.SongsMethods;
import com.niit.jukebox.model.Songs;

public class Main {
    public static void main(String[] args) {
        try {
//            Songs s1=new Songs("dancing quee","b","c","d",2.3f);
//            System.out.println(SongsService.addSong(s1));

            SongsService.selectAll();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
