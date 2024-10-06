import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class MusicPlayer {
	private Clip clip;
	private boolean playing;
	
	public MusicPlayer() {
		
		try{
	    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("Background_Music.wav"));
		clip = AudioSystem.getClip();
		clip.open(inputStream);
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void runMusic() {
		playing = true;
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
	}
	
	public void stopMusic() {
		playing = false;
		clip.stop();
	}
	
	public boolean isPlaying() {
		return playing;
	}
	
}