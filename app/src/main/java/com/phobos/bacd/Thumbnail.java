package com.phobos.bacd;

import com.phobos.bacd.URL.URLResource;

public class Thumbnail extends URLResource<Void> {
   public Thumbnail(int w, int h, String url) {
      super(url);
   }
}
