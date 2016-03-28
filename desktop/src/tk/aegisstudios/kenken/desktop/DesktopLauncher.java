package tk.aegisstudios.kenken.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import tk.aegisstudios.kenken.KenKen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "KenKen";
		config.width = 800;
		config.height = 480;
		config.addIcon("img/kenken.png", Files.FileType.Internal);
		new LwjglApplication(new KenKen(), config);
	}
}