/*
 * Romela Azizyan
 * CS 3230 - MahJong
*/
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.*;

public class Sounds {
	
	private static Boolean soundon = true;

	public void singleClick() {
		if (soundon == true)
			try {
				URL url = this.getClass().getResource("sounds/singleclick.wav");
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
				AudioFormat format = audioStream.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				Clip audioClip = (Clip) AudioSystem.getLine(info);
				audioClip.open(audioStream);
				audioClip.start();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	}

	public void noMatchClick() {
		if (soundon == true)
			try {
				URL url = this.getClass().getResource("sounds/nomatch.wav");
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
				AudioFormat format = audioStream.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				Clip audioClip = (Clip) AudioSystem.getLine(info);
				audioClip.open(audioStream);
				audioClip.start();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	}

	public void doubleClick() {
		if (soundon == true)
			try {
				URL url = this.getClass().getResource("sounds/doubleclick.wav");
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
				AudioFormat format = audioStream.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				Clip audioClip = (Clip) AudioSystem.getLine(info);
				audioClip.open(audioStream);
				audioClip.start();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	}

	public static Boolean getSoundon(){return soundon;}

	public static void setSoundon(Boolean soundon){Sounds.soundon = soundon;}

}
