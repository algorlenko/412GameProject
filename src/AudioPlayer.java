import javax.sound.sampled.*;

public class AudioPlayer {
	
	private Clip clip;
	
	public AudioPlayer(String s) {
		
		try {
			
			AudioInputStream ais =
				AudioSystem.getAudioInputStream(
					getClass().getResourceAsStream(
						s
					)
				);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void play() {
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void stop() {
		if(clip.isRunning()) clip.stop();
	}
	
	public void close() {
		stop();
		clip.close();
	}
	
}














