package com.phobos.bacd.Extractor;

import com.phobos.bacd.URL.URLUtils;
import com.phobos.bacd.VideoMetadata;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeExtractor extends Extractor {
   private static Pattern YT_INITIAL_PLAYER_RESPONSE_RE = Pattern.compile("ytInitialPlayerResponse\\s*=\\s*(\\{.+?\\})\\s*;");
   public YoutubeExtractor(Listener listener) {super(listener);}

   @Override
   protected String openURL(String url) {
      try {
         return URLUtils.OpenURL(url);
      } catch(Exception e) {
         error(e.getMessage());
         return null;
      }
   }

   @Override
   protected VideoMetadata processWebpage(String webpage) {
      Matcher player_response_matcher = YT_INITIAL_PLAYER_RESPONSE_RE.matcher(webpage);
      player_response_matcher.find();
      try {
         VideoMetadata metadata = new VideoMetadata();
         JSONObject player_response = new JSONObject(player_response_matcher.group(1));
         readVideoDetails(player_response, metadata);
         return metadata;
      } catch(Exception e) {
         error(e.getMessage());
         return null;
      }
   }

   private void readVideoDetails(JSONObject json, VideoMetadata out) throws Exception {
      JSONObject videoDetails = json.getJSONObject("videoDetails");
      out.setTitle(videoDetails.getString("title"));
      /*
      JSONArray thumbnails = videoDetails.getJSONObject("thumnail").getJSONArray("thumbnails");
      for(int i = 0; i < thumbnails.length(); ++i) {
         JSONObject thumbnail = thumbnails.getJSONObject(i);
         out.addThumbnail(
                new Thumbnail(
                       thumbnail.getInt("width"),
                       thumbnail.getInt("height"),
                       thumbnail.getString("url")));
      }*/
   }

   @Override
   protected void error(String msg, Object... args) {
      super.error("YoutubeExtractorError: " + msg, args);
   }
}
