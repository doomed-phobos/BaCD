package com.phobos.bacd.Extractor;

import android.os.AsyncTask;

import com.phobos.bacd.VideoMetadata;

public abstract class Extractor extends AsyncTask<String, Void, VideoMetadata> {
   public interface Listener {
      void onCancel(String msg);
      void onSucess(VideoMetadata metadata);
   }

   private String m_error;
   private final Listener m_listener;
   Extractor(Listener listener) {m_listener = listener;}

   @Override
   protected VideoMetadata doInBackground(String... url) {
      String webpage = openURL(url[0]);
      if(webpage == null)
         return null;

      return processWebpage(webpage);
   }

   @Override
   protected void onCancelled() {
      super.onCancelled();
      m_listener.onCancel(m_error);
   }

   @Override
   protected void onPostExecute(VideoMetadata metadata) {
      super.onPostExecute(metadata);
      m_listener.onSucess(metadata);
   }

   protected void error(String msg, Object... args) {
      m_error = String.format(msg, args);
      cancel(true);
   }

   protected abstract String openURL(String url);
   protected abstract VideoMetadata processWebpage(String webpage);
}
