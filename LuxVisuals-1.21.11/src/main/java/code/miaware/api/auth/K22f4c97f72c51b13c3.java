package code.miaware.api.auth;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class K22f4c97f72c51b13c3 {
   private static final String DEFAULT_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36";
   private static final Gson GSON = (new GsonBuilder()).create();

   private static HttpURLConnection K91cfa50a012aca1d9f(String url, String method, String data, Map<String, String> headers, String agent) throws IOException {
      HttpURLConnection connection = (HttpURLConnection)URI.create(url).toURL().openConnection();
      boolean hasRequestBody = data != null && !data.isEmpty();
      connection.setRequestMethod(method);
      connection.setConnectTimeout(2000);
      connection.setReadTimeout(10000);
      connection.setRequestProperty("User-Agent", agent);
      if (headers != null) {
         for(Map.Entry<String, String> entry : headers.entrySet()) {
            connection.setRequestProperty((String)entry.getKey(), (String)entry.getValue());
         }
      }

      connection.setInstanceFollowRedirects(true);
      connection.setDoOutput(hasRequestBody);
      if (hasRequestBody) {
         DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());

         try {
            outputStream.write(data.getBytes(StandardCharsets.UTF_8));
         } catch (Throwable var11) {
            try {
               outputStream.close();
            } catch (Throwable var10) {
               var11.addSuppressed(var10);
            }

            throw var11;
         }

         outputStream.close();
      }

      return connection;
   }

   public static K7ae5509f6232a01dbc request(String url, String method, String data, Map<String, String> headers, String agent) throws IOException {
      HttpURLConnection connection = K91cfa50a012aca1d9f(url, method, data, headers, agent);

      K7ae5509f6232a01dbc var8;
      try {
         int responseCode = connection.getResponseCode();
         InputStream stream = responseCode >= 200 && responseCode < 300 ? connection.getInputStream() : connection.getErrorStream();
         if (stream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));

            try {
               StringBuilder response;
               String line;
               for(response = new StringBuilder(); (line = reader.readLine()) != null; response.append(line)) {
                  if (!response.isEmpty()) {
                     response.append('\n');
                  }
               }

               responseText = response.toString();
            } catch (Throwable var17) {
               try {
                  reader.close();
               } catch (Throwable var16) {
                  var17.addSuppressed(var16);
               }

               throw var17;
            }

            reader.close();
            K7ae5509f6232a01dbc var20 = new K7ae5509f6232a01dbc(responseCode, responseText);
            return var20;
         }

         var8 = new K7ae5509f6232a01dbc(responseCode, "");
      } finally {
         connection.disconnect();
      }

      return var8;
   }

   public static K7ae5509f6232a01dbc get(String url, Map<String, String> headers) throws IOException {
      return request(url, "GET", "", headers, "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
   }

   public static K7ae5509f6232a01dbc post(String url, String data, Map<String, String> headers) throws IOException {
      return request(url, "POST", data, headers, "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
   }

   public static <T> T post(String url, Object data, Class<T> responseType) throws IOException {
      K7ae5509f6232a01dbc response = post(url, GSON.toJson(data), Map.of("Content-Type", "application/json"));
      return (T)GSON.fromJson(response.text, responseType);
   }

   public static <T, E> K0e9efa8abfaf2f0f1e<T, E> postWithFallback(String url, Object data, Class<T> successType, Class<E> errorType) throws IOException {
      K7ae5509f6232a01dbc response = post(url, GSON.toJson(data), Map.of("Content-Type", "application/json"));
      return response.code == 200 ? new K0e9efa8abfaf2f0f1e(GSON.fromJson(response.text, successType), (Object)null) : new K0e9efa8abfaf2f0f1e((Object)null, GSON.fromJson(response.text, errorType));
   }
}
