package code.miaware.client.ui.clickgui;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWGamepadState;

public class Ke35121f65fedb1fbca {
   private static final int JOYSTICK_ID = 0;
   private static final float DEAD_ZONE = 0.3F;
   private static boolean[] lastButtonState = new boolean[15];
   private static long lastNavigationTime = 0L;
   private static final long NAVIGATION_COOLDOWN = 200L;

   public static boolean isGamepadConnected() {
      return GLFW.glfwJoystickPresent(0) && GLFW.glfwJoystickIsGamepad(0);
   }

   public static GLFWGamepadState getGamepadState() {
      if (!isGamepadConnected()) {
         return null;
      } else {
         GLFWGamepadState state = GLFWGamepadState.create();
         return GLFW.glfwGetGamepadState(0, state) ? state : null;
      }
   }

   public static boolean isButtonPressed(int button) {
      GLFWGamepadState state = getGamepadState();
      if (state == null) {
         return false;
      } else {
         boolean currentState = state.buttons(button) == 1;
         boolean wasPressed = !lastButtonState[button] && currentState;
         lastButtonState[button] = currentState;
         return wasPressed;
      }
   }

   public static boolean isButtonDown(int button) {
      GLFWGamepadState state = getGamepadState();
      if (state == null) {
         return false;
      } else {
         return state.buttons(button) == 1;
      }
   }

   public static float getLeftStickX() {
      GLFWGamepadState state = getGamepadState();
      if (state == null) {
         return 0.0F;
      } else {
         float value = state.axes(0);
         return Math.abs(value) < 0.3F ? 0.0F : value;
      }
   }

   public static float getLeftStickY() {
      GLFWGamepadState state = getGamepadState();
      if (state == null) {
         return 0.0F;
      } else {
         float value = state.axes(1);
         return Math.abs(value) < 0.3F ? 0.0F : value;
      }
   }

   public static boolean canNavigate() {
      long currentTime = System.currentTimeMillis();
      if (currentTime - lastNavigationTime >= 200L) {
         lastNavigationTime = currentTime;
         return true;
      } else {
         return false;
      }
   }

   public static boolean isDPadUp() {
      return isButtonPressed(11);
   }

   public static boolean isDPadDown() {
      return isButtonPressed(13);
   }

   public static boolean isDPadLeft() {
      return isButtonPressed(14);
   }

   public static boolean isDPadRight() {
      return isButtonPressed(12);
   }

   public static boolean isButtonA() {
      return isButtonPressed(0);
   }

   public static boolean isButtonB() {
      return isButtonPressed(1);
   }

   public static boolean isButtonX() {
      return isButtonPressed(2);
   }

   public static boolean isButtonY() {
      return isButtonPressed(3);
   }

   public static void reset() {
      lastButtonState = new boolean[15];
      lastNavigationTime = 0L;
   }
}
