package code.miaware.client.features.misc;

import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;
import net.minecraft.class_310;

public final class Kdd27279f42e50d9574 {
   private static final String RESOURCE_ROOT = "figura_avatars";
   private static final AtomicBoolean RUNNING = new AtomicBoolean(false);
   private static final AtomicInteger INSTALLED_FILES = new AtomicInteger();
   private static final AtomicInteger SKIPPED_FILES = new AtomicInteger();
   private static final AtomicInteger REPLACED_FILES = new AtomicInteger();
   private static volatile boolean finished;
   private static volatile Throwable lastError;

   private Kdd27279f42e50d9574() {
   }

   public static void installAsync() {
      if (RUNNING.compareAndSet(false, true)) {
         Thread thread = new Thread(() -> {
            try {
               K935e0e2f97e903b8db();
               Ka135dd6157e7ceb6ed();
               finished = true;
               lastError = null;
            } catch (Throwable t) {
               finished = false;
               lastError = t;
            } finally {
               RUNNING.set(false);
            }

         }, "lux-visuals-Figura-Avatar-Installer");
         thread.setDaemon(true);
         thread.start();
      }
   }

   public static void installBlocking() throws Exception {
      if (!RUNNING.compareAndSet(false, true)) {
         K963ab21a46712a6699();
         Ked4facefc61c50e00d();
      } else {
         try {
            K935e0e2f97e903b8db();
            Ka135dd6157e7ceb6ed();
            finished = true;
            lastError = null;
         } catch (Throwable t) {
            finished = false;
            lastError = t;
            K76908193e48ccc675e(t);
         } finally {
            RUNNING.set(false);
         }

      }
   }

   public static boolean isRunning() {
      return RUNNING.get();
   }

   public static boolean isFinished() {
      return finished;
   }

   public static int getInstalledFiles() {
      return INSTALLED_FILES.get();
   }

   public static int getSkippedFiles() {
      return SKIPPED_FILES.get();
   }

   public static int getReplacedFiles() {
      return REPLACED_FILES.get();
   }

   public static Throwable getLastError() {
      return lastError;
   }

   private static void K935e0e2f97e903b8db() {
      finished = false;
      INSTALLED_FILES.set(0);
      SKIPPED_FILES.set(0);
      REPLACED_FILES.set(0);
      lastError = null;
   }

   private static void K963ab21a46712a6699() throws InterruptedException {
      while(RUNNING.get()) {
         Thread.sleep(10L);
      }

   }

   private static void Ked4facefc61c50e00d() throws Exception {
      Throwable error = lastError;
      if (error != null) {
         K76908193e48ccc675e(error);
      }

   }

   private static void K76908193e48ccc675e(Throwable throwable) throws Exception {
      if (throwable instanceof Exception exception) {
         throw exception;
      } else if (throwable instanceof Error error) {
         throw error;
      } else {
         throw new Exception(throwable);
      }
   }

   private static void Ka135dd6157e7ceb6ed() throws Exception {
      class_310 mc = class_310.method_1551();
      if (mc != null && mc.field_1697 != null) {
         Path avatarsDir = mc.field_1697.toPath().resolve("figura").resolve("avatars").normalize();
         Files.createDirectories(avatarsDir);
         ClassLoader loader = Kdd27279f42e50d9574.class.getClassLoader();
         Enumeration<URL> roots = loader.getResources("figura_avatars");
         boolean found = false;

         while(roots.hasMoreElements()) {
            URL root = (URL)roots.nextElement();
            found = true;
            K975f619264b2712737(root, avatarsDir);
         }

         if (!found) {
            Ke55931a161d63ff260(avatarsDir);
         }

      }
   }

   private static void K975f619264b2712737(URL root, Path avatarsDir) throws Exception {
      String protocol = root.getProtocol();
      if ("file".equalsIgnoreCase(protocol)) {
         Path rootPath = Path.of(root.toURI()).normalize();
         K305ac77e6c0bbda19f(rootPath, avatarsDir);
      } else {
         if ("jar".equalsIgnoreCase(protocol)) {
            JarURLConnection connection = (JarURLConnection)root.openConnection();
            String entryName = connection.getEntryName();
            if (entryName == null || entryName.isEmpty()) {
               entryName = "figura_avatars";
            }

            JarFile jar = connection.getJarFile();

            try {
               K49952e448ede066f50(jar, entryName, avatarsDir);
            } catch (Throwable var9) {
               if (jar != null) {
                  try {
                     jar.close();
                  } catch (Throwable var8) {
                     var9.addSuppressed(var8);
                  }
               }

               throw var9;
            }

            if (jar != null) {
               jar.close();
            }
         }

      }
   }

   private static void Ke55931a161d63ff260(Path avatarsDir) throws Exception {
      URL location = Kdd27279f42e50d9574.class.getProtectionDomain().getCodeSource().getLocation();
      if (location != null) {
         URI uri = location.toURI();
         Path path = Path.of(uri).normalize();
         if (Files.isDirectory(path, new LinkOption[0])) {
            Path root = path.resolve("figura_avatars").normalize();
            if (Files.isDirectory(root, new LinkOption[0])) {
               K305ac77e6c0bbda19f(root, avatarsDir);
            }

         } else {
            if (Files.isRegularFile(path, new LinkOption[0])) {
               JarFile jar = new JarFile(path.toFile());

               try {
                  K49952e448ede066f50(jar, "figura_avatars", avatarsDir);
               } catch (Throwable var8) {
                  try {
                     jar.close();
                  } catch (Throwable var7) {
                     var8.addSuppressed(var7);
                  }

                  throw var8;
               }

               jar.close();
            }

         }
      }
   }

   private static void K305ac77e6c0bbda19f(Path root, Path avatarsDir) throws IOException {
      if (Files.isDirectory(root, new LinkOption[0])) {
         Path normalizedRoot = root.normalize();
         Path normalizedAvatarsDir = avatarsDir.normalize();
         Stream<Path> stream = Files.walk(normalizedRoot);

         try {
            stream.filter((x$0) -> Files.isRegularFile(x$0, new LinkOption[0])).forEach((file) -> {
               try {
                  Path normalizedFile = file.normalize();
                  Path relativePath = normalizedRoot.relativize(normalizedFile).normalize();
                  if (relativePath.isAbsolute() || Kcee414d006005211cb(relativePath)) {
                     return;
                  }

                  Path output = normalizedAvatarsDir.resolve(relativePath).normalize();
                  Ke6d179cd071857b8c4(normalizedFile, output, normalizedAvatarsDir);
               } catch (Throwable var6) {
               }

            });
         } catch (Throwable var8) {
            if (stream != null) {
               try {
                  stream.close();
               } catch (Throwable var7) {
                  var8.addSuppressed(var7);
               }
            }

            throw var8;
         }

         if (stream != null) {
            stream.close();
         }

      }
   }

   private static void K49952e448ede066f50(JarFile jar, String rootEntry, Path avatarsDir) throws IOException {
      Path normalizedAvatarsDir = avatarsDir.normalize();
      String prefix = rootEntry.endsWith("/") ? rootEntry : rootEntry + "/";
      Enumeration<JarEntry> entries = jar.entries();

      while(entries.hasMoreElements()) {
         JarEntry entry = (JarEntry)entries.nextElement();
         if (entry != null && !entry.isDirectory()) {
            String name = entry.getName();
            if (name.startsWith(prefix)) {
               String relativeName = name.substring(prefix.length());
               if (!relativeName.isEmpty() && !relativeName.contains("\\")) {
                  Path relativePath = Path.of(relativeName).normalize();
                  if (!relativePath.isAbsolute() && !Kcee414d006005211cb(relativePath)) {
                     Path output = normalizedAvatarsDir.resolve(relativePath).normalize();
                     if (output.startsWith(normalizedAvatarsDir)) {
                        Files.createDirectories(output.getParent());
                        InputStream input = jar.getInputStream(entry);

                        try {
                           Kd57cc20aac35ba02ac(input, output, normalizedAvatarsDir, entry.getSize());
                        } catch (Throwable var15) {
                           if (input != null) {
                              try {
                                 input.close();
                              } catch (Throwable var14) {
                                 var15.addSuppressed(var14);
                              }
                           }

                           throw var15;
                        }

                        if (input != null) {
                           input.close();
                        }
                     }
                  }
               }
            }
         }
      }

   }

   private static boolean Kcee414d006005211cb(Path path) {
      return path.getNameCount() > 0 && "..".equals(path.getName(0).toString());
   }

   private static void Ke6d179cd071857b8c4(Path input, Path output, Path avatarsDir) throws IOException {
      Path normalizedOutput = output.normalize();
      Path normalizedAvatarsDir = avatarsDir.normalize();
      if (normalizedOutput.startsWith(normalizedAvatarsDir)) {
         Files.createDirectories(normalizedOutput.getParent());
         long sourceSize = Files.size(input);
         if (Files.exists(normalizedOutput, new LinkOption[0]) && Files.size(normalizedOutput) == sourceSize) {
            SKIPPED_FILES.incrementAndGet();
         } else {
            if (Files.exists(normalizedOutput, new LinkOption[0])) {
               REPLACED_FILES.incrementAndGet();
            } else {
               INSTALLED_FILES.incrementAndGet();
            }

            Files.copy(input, normalizedOutput, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
         }
      }
   }

   private static void Kd57cc20aac35ba02ac(InputStream input, Path output, Path avatarsDir, long sourceSize) throws IOException {
      Path normalizedOutput = output.normalize();
      Path normalizedAvatarsDir = avatarsDir.normalize();
      if (normalizedOutput.startsWith(normalizedAvatarsDir)) {
         Files.createDirectories(normalizedOutput.getParent());
         if (Files.exists(normalizedOutput, new LinkOption[0]) && sourceSize >= 0L && Files.size(normalizedOutput) == sourceSize) {
            SKIPPED_FILES.incrementAndGet();
         } else {
            if (Files.exists(normalizedOutput, new LinkOption[0])) {
               REPLACED_FILES.incrementAndGet();
            } else {
               INSTALLED_FILES.incrementAndGet();
            }

            Files.copy(input, normalizedOutput, new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
         }
      }
   }
}
