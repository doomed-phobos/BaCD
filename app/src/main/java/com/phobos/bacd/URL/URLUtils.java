package com.phobos.bacd.URL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class URLUtils {
   public static String OpenURL(String urlStr) throws Exception {
      URL url = new URL(urlStr);
      StringBuilder sb = new StringBuilder();
      try(BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {
         for(String line; (line = reader.readLine()) != null;)
            sb.append(line);
      }
      return sb.toString();
   }
}
