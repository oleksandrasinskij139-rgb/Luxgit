package code.miaware.api.utils.cloud;

import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class Kde3a26eb88a026a2d7 extends WebSocketClient {
   private String lastResponse;
   private CountDownLatch responseLatch;
   private boolean connectionReady = false;

   public Kde3a26eb88a026a2d7(URI serverUri) {
      super(serverUri);
   }

   public void onOpen(ServerHandshake handshakedata) {
      System.out.println("[CloudConfig] WebSocket connection opened");
      this.connectionReady = true;
   }

   public void onMessage(String message) {
      System.out.println("[CloudConfig] Received: " + message);
      this.lastResponse = message;
      if (this.responseLatch != null) {
         this.responseLatch.countDown();
      }

   }

   public void onClose(int code, String reason, boolean remote) {
      System.out.println("[CloudConfig] WebSocket connection closed: " + reason);
      this.connectionReady = false;
   }

   public void onError(Exception ex) {
      System.err.println("[CloudConfig] WebSocket error: " + ex.getMessage());
      ex.printStackTrace();
   }

   public boolean isConnectionReady() {
      return this.connectionReady && this.isOpen();
   }

   public String sendAndWaitForResponse(String message) {
      if (!this.isConnectionReady()) {
         System.err.println("[CloudConfig] Cannot send message: WebSocket not connected");
         return null;
      } else {
         try {
            this.responseLatch = new CountDownLatch(1);
            this.lastResponse = null;
            this.send(message);
            boolean received = this.responseLatch.await(10L, TimeUnit.SECONDS);
            if (!received) {
               System.err.println("[CloudConfig] Timeout waiting for response");
               return null;
            } else {
               return this.lastResponse;
            }
         } catch (InterruptedException e) {
            System.err.println("[CloudConfig] Interrupted while waiting for response: " + e.getMessage());
            return null;
         } catch (Exception e) {
            System.err.println("[CloudConfig] Error sending message: " + e.getMessage());
            e.printStackTrace();
            return null;
         }
      }
   }
}
