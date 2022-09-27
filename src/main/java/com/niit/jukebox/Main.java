package com.niit.jukebox;

import com.niit.jukebox.Service.PlaylistContentService;
import com.niit.jukebox.Service.PlaylistService;
import com.niit.jukebox.Service.SongsService;
import com.niit.jukebox.dao.PlayListContentDAO;
import com.niit.jukebox.dao.PlayListDAO;
import com.niit.jukebox.dao.SongsDAO;
import com.niit.jukebox.model.Songs;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
//           Songs s1=new Songs("ancingque","b","c","d",2.3f);
//            Songs s2=new Songs("ancigque","b","c","d",2.3f);
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
//            SongsService.selectAll(list);

//            System.out.println(PlayListDAO.createPlayList("firstPlaylist"));
//            System.out.println(PlayListDAO.viewPlaylist());
//            System.out.println(PlayListContentDAO.addSongToAPlaylist(1,1));
//            System.out.println(PlayListContentDAO.viewSongsInAPlaylist(1));
//            System.out.println(PlaylistService.getAllPlaylist());
//            System.out.println(PlaylistContentService.playlistContent("firstPlaylist", PlaylistService.getAllPlaylist(), (ArrayList)SongsDAO.displayCatlog()));
//            Hashtable playlist=PlaylistService.getAllPlaylist();
//            System.out.println(PlaylistService.addPlaylist("firstpLaylist",playlist));
//            System.out.println(PlaylistService.getAllPlaylist());
//            System.out.println(playlist.containsKey("firstplaylist"));
//            System.out.println(PlaylistContentService.addAlbumTOPlaylist((ArrayList<Songs>)list,PlaylistService.getAllPlaylist(),"firstplaylist","Abb"));
            System.out.println(PlaylistContentService.playlistContent("firstplaylist",PlaylistService.getAllPlaylist(),(ArrayList)SongsDAO.displayCatlog()));
//            System.out.println((PlaylistService.getAllPlaylist().get("playlist")));
            }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
