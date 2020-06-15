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
//import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

//game set GUI
public class GUI_2 extends JFrame {
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
	
	
	//GAME page***************************************************************************************
	private ImageIcon actionCard=new ImageIcon(Main.class.getResource("../images/actioncard.png"));
	private ImageIcon missionCard=new ImageIcon(Main.class.getResource("../images/missioncard.png"));
	private JButton actionCheck=new JButton(actionCard);
	private JButton missionCheck=new JButton(missionCard);
	
	ImageIcon block1=new ImageIcon(Main.class.getResource("../images/b1.png"));
	JButton redBlockButton=new JButton(block1);
	ImageIcon block2=new ImageIcon(Main.class.getResource("../images/b2.png"));
	JButton orangeBlockButton=new JButton(block2);
	ImageIcon block3=new ImageIcon(Main.class.getResource("../images/b3.png"));
	JButton yellowBlockButton=new JButton(block3);
	ImageIcon block4=new ImageIcon(Main.class.getResource("../images/b4.png"));
	JButton greenBlockButton=new JButton(block4);
	ImageIcon block5=new ImageIcon(Main.class.getResource("../images/b5.png"));
	JButton skyBlockButton=new JButton(block5);
	ImageIcon block6=new ImageIcon(Main.class.getResource("../images/b6.png"));
	JButton blueBlockButton=new JButton(block6);
	ImageIcon block7=new ImageIcon(Main.class.getResource("../images/b7.png"));
	JButton purpleBlockButton=new JButton(block7);
	ImageIcon block8=new ImageIcon(Main.class.getResource("../images/b8.png"));
	JButton pinkBlockButton=new JButton(block8);
	ImageIcon block9=new ImageIcon(Main.class.getResource("../images/b9.png"));
	JButton grayBlockButton=new JButton(block9);
	//********************************************************************************************************

	//유진: 액션카드 버튼 이미지///////////////////////////////////////////////////////////////////////////////////////////////////
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
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//used for location of cursor
	private int mouseX, mouseY;
	
	private int i = 0;
	private final static int DELAY = 10000;
	
	public GUI_2(Game game) {
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
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {

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
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
			//Game START EVENT *********************************************************************************************//
			    
				startButton.setVisible(false);
				quitButton.setVisible(false);
				introductionButton.setVisible(false);
				background=new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
				
				//blocks
				for(int i=0;i<9;i++) {
					blocks_set(game, game.block[i].order, i);
				}
				
				//actionCard open해주는 버튼
				actionCheck.setBounds(720,45,193,296);
				actionCheck.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						background=new ImageIcon(Main.class.getResource("../images/checkActioncard.png")).getImage();
						actionCheck.setVisible(false); //액션카드 버튼 off
						missionCheck.setVisible(false); //미션카드 버튼 off
						//보드판의 블럭 버튼들 안보이게
						redBlockButton.setVisible(false); orangeBlockButton.setVisible(false); yellowBlockButton.setVisible(false); 
						greenBlockButton.setVisible(false); skyBlockButton.setVisible(false); blueBlockButton.setVisible(false); 
						purpleBlockButton.setVisible(false); pinkBlockButton.setVisible(false); grayBlockButton.setVisible(false);
						
						//카드 버튼으로 선택하기
						chooseCard(game);
					}
				});
				add(actionCheck);
				
				//mission card
				missionCheck.setBounds(720,400,193,296);
				//p1, p2에 따라 mission card에 포함될 block 색 표현하는 label 설정
			    JPanel p1_mc=new JPanel();
			    JPanel p2_mc=new JPanel();
			    addPanel_mc(p1_mc, game.player1.misson.first_color);
			    addPanel_mc(p1_mc, game.player1.misson.second_color);
			    addPanel_mc(p1_mc, game.player1.misson.third_color);//p1의 mission
			    addPanel_mc(p2_mc, game.player2.misson.first_color);
			    addPanel_mc(p2_mc, game.player2.misson.second_color);
			    addPanel_mc(p2_mc, game.player2.misson.third_color);//p2의 mission
			    p1_mc.setSize(119,380);
			    p2_mc.setSize(119,380);//사이즈 정하기
			    p1_mc.setLocation(600,220);
			    p2_mc.setLocation(600,220);//위치 정하기
				missionCheck.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						background=new ImageIcon(Main.class.getResource("../images/checkMissioncard.png")).getImage();
						actionCheck.setVisible(false);
						missionCheck.setVisible(false);
						redBlockButton.setVisible(false); orangeBlockButton.setVisible(false); yellowBlockButton.setVisible(false); greenBlockButton.setVisible(false); skyBlockButton.setVisible(false); blueBlockButton.setVisible(false); purpleBlockButton.setVisible(false); pinkBlockButton.setVisible(false); grayBlockButton.setVisible(false);
						
						/***socket이랑 같이 해봐야 할 듯; 조건문 이용해야하려나..?? 일단 임시로 p1거로 받음***/
						//p1의 click인지 p2의 click인지 확인
						add(p1_mc);
						p1_mc.setVisible(true);
						/*
						 * add(p2_mc);
						 * p2_mc.setVisible(true);
						 */
						//
						
						//뒤로 가기 버튼
						backButton.setBounds(1100,30,50,50);
						backButton.setBorderPainted(false);
						backButton.setContentAreaFilled(false);
						backButton.setFocusPainted(true);
						backButton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								backButton.setIcon(backButtonEnteredImage);
								backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
							}
							@Override
							public void mouseExited(MouseEvent e) {
								backButton.setIcon(backButtonBasicImage);
								backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
							}
							@Override
							public void mousePressed(MouseEvent e) {
								background=new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
								backButton.setVisible(false);
								startButton.setVisible(false);
								quitButton.setVisible(false);
								introductionButton.setVisible(false);
								actionCheck.setVisible(true);
								missionCheck.setVisible(true);
								redBlockButton.setVisible(true); orangeBlockButton.setVisible(true); yellowBlockButton.setVisible(true); greenBlockButton.setVisible(true); skyBlockButton.setVisible(true); blueBlockButton.setVisible(true); purpleBlockButton.setVisible(true); pinkBlockButton.setVisible(true); grayBlockButton.setVisible(true);
								p1_mc.setVisible(false);
								p2_mc.setVisible(false);
							}
						});
						add(backButton);
						backButton.setVisible(true);
					}
				});
				add(missionCheck);
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
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
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
			}
			@Override
			public void mouseExited(MouseEvent e) {
				introductionButton.setIcon(introductionButtonBasicImage);
				introductionButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
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
					}
					@Override
					public void mouseExited(MouseEvent e) {
						backButton.setIcon(backButtonBasicImage);
						backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
					@Override
					public void mousePressed(MouseEvent e) {
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
	}//여기까지 GUI_2 생성자
	
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
		
	//blocks button set
	//a가 블럭 순서, i가 포문 갯수
	public void blocks_set(Game game, int a, int i) {
		int height=30+74*(a-1);
		if(game.block[i].exist) {   ////블럭이 존재한다면 만들어주도록 추가
			switch(i) {
	    	case 0:
	    		redBlockButton.setBounds(594,height,78,78);;
	    		redBlockButton.setVisible(true);
	    		add(redBlockButton);
	    		break;
	    	case 1:
	    		orangeBlockButton.setBounds(594,height,78,78);
	    		orangeBlockButton.setVisible(true);
	    		add(orangeBlockButton);
	    		break;
	    	case 2:
	    		yellowBlockButton.setBounds(594,height,78,78);
	    		yellowBlockButton.setVisible(true);
	    		add(yellowBlockButton);
	    		break;
	    	case 3:
	    		greenBlockButton.setBounds(594,height,78,78);
	    		greenBlockButton.setVisible(true);
	    		add(greenBlockButton);
	    		break;
	    	case 4:
	    		skyBlockButton.setBounds(594,height,78,78);
	    		skyBlockButton.setVisible(true);
	    		add(skyBlockButton);
	    		break;
	    	case 5:
	    		blueBlockButton.setBounds(594,height,78,78);
	    		blueBlockButton.setVisible(true);
	    		add(blueBlockButton);
	    		break;
	    	case 6:
	    		purpleBlockButton.setBounds(594,height,78,78);
	    		purpleBlockButton.setVisible(true);
	    		add(purpleBlockButton);
	    		break;
	    	case 7:
	    		pinkBlockButton.setBounds(594,height,78,78);
	    		pinkBlockButton.setVisible(true);
	    		add(pinkBlockButton);
	    		break;
	    	case 8:
	    		grayBlockButton.setBounds(594,height,78,78);
	    		grayBlockButton.setVisible(true);
	    		add(grayBlockButton);
	    		break;
			}
    	}
	}//여기까지 blocks_set함수
	
	//mission card 버튼 누르면 표시되는 mission set
	public void addPanel_mc(JPanel jp, String s) {
		switch(s) {
    	case "red":
    		ImageIcon p1_mc1=new ImageIcon(Main.class.getResource("../images/b1_2.png"));
    		JLabel jl1=new JLabel(p1_mc1);
    		jp.add(jl1);
    		break;
    	case "orange":
    		ImageIcon p1_mc2=new ImageIcon(Main.class.getResource("../images/b2_2.png"));
    		JLabel jl2=new JLabel(p1_mc2);
    		jp.add(jl2);
    		break;
    	case "yellow":
    		ImageIcon p1_mc3=new ImageIcon(Main.class.getResource("../images/b3_2.png"));
    		JLabel jl3=new JLabel(p1_mc3);
    		jp.add(jl3);
    		break;
    	case "green":
    		ImageIcon p1_mc4=new ImageIcon(Main.class.getResource("../images/b4_2.png"));
    		JLabel jl4=new JLabel(p1_mc4);
    		jp.add(jl4);
    		break;
    	case "sky":
    		ImageIcon p1_mc5=new ImageIcon(Main.class.getResource("../images/b5_2.png"));
    		JLabel jl5=new JLabel(p1_mc5);
    		jp.add(jl5);
    		break;
    	case "blue":
    		ImageIcon p1_mc6=new ImageIcon(Main.class.getResource("../images/b6_2.png"));
    		JLabel jl6=new JLabel(p1_mc6);
    		jp.add(jl6);
    		break;
    	case "purple":
    		ImageIcon p1_mc7=new ImageIcon(Main.class.getResource("../images/b7_2.png"));
    		JLabel jl7=new JLabel(p1_mc7);
    		jp.add(jl7);
    		break;
    	case "pink":
    		ImageIcon p1_mc8=new ImageIcon(Main.class.getResource("../images/b8_2.png"));
    		JLabel jl8=new JLabel(p1_mc8);
    		jp.add(jl8);
    		break;
    	case "gray":
    		ImageIcon p1_mc9=new ImageIcon(Main.class.getResource("../images/b9_2.png"));
    		JLabel jl9=new JLabel(p1_mc9);
    		jp.add(jl9);
    		break;
    	}
	}
	
	//유진: 액션카드 실행에 관한 함수들///////////////////////////////////////////////////////////////
	//유진: current_player의 남은 카드들 버튼으로 보여주기
		void chooseCard(Game game) {
			//유진: 각 조건문은 current_player의 카드가 남아있으면 해당 카드 버튼이 뜨도록 하였음
			System.out.println("Player : " + game.current_player.id);
			if(game.current_player.action_card.upone.num_thiscard>0) 
				ac_UpOneButton(game); //유진: 해당 카드 버튼 띄우기
			
			if(game.current_player.action_card.uptwo.num_thiscard>0)
				ac_UpTwoButton(game);
			
			if(game.current_player.action_card.upthree.num_thiscard>0)
				ac_UpThreeButton(game);
			
			if(game.current_player.action_card.down.num_thiscard>0)
				ac_DownButton(game);
			
			if(game.current_player.action_card.remove.num_thiscard>0)
				ac_RemoveButton(game);
		}
		//유진: 해당 카드 버튼 띄우기
		void ac_UpOneButton(Game game) {
			ac_UpOneButton.setBounds(625, 85, 184, 286);
			ac_UpOneButton.setVisible(true);
			
			ac_UpOneButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					//choose a block
					background=new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
					chooseBlock(game, "upone"); //현재 상황에 맞춰 남은 블럭 버튼들 보여주기 
				}
			});
			add(ac_UpOneButton);
		}
		
		void ac_UpTwoButton(Game game) {
			ac_UpTwoButton.setBounds(830, 85, 184, 286);
			ac_UpTwoButton.setVisible(true);
			ac_UpTwoButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					//choose a block
					background=new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
					chooseBlock(game, "uptwo");//현재 상황에 맞춰 남은 블럭 버튼들 보여주기 
				}
			});
			add(ac_UpTwoButton);
		}
		
		void ac_UpThreeButton(Game game) {
			ac_UpThreeButton.setBounds(415, 85, 184, 286);
			ac_UpThreeButton.setVisible(true);
			ac_UpThreeButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					//choose a block
					background=new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
					chooseBlock(game, "upthree"); //현재 상황에 맞춰 남은 블럭 버튼들 보여주기 
				}
			});
			add(ac_UpThreeButton);
		}
		
		void ac_DownButton(Game game) {
			ac_DownButton.setBounds(830, 400, 184, 286);
			ac_DownButton.setVisible(true);
			ac_DownButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {				
					//choose a block
					background=new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
					chooseBlock(game, "down"); //현재 상황에 맞춰 남은 블럭 버튼들 보여주기 
					game.current_player.action_card.down.function(game);//함수 내에서 해당 카드 기능 실행하기
				}
			});
			add(ac_DownButton);
		}
		
		void ac_RemoveButton(Game game) {
			ac_RemoveButton.setBounds(625, 400, 184, 286);
			ac_RemoveButton.setVisible(true);
			ac_RemoveButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					//choose a block
					ac_UpOneButton.setVisible(false); ac_UpTwoButton.setVisible(false); 
					ac_UpThreeButton.setVisible(false); ac_DownButton.setVisible(false); ac_RemoveButton.setVisible(false);
					background=new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
					backButton.setVisible(false); //뒤로가기 버튼 없애기
					//액션카드 버튼들 안보이게끔
					//애는 블럭 선택할 필요 없음. 무조건 마지막 블럭 버리기
					game.current_player.action_card.remove.function(game);
					for(int o = 0; o<9; o++) { //9개의 색 블럭 확인
						for(int j = 1; j<=game.num_block;j++) { //남은 갯수 만큼의 등수 확인
							if(game.block[o].exist) {//이 블럭 살아 있니
								if(game.block[o].order == j) {
									System.out.println("make block["+o+"] ("+j+")");
								}}}}
					for(int i=0;i<9;i++) { //액션카드 기능 반영하여 다시 블럭 셋팅
						blocks_set(game, game.block[i].order, i);
					}
					actionCheck.setVisible(true);//다시 액션카드 open버튼 on
					missionCheck.setVisible(true);
				}
			});
			add(ac_RemoveButton);
		}

		
		//유진: 액션카드 선택 후 남아있는 블럭들 중에서 선택할 수 있도록 블럭을 버튼으로 만들기
		//남아있는 블럭인지 확인 후 버튼 생성
		void chooseBlock(Game game, String function) {
			ac_UpOneButton.setVisible(false); ac_UpTwoButton.setVisible(false); 
			ac_UpThreeButton.setVisible(false); ac_DownButton.setVisible(false); ac_RemoveButton.setVisible(false);
			
			Block temp_block;
			int height;
			for(int i = 0; i<9; i++) { //9개의 색 블럭 확인
				for(int j = 1; j<=game.num_block;j++) { //남은 갯수 만큼의 등수 확인
					if(game.block[i].exist) {//이 블럭 살아 있니
						if(game.block[i].order == j) {
							height = 30+74*(j-1); //해당 등수의 위치 설정
							temp_block = game.block[i];
							System.out.println("make block["+i+"] ("+j+")");
							makeBlock(game, temp_block, i, height, function);//i에 따라 색 구분할 수 있게 매개변수로
						}
					}
				}
			}
			System.out.println("////");
		}
		void makeBlock(Game game, Block block, int index, int height, String function) {
			if(index == 0) {
				redBlockButton.setBounds(594, height, 78, 78);
				redBlockButton.setVisible(true);
				redBlockButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						
						//choose_block에 Block 변수로 넘겨주기
						game.current_player.action_card.choose_block = block;
						if(function == "upone")
							game.current_player.action_card.upone.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "uptwo")
							game.current_player.action_card.uptwo.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "upthree")
							game.current_player.action_card.upthree.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "down")
							game.current_player.action_card.down.function(game);//함수 내에서 해당 카드 기능 실행하기
					
						for(int i=0;i<9;i++) { //액션카드 기능 반영하여 다시 블럭 셋팅
							blocks_set(game, game.block[i].order, i);
						}
						actionCheck.setVisible(true);//다시 액션카드 open버튼 on
						missionCheck.setVisible(true);

					}
				});
				add(redBlockButton);
			}else if(index == 1) {
				orangeBlockButton.setBounds(594, height, 78, 78);
				orangeBlockButton.setVisible(true);
				orangeBlockButton.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mousePressed(MouseEvent e) {
						
						//매개변수 넘겨주기
						game.current_player.action_card.choose_block = block;
						if(function == "upone")
							game.current_player.action_card.upone.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "uptwo")
							game.current_player.action_card.uptwo.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "upthree")
							game.current_player.action_card.upthree.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "down")
							game.current_player.action_card.down.function(game);//함수 내에서 해당 카드 기능 실행하기
					
						for(int i=0;i<9;i++) { //액션카드 기능 반영하여 다시 블럭 셋팅
							blocks_set(game, game.block[i].order, i);
						}
						actionCheck.setVisible(true);//다시 액션카드 open버튼 on
						missionCheck.setVisible(true);
					}
				});
				add(orangeBlockButton);

			}else if(index == 2) {
				yellowBlockButton.setBounds(594, height, 78, 78);
				yellowBlockButton.setVisible(true);
				yellowBlockButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						//매개변수 넘겨주기
						game.current_player.action_card.choose_block = block;
						if(function == "upone")
							game.current_player.action_card.upone.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "uptwo")
							game.current_player.action_card.uptwo.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "upthree")
							game.current_player.action_card.upthree.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "down")
							game.current_player.action_card.down.function(game);//함수 내에서 해당 카드 기능 실행하기
					
						for(int i=0;i<9;i++) { //액션카드 기능 반영하여 다시 블럭 셋팅
							blocks_set(game, game.block[i].order, i);
						}
						actionCheck.setVisible(true);//다시 액션카드 open버튼 on
						missionCheck.setVisible(true);
					}
				});
				add(yellowBlockButton);
				
			}else if(index == 3) {
				greenBlockButton.setBounds(594, height, 78, 78);
				greenBlockButton.setVisible(true);
				greenBlockButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						//매개변수 넘겨주기
						background=new ImageIcon(Main.class.getResource("../images/instructionBack.png")).getImage();
						game.current_player.action_card.choose_block = block;
						if(function == "upone")
							game.current_player.action_card.upone.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "uptwo")
							game.current_player.action_card.uptwo.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "upthree")
							game.current_player.action_card.upthree.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "down")
							game.current_player.action_card.down.function(game);//함수 내에서 해당 카드 기능 실행하기
					
						for(int i=0;i<9;i++) { //액션카드 기능 반영하여 다시 블럭 셋팅
							blocks_set(game, game.block[i].order, i);
						}
						actionCheck.setVisible(true);//다시 액션카드 open버튼 on
						missionCheck.setVisible(true);
						
					}
				});
				add(greenBlockButton);
				
			}else if(index == 4) {
				skyBlockButton.setBounds(594, height, 78, 78);
				skyBlockButton.setVisible(true);
				skyBlockButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						//매개변수 넘겨주기
						game.current_player.action_card.choose_block = block;
						if(function == "upone")
							game.current_player.action_card.upone.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "uptwo")
							game.current_player.action_card.uptwo.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "upthree")
							game.current_player.action_card.upthree.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "down")
							game.current_player.action_card.down.function(game);//함수 내에서 해당 카드 기능 실행하기
					
						for(int i=0;i<9;i++) { //액션카드 기능 반영하여 다시 블럭 셋팅
							blocks_set(game, game.block[i].order, i);
						}
						actionCheck.setVisible(true);//다시 액션카드 open버튼 on
						missionCheck.setVisible(true);
					}
				});
				add(skyBlockButton);
				
			}else if(index == 5) {
				blueBlockButton.setBounds(594, height, 78, 78);
				blueBlockButton.setVisible(true);
				blueBlockButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						//매개변수 넘겨주기
						game.current_player.action_card.choose_block = block;
						if(function == "upone")
							game.current_player.action_card.upone.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "uptwo")
							game.current_player.action_card.uptwo.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "upthree")
							game.current_player.action_card.upthree.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "down")
							game.current_player.action_card.down.function(game);//함수 내에서 해당 카드 기능 실행하기
					
						for(int i=0;i<9;i++) { //액션카드 기능 반영하여 다시 블럭 셋팅
							blocks_set(game, game.block[i].order, i);
						}
						actionCheck.setVisible(true);//다시 액션카드 open버튼 on
						missionCheck.setVisible(true);
					}
				});
				add(blueBlockButton);
				
			}else if(index == 6) {
				purpleBlockButton.setBounds(594, height, 78, 78);
				purpleBlockButton.setVisible(true);
				purpleBlockButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						//매개변수 넘겨주기
						game.current_player.action_card.choose_block = block;
						if(function == "upone")
							game.current_player.action_card.upone.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "uptwo")
							game.current_player.action_card.uptwo.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "upthree")
							game.current_player.action_card.upthree.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "down")
							game.current_player.action_card.down.function(game);//함수 내에서 해당 카드 기능 실행하기
					
						for(int i=0;i<9;i++) { //액션카드 기능 반영하여 다시 블럭 셋팅
							blocks_set(game, game.block[i].order, i);
						}
						actionCheck.setVisible(true);//다시 액션카드 open버튼 on
						missionCheck.setVisible(true);
					}
				});
				add(purpleBlockButton);
				
			}else if(index == 7) {
				pinkBlockButton.setBounds(594, height, 78, 78);
				pinkBlockButton.setVisible(true);
				pinkBlockButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						//매개변수 넘겨주기
						game.current_player.action_card.choose_block = block;
						if(function == "upone")
							game.current_player.action_card.upone.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "uptwo")
							game.current_player.action_card.uptwo.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "upthree")
							game.current_player.action_card.upthree.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "down")
							game.current_player.action_card.down.function(game);//함수 내에서 해당 카드 기능 실행하기
					
						for(int i=0;i<9;i++) { //액션카드 기능 반영하여 다시 블럭 셋팅
							blocks_set(game, game.block[i].order, i);
						}
						actionCheck.setVisible(true);//다시 액션카드 open버튼 on
						missionCheck.setVisible(true);
					}
				});
				add(pinkBlockButton);
				
			}else if(index == 8) {
				grayBlockButton.setBounds(594, height, 78, 78);
				grayBlockButton.setVisible(true);
				grayBlockButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						//매개변수 넘겨주기
						game.current_player.action_card.choose_block = block;
						if(function == "upone")
							game.current_player.action_card.upone.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "uptwo")
							game.current_player.action_card.uptwo.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "upthree")
							game.current_player.action_card.upthree.function(game);//함수 내에서 해당 카드 기능 실행하기
						else if(function == "down")
							game.current_player.action_card.down.function(game);//함수 내에서 해당 카드 기능 실행하기
					
						for(int i=0;i<9;i++) { //액션카드 기능 반영하여 다시 블럭 셋팅
							blocks_set(game, game.block[i].order, i);
						}
						actionCheck.setVisible(true);//다시 액션카드 open버튼 on
						missionCheck.setVisible(true);
					}
				});
				add(grayBlockButton);
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}