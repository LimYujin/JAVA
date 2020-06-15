package tikitaka;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import javazoom.jl.player.Player;
import javax.sound.sampled.*;
import java.io.*;
import java.net.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;


public class client extends JFrame implements ActionListener{
	/*Game game = new Game();
	game.game_init();

	GUI_2 gui = new GUI_2(game);
*/	
	//game set GUI
	//public void GUI_2 extends JFrame {	
		//이미지 변수
		private Image screenImage;
		private Graphics screenGraphic;
		
		private Image background=new ImageIcon(Main.class.getResource("../images/fp_title.jpg")).getImage();//initialize background image
		private JLabel menuBar=new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));//initialize menu bar image
		
		//exit button(화면 끄는 x 버튼)
		//each button image has 2 state : running state(cursor 올린 상태), basic state
		private ImageIcon exitButtonEnteredImage=new ImageIcon(Main.class.getResource("../images/exitbuttonr.png"));//store image of exitbutton running
		private ImageIcon exitButtonBasicImage=new ImageIcon(Main.class.getResource("../images/exitbutton.png"));//store image of exit button basic state
		private JButton exitButton=new JButton(exitButtonBasicImage);//make exit button
		
		//IN start page
		//buttons
		//each button image has 2 state : running state(cursor 올린 상태), basic state
		private ImageIcon startButtonEnteredImage=new ImageIcon(Main.class.getResource("../images/startButtonr.png"));//store image of startbutton running
		private ImageIcon startButtonBasicImage=new ImageIcon(Main.class.getResource("../images/startButton.png"));//store image of startbutton basic state
		private ImageIcon quitButtonEnteredImage=new ImageIcon(Main.class.getResource("../images/quitButtonr.png"));//store image of quitbutton running
		private ImageIcon quitButtonBasicImage=new ImageIcon(Main.class.getResource("../images/quitButton.png"));//store image of quitbutton basic state
		private ImageIcon introductionButtonEnteredImage=new ImageIcon(Main.class.getResource("../images/introductionr.png"));//store image of how_to_play running
		private ImageIcon introductionButtonBasicImage=new ImageIcon(Main.class.getResource("../images/introduction.png"));//store image of how_to_play basic state
		private JButton startButton=new JButton(startButtonBasicImage);//make start button
		private JButton quitButton=new JButton(quitButtonBasicImage);//make quit button
		private JButton introductionButton=new JButton(introductionButtonBasicImage);//make how_to_play button
		
		//go-back(return) button(화면 뒤로 가기 버튼)
		//each button image has 2 state : running state(cursor 올린 상태), basic state
		private ImageIcon backButtonEnteredImage=new ImageIcon(Main.class.getResource("../images/backr.png"));
		private ImageIcon backButtonBasicImage=new ImageIcon(Main.class.getResource("../images/back.png"));
		private JButton backButton=new JButton(backButtonBasicImage);//make go_back button
		
		
	int port = 8800;
	String playerName, playerScore, playerIdx;
	boolean gameStartr;
	
	public client() {
		Game game = new Game();
		game.game_init();

		GUI_2 gui = new GUI_2(game);
		///여기에 GUI
		setUndecorated(true);//not to show original menu bar
		setTitle("TikiTaka Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0,0,0,0));
		setLayout(null);
		
		//menu_Bar> exit button
		exitButton.setBounds(1245,0,30,30);//location of exit button
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(true);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
				//Music buttonEnteredMusic=new Music("buttonEnteredMusic.mp3",false);
				//buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//Music buttonEnteredMusic=new Music("buttonPressedMusic.mp3",false);
				//buttonEnteredMusic.start();
				//이 부분은 원래 버튼 누르면 "딸칵" 소리 나고 끝내려고 .sleep 이용해서 delpay를 주려 했는데 소리 실패..
				try {
					Thread.sleep(1000);
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);//button 생성!
		
		//start button
		startButton.setBounds(240,500,250,75);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(true);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
				//Music buttonEnteredMusic=new Music("buttonEnteredMusic.mp3",false);
				//buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//Music buttonEnteredMusic=new Music("buttonPressedMusic.mp3",false);
				//buttonEnteredMusic.start();
				
				//Game START EVENT
				startButton.setVisible(false);
				quitButton.setVisible(false);
				introductionButton.setVisible(false);
				background=new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
			}
		});
		add(startButton);
		
		//quit button
		quitButton.setBounds(515,500,250,75);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(true);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
				//Music buttonEnteredMusic=new Music("buttonEnteredMusic.mp3",false);
				//buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//Music buttonEnteredMusic=new Music("buttonPressedMusic.mp3",false);
				//buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);
		//introduction button; how_to_play
		introductionButton.setBounds(790,500,250,75);
		introductionButton.setBorderPainted(false);
		introductionButton.setContentAreaFilled(false);
		introductionButton.setFocusPainted(true);
		introductionButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				introductionButton.setIcon(introductionButtonEnteredImage);
				introductionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
				//Music buttonEnteredMusic=new Music("buttonEnteredMusic.mp3",false);
				//buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				introductionButton.setIcon(introductionButtonBasicImage);
				introductionButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//Music buttonEnteredMusic=new Music("buttonPressedMusic.mp3",false);
				//buttonEnteredMusic.start();
			
				//how-to-play-game(instruction/introduction) page
				//first page(title page)에 있던 버튼들이 없어져야 하니까 안보이게 .setVisible(false) 이용
				startButton.setVisible(false);
				quitButton.setVisible(false);
				introductionButton.setVisible(false);
				background=new ImageIcon(Main.class.getResource("../images/instructionBack.jpg")).getImage();//change used background
				
				//go-back button //to first_page
				backButton.setBounds(1170,75,50,50);
				backButton.setBorderPainted(false);
				backButton.setContentAreaFilled(false);
				backButton.setFocusPainted(true);
				backButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						backButton.setIcon(backButtonEnteredImage);
						backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
						
						//Music buttonEnteredMusic=new Music("buttonEnteredMusic.mp3",false);
						//buttonEnteredMusic.start();
					}
					@Override
					public void mouseExited(MouseEvent e) {
						backButton.setIcon(backButtonBasicImage);
						backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
					@Override
					public void mousePressed(MouseEvent e) {
						//Music buttonEnteredMusic=new Music("buttonPressedMusic.mp3",false);
						//buttonEnteredMusic.start();
						background=new ImageIcon(Main.class.getResource("../images/fp_title.jpg")).getImage();//change background image
						//다시 버튼 보이게
						startButton.setVisible(true);
						quitButton.setVisible(true);
						introductionButton.setVisible(true);
						backButton.setVisible(false);//first page에서는 뒤로가기 필요없으니까 없애기
					}
				});
				add(backButton);
				backButton.setVisible(true);//how_to_play 화면의 뒤로가기 버튼 보이게하기
			}
		});
		add(introductionButton);
				
				
	}
	
	public void game() {
		String ip = login.ip;
		String id = login.id;
		
		try {
			System.out.println("client: 접속 요청 중..");
			Socket s = new Socket(ip, port);
			System.out.println("client: 서버 접속 완료");
			Sender sender = new Sender(s, id); //송신
			Receiver receiver = new Receiver(s); //수신
			new Thread(sender).start();
			new Thread(receiver).start();
			
			
		}catch(UnknownHostException uh) {
			JOptionPane.showMessageDialog(null, "호스트를 찾지 못했습니다.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}catch(IOException io){
			JOptionPane.showMessageDialog(null, "서버 접속 실패!\n서버가 열리지 않았습니다.", "ERROR", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == exitButton){ //종료버튼
			int set;
			set = JOptionPane.showConfirmDialog(null, "게임을 종료하시겠습니까?", "Exit", JOptionPane.OK_CANCEL_OPTION);
			if(set == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
	
	class Sender extends Thread implements ActionListener {
		DataOutputStream dos;
		Socket s;
		String id;
		
		Sender(Socket s, String id) {
			this.s = s;
			try{
				dos = new DataOutputStream(this.s.getOutputStream());
				this.id = id;
			}catch(IOException io){}
		}
		
		public void run(){
			try{
				dos.writeUTF(id); //id를 넘겨줌
			}catch(IOException io){}
		}
		public void actionPerformed(ActionEvent e) {
			//보드판 안에 있는 버튼들에 대해서
			
			//if(e.getSource() == Mission_button) {}
		}
		

	}//MyPlayer
	
	class Music1 extends Thread {
		private Player player;
		private boolean isLoop;
		private File file;
		private FileInputStream fis;
		private BufferedInputStream bis;
		
		public Music1(String name,boolean isLoop) {
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

	class Receiver extends Thread {
		Socket s;
		DataInputStream dis;
		DataOutputStream dos;
		Receiver(Socket s){
			this.s = s;
			try{
				dis = new DataInputStream(this.s.getInputStream());
				dos = new DataOutputStream(this.s.getOutputStream());
			}catch(IOException io){}
		}
		
		public void run() {
			while(dis != null) {
			}
		}
	}
	
}
