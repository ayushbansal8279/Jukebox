package com.niit.jukebox;

import com.niit.jukebox.Service.SongsService;
import com.niit.jukebox.methods.SongsDAO;
import com.niit.jukebox.model.Songs;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
           Songs s1=new Songs("ancingque","b","c","d",2.3f);
            Songs s2=new Songs("ancigque","b","c","d",2.3f);
//            System.out.println(SongsDAO.insertSong(s1));
//            System.out.println(SongsService.addSong(s1));
//            System.out.println(SongsDAO.displayCatlog());
            List<Songs> list;
            list= SongsDAO.displayCatlog();
//            System.out.println(SongsService.getOneSong(list,"Dancing Queen"));
//            System.out.println(SongsService.songsByAlbum(list,"sal"));
//            System.out.println(SongsService.songsByArtist(list,"Big"));
//            System.out.println(SongsService.songsByGenre(list,"jazz"));
//            System.out.println(SongsService.isSongAvailable(list,s1));
//            SongsService.selectAll(SongsService.songsByGenre(list,"Jazz"));
//            SongsService.selectAll(list);
//            System.out.println(SongsService.addSong(s1));
//            System.out.println(SongsService.addSong(s2));
//            SongsService.selectAll(list);
//            list= SongsDAO.displayCatlog();
            SongsService.selectAll(list);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
