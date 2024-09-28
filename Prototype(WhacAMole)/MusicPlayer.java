import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {

	// Added a static Clip variable to hold the current clip
	private static Clip clip;
	
	public static void RunMusic(String path) {
		try {
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
			Clip clip = AudioSystem.getClip();
			clip.open(inputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	// Added a stopMusic method to stop the current clip
	public static void stopMusic() {
        // Check if clip is not null before stopping
        if (clip != null) {
            clip.stop();
            clip.close();
            clip = null;
		}
	}
}
