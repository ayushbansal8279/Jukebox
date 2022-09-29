package com.niit.jukebox;

import com.niit.jukebox.Service.PlaylistContentService;
import com.niit.jukebox.Service.PlaylistService;
import com.niit.jukebox.Service.SongsService;
import com.niit.jukebox.model.Songs;

import java.util.*;

public class JukeBoxMain {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        try {
            SongsService songsService = new SongsService();
            PlaylistService playlistService = new PlaylistService();
            PlaylistContentService playlistContentService = new PlaylistContentService();

            List<Songs> allSongsList;
            Hashtable<String, Integer> allPlaylist;
            allSongsList = songsService.getAllSongs();
            allPlaylist = playlistService.getAllPlaylist();

            songsService.selectAll(allSongsList);
            while (true) {
                System.out.println("\nChoose an option for:-");
                System.out.println("\t1-Songs\n\t2-Playlist\n\t3-Player\n\t4-Exit");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1: {
                        int choice2=0;
                        while (choice2!=6) {
                            try {
                                System.out.println("\nEnter a choice in songs");
                                System.out.println("\t1-To add a song.\n\t2-Search song by name.\n\t3-Search song by album.\n\t4-Search song by artist.\n\t5-Search song by genre.\n\t6-Main Menu.\n\t7-Exit");
                                choice2 = sc.nextInt();
                                switch (choice2) {
                                    case 1: {
                                        sc.nextLine();
                                        System.out.println("Enter song's name");
                                        String songName = sc.nextLine();
                                        System.out.println("Enter song's artist");
                                        String songartist = sc.nextLine();
                                        System.out.println("Enter song's genre");
                                        String songgenre = sc.nextLine();
                                        System.out.println("Enter song's album");
                                        String songalbum = sc.nextLine();
                                        System.out.println("Enter song's duration");
                                        float songduration = sc.nextFloat();
                                        if (songsService.addSong(new Songs(songName, songartist, songgenre, songalbum, songduration), allSongsList)) {
                                            allSongsList = songsService.getAllSongs();
                                            System.out.println("Song added successfully");
                                        } else {
                                            System.out.println("Something gone wrong");
                                        }
                                        break;
                                    }
                                    case 2: {
                                        System.out.println("Enter a song name.");
                                        sc.nextLine();
                                        String songName = sc.nextLine();
                                        System.out.println(songsService.getOneSong(allSongsList, songName));
                                        break;
                                    }
                                    case 3: {
                                        System.out.println("Enter a album name.");
                                        sc.nextLine();
                                        String albumName = sc.nextLine();
                                        songsService.selectAll(songsService.songsByAlbum(allSongsList, albumName));
                                        break;
                                    }
                                    case 4: {
                                        System.out.println("Enter an artist name.");
                                        sc.nextLine();
                                        String artistName = sc.nextLine();
                                        songsService.selectAll(songsService.songsByArtist(allSongsList, artistName));
                                        break;
                                    }
                                    case 5: {
                                        System.out.println("Enter a genre name.");
                                        sc.nextLine();
                                        String genremName = sc.nextLine();
                                        songsService.selectAll(songsService.songsByGenre(allSongsList, genremName));
                                        break;
                                    }
                                    case 6: {
                                        System.out.println("Returning to main menu...");
                                        break;
                                    }
                                    case 7:
                                        System.exit(0);
                                    default:
                                        System.out.println("Wrong input");
                                }
                            }
                            catch (Exception e){
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    }
                    case 2: {
                        int choice2 =0;
                        while(choice2!=6) {
                            try {
                                System.out.println("\nEnter a choice in playlist");
                                System.out.println("\t1-Show all playlist.\n\t2-Create a new playlist.\n\t3-Add song to a playlist.\n\t4-Add an album's songs to a playlist.\n\t5-Display a playlist.\n\t6-Main Menu.\n\t7-Exit");
                                choice2 = sc.nextInt();
                                switch (choice2) {
                                    case 1: {
                                        Set<String> playlist = allPlaylist.keySet();
                                        Iterator<String> ite = playlist.iterator();
                                        while (ite.hasNext()) {
                                            System.out.println(ite.next());
                                        }
                                        break;
                                    }
                                    case 2: {
                                        System.out.println("Enter new playlist name");
                                        sc.nextLine();
                                        String newPlaylistName = sc.nextLine();
                                        if (playlistService.addPlaylist(newPlaylistName, allPlaylist)) {
                                            System.out.println("New playlist created.");
                                            allPlaylist = playlistService.getAllPlaylist();
                                        } else {
                                            System.out.println("Something gone wrong!");
                                        }
                                        break;
                                    }
                                    case 3: {
                                        System.out.println("Enter playlist name");
                                        sc.nextLine();
                                        String playlistName = sc.nextLine();
                                        System.out.println("Enter song name");
                                        String songName = sc.nextLine();
                                        if (playlistContentService.addSongToPlaylist(allSongsList, allPlaylist, playlistName, songName)) {
                                            System.out.println("Song added successfully to the playlist");
                                        } else {
                                            System.out.println("Something gone wrong!");
                                        }
                                        break;
                                    }
                                    case 4: {
                                        System.out.println("Enter playlist name");
                                        sc.nextLine();
                                        String playlistName = sc.nextLine();
                                        System.out.println("Enter album name");
                                        String albumName = sc.nextLine();
                                        if (playlistContentService.addAlbumToPlaylist(allSongsList, allPlaylist, playlistName, albumName)) {
                                            System.out.println("Album added successfully");
                                        } else {
                                            System.out.println("Something gone wrong!");
                                        }
                                        break;
                                    }
                                    case 5: {
                                        System.out.println("Enter playlist name");
                                        sc.nextLine();
                                        String playlistname = sc.nextLine();
                                        songsService.selectAll(playlistContentService.playlistContent(playlistname, allPlaylist, allSongsList));
                                        break;
                                    }
                                    case 6: {
                                        System.out.println("Returning to main menu...");
                                        break;
                                    }
                                    case 7: {
                                        System.exit(0);
                                    }
                                    default:
                                        System.out.println("Wrong input");
                                }
                            }
                            catch (Exception e){
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    }

                    case 3: {
                        break;
                    }

                    case 4: {
                        System.exit(0);
                    }
                    default:
                        System.out.println("Wrong input");
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
