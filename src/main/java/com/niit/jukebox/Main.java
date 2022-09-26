package com.niit.jukebox;

import com.niit.jukebox.Service.SongsService;

public class Main {
    public static void main(String[] args) {
        try {
//            Songs s1=new Songs("ancing que","b","c","d",2.3f);
//            System.out.println(SongsService.addSong(s1));

            SongsService.selectAll();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
