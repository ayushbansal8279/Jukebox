package com.songtest;

import com.niit.jukebox.Service.SongsService;
import com.niit.jukebox.methods.SongsMethods;
import com.niit.jukebox.model.Songs;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class insertTest {

    Songs s1;

    @Before
    public void setUp() {
        s1 = new Songs("Vande Matram","Bankim Chandra","Folk","Gurus of Peace",1);
    }

    @After
    public void tearDown() {
        s1 = null;
    }

    @Test
    public void selectAllSuccess() {
        try {
            Assert.assertTrue(SongsService.addSong(s1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
