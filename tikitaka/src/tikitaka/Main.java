package tikitaka;

import java.awt.event.ActionEvent;

public class Main {
	public static final int SCREEN_WIDTH=1280;
	public static final int SCREEN_HEIGHT=720;
	
	public static void main(String[] args) {
		/*
		login login = new login();
		login.init();*/
		Game game = new Game();
		game.game_init();
		GUI_2 gui = new GUI_2(game);

		
	}
}