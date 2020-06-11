package tikitaka;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;


public class GUI extends JFrame {
	//이미지 변수
	private Image screenImage;
	private Graphics screenGraphic;
	
	private Image background=new ImageIcon(Main.class.getResource("../images/fp_title.jpg")).getImage();//initialize background image
	private JLabel menuBar=new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));//initialize menu bar image
	
	//exit button(화면 끄는 x 버튼)
	//each button image has 2 state : running state(cursor 올린 상태), basic state
	private ImageIcon exitButtonEnteredImage=new ImageIcon(Main.class.getResource("../images/exitbuttonr.png"));//store image of exitbutton running
	private ImageIcon exitButtonBasicImage=new ImageIcon(Main.class.getResource("../images/exitbutton.png"));//storre image of exit button basic state
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
	
	//GAME page
	//..//
	
	//used for location of cursor
	private int mouseX, mouseY;
	
	private int i = 0;
	private final static int DELAY = 10000;
	
	public GUI() {
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
		
		//menu_Bar
		menuBar.setBounds(0,0,1280,30);//locate menu bar
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX=e.getX();
				mouseY=e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x=e.getXOnScreen();
				int y=e.getYOnScreen();
				setLocation(x-mouseX,y-mouseY);
			}
		});//when menu bar is dragged, automatically game.exe is also dragged
		add(menuBar);
		
		//music (출처: joakim karud music)
		Music introMusic=new Music("intromusic.mp3",true);
		introMusic.start();
		
		//계속 반복하여 재생되게
		Timer timer = new Timer(DELAY, new ActionListener() {
			 @Override
		     public void actionPerformed(ActionEvent e) {
				 i++;
				 if (i == 4) i=0;
		     }
		});
		timer.start();
	}
	
	//JFrame을 상속받은 GUI 게임에서 가장 먼저 실행되는 METHOD
	public void paint(Graphics g) {
		screenImage=createImage(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		screenGraphic=screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(background,0,0,null);//drawing background
		paintComponents(g);//drawing jLabel 
		repaint(); 
	}
}