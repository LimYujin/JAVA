package tikitaka;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import javazoom.jl.player.Player;

//using thread to have a game music
public class Music extends Thread {
	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name,boolean isLoop) {
		try {
			this.isLoop=isLoop;
			file=new File(Main.class.getResource("../music/intromusic.mp3").toURI());
			fis=new FileInputStream(file);
			bis=new BufferedInputStream(fis);
			player=new Player(bis);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//let know where the music is played
	public int getTime() {
		if(player==null) {
			return 0;
		}
		return player.getPosition();
	}
	//to sound off
	public void close() {
		isLoop=false;
		player.close();
		this.interrupt();
	}
	
	@Override
	public void run() {
		try {
			do {
				player.play();
				//infinite loop
				fis=new FileInputStream(file);
				bis=new BufferedInputStream(fis);
				player=new Player(bis);
			} while(isLoop);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}