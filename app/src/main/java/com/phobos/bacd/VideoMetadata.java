package com.phobos.bacd;

import java.util.ArrayList;

public class VideoMetadata {
   private ArrayList<Thumbnail> m_thumbnails = new ArrayList<>();
   private String m_title;
   public VideoMetadata() {}

   /*
   public ArrayList<Thumbnail> availableThumbnails() {return m_thumbnails;}
   public void addThumbnail(Thumbnail t) {
      m_thumbnails.add(t);
   }*/
   public void setTitle(String title) {m_title = title;}
   public String title() {return m_title;}
}
