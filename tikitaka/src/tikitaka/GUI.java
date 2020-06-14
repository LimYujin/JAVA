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


public class GUI extends JFrame{
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
	
	//액션카드 버튼 이미지
	private ImageIcon actioncardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/actioncard.png")); 
	private JButton actioncardButton =new JButton(actioncardButtonBasicImage);
	private ImageIcon ac_UpOneButtonBasicImage = new ImageIcon(Main.class.getResource("../images/ac2.png")); 
	private JButton ac_UpOneButton =new JButton(ac_UpOneButtonBasicImage);
	private ImageIcon ac_UpTwoButtonBasicImage = new ImageIcon(Main.class.getResource("../images/ac3.png")); 
	private JButton ac_UpTwoButton =new JButton(ac_UpTwoButtonBasicImage);
	private ImageIcon ac_UpThreeButtonBasicImage = new ImageIcon(Main.class.getResource("../images/ac1.png")); 
	private JButton ac_UpThreeButton =new JButton(ac_UpThreeButtonBasicImage);
	private ImageIcon ac_DownButtonBasicImage = new ImageIcon(Main.class.getResource("../images/ac6.png")); 
	private JButton ac_DownButton =new JButton(ac_DownButtonBasicImage);
	private ImageIcon ac_RemoveButtonBasicImage = new ImageIcon(Main.class.getResource("../images/ac5.png")); 
	private JButton ac_RemoveButton =new JButton(ac_RemoveButtonBasicImage);
	
	//색깔 블럭 버튼 이미지
	private ImageIcon RedBlockButtonBasicImage = new ImageIcon(Main.class.getResource("../images/b1.png")); //red
	private JButton redBlockButton =new JButton(RedBlockButtonBasicImage);
	private ImageIcon orangeBlockButtonBasicImage = new ImageIcon(Main.class.getResource("../images/b2.png")); //orange
	private JButton orangeBlockButton =new JButton(orangeBlockButtonBasicImage);
	private ImageIcon block3ButtonBasicImage = new ImageIcon(Main.class.getResource("../images/b3.png")); //yellow
	private JButton yellowBlockButton =new JButton(block3ButtonBasicImage);
	private ImageIcon block4ButtonBasicImage = new ImageIcon(Main.class.getResource("../images/b4.png")); //green
	private JButton greenBlockButton =new JButton(block4ButtonBasicImage);
	private ImageIcon block5ButtonBasicImage = new ImageIcon(Main.class.getResource("../images/b5.png")); //sky
	private JButton skyBlockButton =new JButton(block5ButtonBasicImage);
	private ImageIcon block6ButtonBasicImage = new ImageIcon(Main.class.getResource("../images/b6.png")); //blue
	private JButton blueBlockButton =new JButton(block6ButtonBasicImage);
	private ImageIcon block7ButtonBasicImage = new ImageIcon(Main.class.getResource("../images/b7.png")); //purple
	private JButton purpleBlockButton =new JButton(block7ButtonBasicImage);
	private ImageIcon block8ButtonBasicImage = new ImageIcon(Main.class.getResource("../images/b8.png")); //pink
	private JButton pinkBlockButton =new JButton(block8ButtonBasicImage);
	private ImageIcon block9ButtonBasicImage = new ImageIcon(Main.class.getResource("../images/b9.png")); //gray
	private JButton grayBlockButton =new JButton(block9ButtonBasicImage);
	
	
	//GAME page
	//..//
	
	//used for location of cursor
	private int mouseX, mouseY;
	
	private int i = 0;
	private final static int DELAY = 10000;
	
	public GUI(Game game) {
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
				openActioncardButton(game); //유진: 스타트 누르면 액션카드 표지 나오도록 한 것! 누르면 카드 선택하는거 뜸
				
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
	
	//유진: 스타트 누르면 액션카드 표지 나오도록 한 것! 누르면 카드 선택하는거 뜸
	void openActioncardButton(Game game) {
		//actioncard button
		actioncardButton.setBounds(500, 500, 300, 100); //유진: 이거 위치 수정 부탁!
		actioncardButton.setBorderPainted(false);
		actioncardButton.setContentAreaFilled(false);
		actioncardButton.setFocusPainted(true);
		actioncardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//Music buttonEnteredMusic=new Music("buttonPressedMusic.mp3",false);
				//buttonEnteredMusic.start();
				
				//open actioncard event
				background=new ImageIcon(Main.class.getResource("../images/checkActioncard.png")).getImage(); //액션카드 목록 보이도록! 배경 사진 변경 부탁!
				actioncardButton.setVisible(false); //액션카드 open하는버튼 off
				chooseCard(game); //유진: current_player의 남은 카드들 버튼으로 보여주기
			}
		});
		add(actioncardButton);
	}
	
	//유진: current_player의 남은 카드들 버튼으로 보여주기
	void chooseCard(Game game) {
		//유진: 각 조건문은 current_player의 카드가 남아있으면 해당 카드 버튼이 뜨도록 하였음
		if(game.current_player.action_card.upone.num_thiscard>0) 
			ac_UpOneButton(game); //유진:
		
		if(game.current_player.action_card.uptwo.num_thiscard>0)
			ac_UpTwoButton(game);
		
		if(game.current_player.action_card.upthree.num_thiscard>0)
			ac_UpThreeButton(game);
		
		if(game.current_player.action_card.down.num_thiscard>0)
			ac_DownButton(game);
		
		if(game.current_player.action_card.remove.num_thiscard>0)
			ac_RemoveButton(game);
	}
	
	//액션카드 선택 후 남아있는 블럭들 중에서 선택할 수 있도록 블럭을 버튼으로 만들기
	void chooseBlock(Game game) {
		
		//액션카드 버튼들 안보이게끔
		ac_UpOneButton.setVisible(false);
		ac_UpTwoButton.setVisible(false);
		ac_UpThreeButton.setVisible(false);
		ac_DownButton.setVisible(false);
		ac_RemoveButton.setVisible(false);
		
		////블럭버튼들
		blockButton(game);
		
	}
	
	void blockButton(Game game) {
		Block temp_block;
		int height;
		for(int i = 0; i<9; i++) { //9개의 색 블럭 확인
			for(int j = 0; j<game.num_block;j++) { //남은 갯수 만큼의 등수 확인
				if(game.block[i].order == j) {
					height = 30+74*(j-1); //해당 등수의 위치 설정
					temp_block = game.block[i];  //red
					if(game.block[i].exist)
						makeBlock(game, temp_block, i, height);//i에 따라 색 구분할 수 있게 매개변수로
				}
			}
		}
	}
	
	void makeBlock(Game game, Block block, int index, int height) {
		
		if(index == 0) {
			redBlockButton.setBounds(594, height, 78, 78);
			redBlockButton.setBorderPainted(false);
			redBlockButton.setContentAreaFilled(false);
			redBlockButton.setFocusPainted(true);
			redBlockButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					//choose_block에 Block 변수로 넘겨주기
					game.current_player.action_card.choose_block = block;
				}
			});
			add(redBlockButton);
		}else if(index == 1) {
			orangeBlockButton.setBounds(594, height, 78, 78);
			orangeBlockButton.setBorderPainted(false);
			orangeBlockButton.setContentAreaFilled(false);
			orangeBlockButton.setFocusPainted(true);
			orangeBlockButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					//매개변수 넘겨주기
					game.current_player.action_card.choose_block = block;
				}
			});
			add(orangeBlockButton);

		}else if(index == 2) {
			yellowBlockButton.setBounds(594, height, 78, 78);
			yellowBlockButton.setBorderPainted(false);
			yellowBlockButton.setContentAreaFilled(false);
			yellowBlockButton.setFocusPainted(true);
			yellowBlockButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					//매개변수 넘겨주기
					game.current_player.action_card.choose_block = block;
				}
			});
			add(yellowBlockButton);
			
		}else if(index == 3) {
			greenBlockButton.setBounds(594, height, 78, 78);
			greenBlockButton.setBorderPainted(false);
			greenBlockButton.setContentAreaFilled(false);
			greenBlockButton.setFocusPainted(true);
			greenBlockButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					//매개변수 넘겨주기
					game.current_player.action_card.choose_block = block;
				}
			});
			add(greenBlockButton);
			
		}else if(index == 4) {
			skyBlockButton.setBounds(594, height, 78, 78);
			skyBlockButton.setBorderPainted(false);
			skyBlockButton.setContentAreaFilled(false);
			skyBlockButton.setFocusPainted(true);
			skyBlockButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					//매개변수 넘겨주기
					game.current_player.action_card.choose_block = block;
				}
			});
			add(skyBlockButton);
			
		}else if(index == 5) {
			blueBlockButton.setBounds(594, height, 78, 78);
			blueBlockButton.setBorderPainted(false);
			blueBlockButton.setContentAreaFilled(false);
			blueBlockButton.setFocusPainted(true);
			blueBlockButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					//매개변수 넘겨주기
					game.current_player.action_card.choose_block = block;
				}
			});
			add(blueBlockButton);
			
		}else if(index == 6) {
			purpleBlockButton.setBounds(594, height, 78, 78);
			purpleBlockButton.setBorderPainted(false);
			purpleBlockButton.setContentAreaFilled(false);
			purpleBlockButton.setFocusPainted(true);
			purpleBlockButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					//매개변수 넘겨주기
					game.current_player.action_card.choose_block = block;
				}
			});
			add(purpleBlockButton);
			
		}else if(index == 7) {
			pinkBlockButton.setBounds(594, height, 78, 78);
			pinkBlockButton.setBorderPainted(false);
			pinkBlockButton.setContentAreaFilled(false);
			pinkBlockButton.setFocusPainted(true);
			pinkBlockButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					//매개변수 넘겨주기
					game.current_player.action_card.choose_block = block;
				}
			});
			add(pinkBlockButton);
			
		}else if(index == 8) {
			grayBlockButton.setBounds(594, height, 78, 78);
			grayBlockButton.setBorderPainted(false);
			grayBlockButton.setContentAreaFilled(false);
			grayBlockButton.setFocusPainted(true);
			grayBlockButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					//매개변수 넘겨주기
					game.current_player.action_card.choose_block = block;
				}
			});
			add(grayBlockButton);
		}
	}
	void ac_UpOneButton(Game game) {
		ac_UpOneButton.setBounds(300, 300, 200, 200);
		ac_UpOneButton.setBorderPainted(false);
		ac_UpOneButton.setContentAreaFilled(false);
		ac_UpOneButton.setFocusPainted(true);
		ac_UpOneButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//Music buttonEnteredMusic=new Music("buttonPressedMusic.mp3",false);
				//buttonEnteredMusic.start();
				
				//choose a block
				background=new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
				actioncardButton.setVisible(true); //다시 액션카드 open버튼 on
				chooseBlock(game); //현재 상황에 맞춰 남은 블럭 버튼들 보여주기 
				game.current_player.action_card.upone.function();
				
			}
		});
		add(ac_UpOneButton);
	}
	
	void ac_UpTwoButton(Game game) {
		ac_UpTwoButton.setBounds(400, 300, 200, 200);
		ac_UpTwoButton.setBorderPainted(false);
		ac_UpTwoButton.setContentAreaFilled(false);
		ac_UpTwoButton.setFocusPainted(true);
		ac_UpTwoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//Music buttonEnteredMusic=new Music("buttonPressedMusic.mp3",false);
				//buttonEnteredMusic.start();
				
				//choose a block
				background=new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
				actioncardButton.setVisible(true); //다시 액션카드 open버튼 on
				chooseBlock(game); //현재 상황에 맞춰 남은 블럭 버튼들 보여주기 
				game.current_player.action_card.uptwo.function();
				
			}
		});
		add(ac_UpTwoButton);
	}
	
	void ac_UpThreeButton(Game game) {
		ac_UpThreeButton.setBounds(500, 300, 200, 200);
		ac_UpThreeButton.setBorderPainted(false);
		ac_UpThreeButton.setContentAreaFilled(false);
		ac_UpThreeButton.setFocusPainted(true);
		ac_UpThreeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//Music buttonEnteredMusic=new Music("buttonPressedMusic.mp3",false);
				//buttonEnteredMusic.start();
				
				//choose a block
				background=new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
				actioncardButton.setVisible(true); //다시 액션카드 open버튼 on
				chooseBlock(game); //현재 상황에 맞춰 남은 블럭 버튼들 보여주기 
				game.current_player.action_card.upthree.function();
			}
		});
		add(ac_UpThreeButton);
	}
	
	void ac_DownButton(Game game) {
		ac_DownButton.setBounds(600, 300, 200, 200);
		ac_DownButton.setBorderPainted(false);
		ac_DownButton.setContentAreaFilled(false);
		ac_DownButton.setFocusPainted(true);
		ac_DownButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {				
				//choose a block
				background=new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
				actioncardButton.setVisible(true); //다시 액션카드 open버튼 on
				chooseBlock(game); //현재 상황에 맞춰 남은 블럭 버튼들 보여주기 
				game.current_player.action_card.down.function();
			}
		});
		add(ac_DownButton);
	}
	
	void ac_RemoveButton(Game game) {
		ac_RemoveButton.setBounds(700, 300, 200, 200);
		ac_RemoveButton.setBorderPainted(false);
		ac_RemoveButton.setContentAreaFilled(false);
		ac_RemoveButton.setFocusPainted(true);
		ac_RemoveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//Music buttonEnteredMusic=new Music("buttonPressedMusic.mp3",false);
				//buttonEnteredMusic.start();
				
				//choose a block
				background=new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
				actioncardButton.setVisible(true); //다시 액션카드 open버튼 on
				chooseBlock(game); //현재 상황에 맞춰 남은 블럭 버튼들 보여주기 
				game.current_player.action_card.remove.function();
				
			}
		});
		add(ac_RemoveButton);
	}
}