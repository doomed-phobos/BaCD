package com.phobos.bacd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.phobos.bacd.Extractor.Extractor;
import com.phobos.bacd.Extractor.YoutubeExtractor;

public class MainActivity extends AppCompatActivity {
   class ExtractorListener implements Extractor.Listener {
      @Override
      public void onCancel(String msg) {
         Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
      }

      @Override
      public void onSucess(VideoMetadata metadata) {
         Toast.makeText(MainActivity.this, metadata.title(), Toast.LENGTH_SHORT).show();
      }
   }

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      new YoutubeExtractor(new ExtractorListener()).execute("https://www.youtube.com/watch?v=-fN-Xjpd-qE");
//      try {
//         new YoutubeExtractor(this, "https://www.youtube.com/watch?v=f1x_UKH_bQg").buildVideo();
//      } catch(Exception e) {
//         Log.e(MainActivity.class.getName(), "", e);
//      }
   }
}