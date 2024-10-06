import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffects {
    public static void playBombSound() {
        playSound("BombSoundEffect.wav");
    }

    public static void playMoleHitSound() {
        playSound("MoleSoundEffect.wav");
    }

    public static void playPowerUpSound() {
        playSound("TimePowerUpEffect.wav");
    }

    private static void playSound(String fileName) { // Private method to play a sound file
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fileName));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error playing sound: " + e.getMessage());
        }
    }
}
