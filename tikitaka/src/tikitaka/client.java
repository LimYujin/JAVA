package tikitaka;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.sound.sampled.*;
import java.io.*;
import java.net.*;
import java.util.Iterator;
import java.util.Vector;

public class client extends JFrame implements ActionListener{
	private Image screenImage;
	private Graphics screenGraphic;
	
	private Image background=new ImageIcon(Main.class.getResource("../images/fp_title.jpg")).getImage();//initialize background image
	private JLabel menuBar=new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));//initialize menu bar image
	
	//exit button(ȭ�� ���� x ��ư)
	//each button image has 2 state : running state(cursor �ø� ����), basic state
	private ImageIcon exitButtonEnteredImage=new ImageIcon(Main.class.getResource("../images/exitbuttonr.png"));//store image of exitbutton running
	private ImageIcon exitButtonBasicImage=new ImageIcon(Main.class.getResource("../images/exitbutton.png"));//storre image of exit button basic state
	private JButton exitButton=new JButton(exitButtonBasicImage);//make exit button
	
	//IN start page
	//buttons
	//each button image has 2 state : running state(cursor �ø� ����), basic state
	private ImageIcon startButtonEnteredImage=new ImageIcon(Main.class.getResource("../images/startButtonr.png"));//store image of startbutton running
	private ImageIcon startButtonBasicImage=new ImageIcon(Main.class.getResource("../images/startButton.png"));//store image of startbutton basic state
	private ImageIcon quitButtonEnteredImage=new ImageIcon(Main.class.getResource("../images/quitButtonr.png"));//store image of quitbutton running
	private ImageIcon quitButtonBasicImage=new ImageIcon(Main.class.getResource("../images/quitButton.png"));//store image of quitbutton basic state
	private ImageIcon introductionButtonEnteredImage=new ImageIcon(Main.class.getResource("../images/introductionr.png"));//store image of how_to_play running
	private ImageIcon introductionButtonBasicImage=new ImageIcon(Main.class.getResource("../images/introduction.png"));//store image of how_to_play basic state
	private JButton startButton=new JButton(startButtonBasicImage);//make start button
	private JButton quitButton=new JButton(quitButtonBasicImage);//make quit button
	private JButton introductionButton=new JButton(introductionButtonBasicImage);//make how_to_play button
	
	//go-back(return) button(ȭ�� �ڷ� ���� ��ư)
	//each button image has 2 state : running state(cursor �ø� ����), basic state
	private ImageIcon backButtonEnteredImage=new ImageIcon(Main.class.getResource("../images/backr.png"));
	private ImageIcon backButtonBasicImage=new ImageIcon(Main.class.getResource("../images/back.png"));
	private JButton backButton=new JButton(backButtonBasicImage);//make go_back button
	
	
	//used for location of cursor
		private int mouseX, mouseY;
		
		private int i = 0;
		private final static int DELAY = 10000;
	//////////
	int port = 8800;
	String playerName, playerScore, playerIdx;
	boolean gameStartr;
	
	public client() {
		///���⿡ GUI
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
				//�� �κ��� ���� ��ư ������ "��Ĭ" �Ҹ� ���� �������� .sleep �̿��ؼ� delpay�� �ַ� �ߴµ� �Ҹ� ����..
				try {
					Thread.sleep(1000);
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);//button ����!
		
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
				//first page(title page)�� �ִ� ��ư���� �������� �ϴϱ� �Ⱥ��̰� .setVisible(false) �̿�
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
						//�ٽ� ��ư ���̰�
						startButton.setVisible(true);
						quitButton.setVisible(true);
						introductionButton.setVisible(true);
						backButton.setVisible(false);//first page������ �ڷΰ��� �ʿ�����ϱ� ���ֱ�
					}
				});
				add(backButton);
				backButton.setVisible(true);//how_to_play ȭ���� �ڷΰ��� ��ư ���̰��ϱ�
			}
		});
		add(introductionButton);
				
				
	}
	
	public void game() {
		String ip;
		
		try {
			Socket s = new Socket(ip, port);
			Sender sender = new Sender(s);
			Receiver receiver = new Receiver(s);
			new Thread(sender).start();
			new Thread(receiver).start();
			
			
		}catch(UnknownHostException uh) {
			JOptionPane.showMessageDialog(null, "ȣ��Ʈ�� ã�� ���߽��ϴ�.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}catch(IOException io){
			JOptionPane.showMessageDialog(null, "���� ���� ����!\n������ ������ �ʾҽ��ϴ�.", "ERROR", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitButton){ //�����ư
			int set;
			set = JOptionPane.showConfirmDialog(null, "������ �����Ͻðڽ��ϱ�?", "Exit", JOptionPane.OK_CANCEL_OPTION);
			if(set == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
	
	class Sender extends Thread implements KeyListener, ActionListener, MouseMotionListener, MouseListener {
		DataOutputStream dos;
		Socket s;
		String nickname;
		
		Sender(Socket s, String nickname) {
			this.s = s;
			try{
				dos = new DataOutputStream(this.s.getOutputStream());
				this.nickname = nickname;
			}catch(IOException io){}
		}
		
		public void run(){
			try{
				dos.writeUTF(nickname); //�г����� �Ѱ���
			}catch(IOException io){}
		}
		/*
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == button){ // '�غ�' ��ư�� ������ 15ȸ ������ ���۵�
				try{

				}catch(IOException io){}
			}
		}
		*/
		
		//���콺�Է�
	}//MyPlayer
	
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
