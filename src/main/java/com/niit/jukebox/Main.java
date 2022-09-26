package com.niit.jukebox;

import com.niit.jukebox.Service.SongsService;
import com.niit.jukebox.methods.SongsDAO;
import com.niit.jukebox.model.Songs;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
           Songs s1=new Songs("ancing que","b","c","d",2.3f);
//            System.out.println(SongsDAO.insertSong(s1));
            System.out.println(SongsService.addSong(s1));
//            System.out.println(SongsDAO.displayCatlog());
//            List<Songs> list= SongsDAO.displayCatlog();
//            System.out.println(SongsService.getOneSong(list,"Dancing Queen"));
//            System.out.println(SongsService.songsByAlbum(list,"sal"));
//            System.out.println(SongsService.songsByArtist(list,"Big"));
//            System.out.println(SongsService.songsByGenre(list,"jazz"));

//            SongsService.selectAll(SongsService.songsByGenre(list,"Jazz"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
