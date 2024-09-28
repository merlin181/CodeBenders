import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer { // Define a public class named MusicPlayer for playing audio files


	// Added a static Clip variable to hold the current clip
	private static Clip clip;
	
	public static void RunMusic(String path) {
		try {
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path)); // Create an AudioInputStream object from the specified file path
			Clip clip = AudioSystem.getClip(); // Get an audio clip from the AudioSystem
			clip.open(inputStream); // Open the audio clip using the input stream
			clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the audio clip indefinitely 
			clip.start(); 
		} catch (UnsupportedAudioFileException e) { // Catch UnsupportedAudioFileException if the audio file is unsupported
			e.printStackTrace(); // Print the error message
		} catch (IOException e) { // Catch IOException if there's an input/output error
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
