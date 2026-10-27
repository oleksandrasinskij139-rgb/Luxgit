package code.miaware.client.network.badge;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Kd54913d10ff6ea6960 implements AutoCloseable {
   public static final URI ENDPOINT = URI.create("http://104.128.137.86");
   private final URI endpoint;
   private final HttpClient http;
   private volatile K1c945ad41ed9205255 self;
   private volatile K619004015a0f1a4c67 presence;
   private ScheduledExecutorService worker;
   private long generation;
   private long nextWarning;
   private static final Logger LOG = LoggerFactory.getLogger("LuxVisuals-Badges");

   private void K67ccb46a725e92eaca(String step, String reason) {
      long now = System.nanoTime();
      if (now >= this.nextWarning) {
         this.nextWarning = now + TimeUnit.SECONDS.toNanos(60L);
         LOG.warn((String)"Badge VDS {} failed ({}); retrying automatically. Other players cannot recognize this client until registration succeeds.", (Object)step, (Object)reason);
      }
   }

   public Kd54913d10ff6ea6960() {
      this(ENDPOINT);
   }

   Kd54913d10ff6ea6960(URI endpoint) {
      this.http = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(3L)).build();
      this.presence = new K619004015a0f1a4c67(Set.of(), Set.of(), 0L);
      this.endpoint = endpoint;
   }

   public synchronized void start(UUID uuid, String name) {
      if (uuid != null && name != null && name.matches("[A-Za-z0-9_]{1,16}")) {
         K1c945ad41ed9205255 next = new K1c945ad41ed9205255(uuid, name);
         if (!next.equals(this.self) || this.worker == null || this.worker.isShutdown()) {
            this.close();
            this.self = next;
            long epoch = ++this.generation;
            this.worker = Executors.newSingleThreadScheduledExecutor((r) -> {
               Thread t = new Thread(r, "lux-badge-presence");
               t.setDaemon(true);
               return t;
            });
            this.worker.scheduleWithFixedDelay(() -> this.pulse(epoch, next), 0L, 25L, TimeUnit.SECONDS);
         }
      }
   }

   public boolean contains(UUID uuid, String name) {
      K1c945ad41ed9205255 own = this.self;
      if (own == null) {
         return false;
      } else if (!own.uuid.equals(uuid) && !own.name.equalsIgnoreCase(name)) {
         K619004015a0f1a4c67 p = this.presence;
         return System.nanoTime() < p.expires && (uuid != null && p.uuids.contains(uuid) || name != null && p.names.contains(name.toLowerCase(Locale.ROOT)));
      } else {
         return true;
      }
   }

   private void pulse(long epoch, K1c945ad41ed9205255 own) {
      try {
         JsonObject body = new JsonObject();
         body.addProperty("uuid", own.uuid.toString());
         body.addProperty("username", own.name);
         HttpResponse<Void> heartbeat = this.http.send(HttpRequest.newBuilder(this.endpoint.resolve("/api/heartbeat")).timeout(Duration.ofSeconds(3L)).header("Content-Type", "application/json").POST(BodyPublishers.ofString(body.toString())).build(), BodyHandlers.discarding());
         if (heartbeat.statusCode() != 200) {
            this.K67ccb46a725e92eaca("heartbeat", "HTTP " + heartbeat.statusCode());
         }

         HttpResponse<String> response = this.http.send(HttpRequest.newBuilder(this.endpoint.resolve("/api/user/online")).timeout(Duration.ofSeconds(3L)).header("Accept", "application/json").GET().build(), BodyHandlers.ofString());
         if (response.statusCode() != 200) {
            this.K67ccb46a725e92eaca("online list", "HTTP " + response.statusCode());
            return;
         }

         if (((String)response.body()).length() > 1000000) {
            this.K67ccb46a725e92eaca("online list", "response too large");
            return;
         }

         JsonArray online = JsonParser.parseString((String)response.body()).getAsJsonObject().getAsJsonArray("online");
         if (online == null || online.size() > 4096) {
            this.K67ccb46a725e92eaca("online list", "unexpected JSON schema");
            return;
         }

         Set<UUID> uuids = new HashSet();
         Set<String> names = new HashSet();

         for(JsonElement entry : online) {
            if (entry.isJsonObject()) {
               JsonObject row = entry.getAsJsonObject();
               if (row.has("username") && row.get("username").isJsonPrimitive()) {
                  String name = row.get("username").getAsString();
                  if (name.matches("[A-Za-z0-9_]{1,16}")) {
                     names.add(name.toLowerCase(Locale.ROOT));
                  }
               }

               try {
                  if (row.has("uuid")) {
                     uuids.add(UUID.fromString(row.get("uuid").getAsString()));
                  }
               } catch (RuntimeException var16) {
               }
            }
         }

         synchronized(this) {
            if (this.generation == epoch && own.equals(this.self)) {
               this.presence = new K619004015a0f1a4c67(Set.copyOf(uuids), Set.copyOf(names), System.nanoTime() + TimeUnit.SECONDS.toNanos(65L));
            }
         }
      } catch (InterruptedException var17) {
         Thread.currentThread().interrupt();
      } catch (Exception error) {
         this.K67ccb46a725e92eaca("connection", error.getClass().getSimpleName());
      }

   }

   public synchronized void close() {
      ++this.generation;
      this.self = null;
      this.presence = new K619004015a0f1a4c67(Set.of(), Set.of(), 0L);
      if (this.worker != null) {
         this.worker.shutdownNow();
         this.worker = null;
      }

   }
}
