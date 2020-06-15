package tikitaka;

import java.io.*;
import java.util.*;
import java.util.List;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class server extends JFrame implements ActionListener {
	JPanel basePane, panel_main, panel_textarea, panel_btn;
	JTextArea textarea;
	JScrollPane scrollpane;

	JLabel serverstatus_label;
	JButton serverstart_btn, serverclose_btn;
	public server() {}
	
	boolean gamestart, setEnd = false;
	LinkedHashMap<String, DataOutputStream> clientOutput = new LinkedHashMap<String, DataOutputStream>();
	LinkedHashMap<String, Integer> clientScore = new LinkedHashMap<String, Integer>();

	ServerSocket ss;
	Socket s;
	int port = 8800;
	int readyPlayer;
	int score, currentTime = 0;
	public static int droper = 0;
	public static final int MAX_CLIENT = 2;
	public static int gameNum = 0;
	public static int setNum = 0;
	//public static boolean set0 = false;
	String sentence;

	public void init() {
		setTitle("SERVER");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		setLocationRelativeTo(null);

		basePane = new JPanel();
		basePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(basePane);
		basePane.setLayout(new GridLayout(0, 1, 10, 0));

		panel_main = new JPanel();
		basePane.add(panel_main);
		panel_main.setLayout(new BoxLayout(panel_main, BoxLayout.Y_AXIS));

		panel_btn = new JPanel();
		panel_btn.setPreferredSize(new Dimension(10, 43));
		panel_btn.setAutoscrolls(true);
		panel_main.add(panel_btn);
		panel_btn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		serverstart_btn = new JButton(" 서버 시작 ");
		serverstart_btn.setHorizontalTextPosition(SwingConstants.CENTER);
		serverstart_btn.setPreferredSize(new Dimension(120, 40));
		serverstart_btn.setFocusPainted(false);
		serverstart_btn.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
		serverstart_btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		serverstart_btn.setForeground(Color.WHITE);
		serverstart_btn.setBackground(Color.DARK_GRAY);
		serverstart_btn.setBorder(null);
		panel_btn.add(serverstart_btn);
		serverstart_btn.addActionListener(this);

		serverclose_btn = new JButton(" 서버 종료 ");
		serverclose_btn.setHorizontalTextPosition(SwingConstants.CENTER);
		serverclose_btn.setPreferredSize(new Dimension(120, 40));
		serverclose_btn.setFocusPainted(false);
		serverclose_btn.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
		serverclose_btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		serverclose_btn.setForeground(Color.WHITE);
		serverclose_btn.setBackground(Color.DARK_GRAY);
		serverclose_btn.setBorder(null);
		panel_btn.add(serverclose_btn);
		serverclose_btn.addActionListener(this);
		serverclose_btn.setEnabled(false);

		serverstatus_label = new JLabel("[ Server Waiting .. ]");
		serverstatus_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		serverstatus_label.setPreferredSize(new Dimension(96, 50));
		panel_main.add(serverstatus_label);
		serverstatus_label.setHorizontalTextPosition(SwingConstants.CENTER);
		serverstatus_label.setHorizontalAlignment(SwingConstants.CENTER);
		serverstatus_label.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));

		panel_textarea = new JPanel();
		panel_main.add(panel_textarea);
		panel_textarea.setLayout(new BorderLayout(0, 0));
	    scrollpane = new JScrollPane();
		scrollpane.setBorder(new LineBorder(Color.DARK_GRAY));
		panel_textarea.add(scrollpane);

		textarea = new JTextArea();
		textarea.setLineWrap(true);
		textarea.setEditable(false);
		scrollpane.setViewportView(textarea);

		basePane.setBackground(new Color(247, 243, 222));
		panel_main.setBackground(new Color(247, 243, 222));
		panel_textarea.setBackground(new Color(247, 243, 222));
		panel_btn.setBackground(new Color(247, 243, 222));
	}

	public void actionPerformed(ActionEvent e) { // '서버 시작 & 종료' 버튼
		if (e.getSource() == serverstart_btn) {
			new Thread() {
				public void run() {
					try {

						Collections.synchronizedMap(clientOutput);
						ss = new ServerSocket(port);
						serverstatus_label.setText("[ Server Started ]");
						textarea.append("[ 서버가 시작되었습니다 ]" + "\n");
						serverstart_btn.setEnabled(false);
						serverclose_btn.setEnabled(true);
						clientAccept();
					} catch (IOException ie) {
					}
				}
			}.start();

		} else if (e.getSource() == serverclose_btn) {
			int select = JOptionPane.showConfirmDialog(null, "서버를 정말 종료하시겠습니까?", "JAVA CatchMind Server",
					JOptionPane.OK_CANCEL_OPTION);
			try {
				if (select == JOptionPane.YES_OPTION) {
					ss.close();
					serverstatus_label.setText("[ Server Closed ]");
					textarea.append("[ 서버가 종료되었습니다 ]" + "\n");
					serverstart_btn.setEnabled(true);
					serverclose_btn.setEnabled(false);
				}
			} catch (IOException io) {
			}
		}
	}

	
	public void clientAccept() {
		try {
			while (true) {
				s = ss.accept();
				
				if ((clientOutput.size()) + 1 > MAX_CLIENT || gamestart == true) {
					s.close();
				} else {					
					Tiki_Thread tt = new Tiki_Thread(s);
					tt.start();
					
					Thread cm = new clientManager(s);
					cm.start();
				}
			}

		} catch (IOException io) {
	  }
		
	}
	class Tiki_Thread extends Thread
	{
		private String ID=null; 
		private Socket socket;
		
		private boolean ready=false;
		
		private BufferedReader reader;     // 입력 스트림
		private PrintWriter writer;   
		Tiki_Thread(Socket socket){     // 생성자
		      this.socket=socket;}
		Socket getSocket(){   return socket;}          // 소켓을 반환한다.
		String getUserName(){   return ID;}      // 사용자 이름을 반환한다.
		boolean isReady(){  return ready;}            // 준비 상태를 반환한다.
		
		public void run()
		{
			 try{
				 reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			     writer=new PrintWriter(socket.getOutputStream(), true);
			     String msg;                     // 클라이언트의 메시지
			     while((msg=reader.readLine())!=null){
			    	 if(msg.startsWith("[NAME]")){
			             ID=msg.substring(6);          // userName을 정한다.
			           }
			     }
			 }catch(Exception e){}
			 finally{
			        try{
			          //bMan.remove(this);
			          if(reader!=null) reader.close();
			          if(writer!=null) writer.close();
			          if(socket!=null) socket.close();
			          reader=null; writer=null; socket=null;
			          System.out.println(ID+"님이 접속을 끊었습니다.");
			          System.out.println("접속자 수: "+clientOutput.size());
			        }catch(Exception e){}
		}
	}
}
	
	public void serverMsg(String str) {
		Iterator<String> it = clientOutput.keySet().iterator();
		while (it.hasNext()) {
			try {
				DataOutputStream dos = clientOutput.get(it.next());
				dos.writeUTF(str);
				dos.flush();
			} catch (IOException io) {
			}
		}
	}

	public class clientManager extends Thread {
		Socket soc;
		DataInputStream dis;
		DataOutputStream dos;
		
		public clientManager(Socket soc) {			
			this.soc = soc;
			try {
				dis = new DataInputStream(soc.getInputStream());
				dos = new DataOutputStream(soc.getOutputStream());
			} catch (IOException io) {
			}
		}

		public void run() {
			String ID = "";
			try {
				ID = dis.readUTF();
				if (!clientOutput.containsKey(ID)) {
					clientOutput.put(ID, dos);
					clientScore.put(ID, score);
				} else if (clientOutput.containsKey(ID)) {
					JOptionPane.showMessageDialog(null, "중복된 닉네임이 있습니다. ", "ERROR", JOptionPane.ERROR_MESSAGE);
					s.close();
				}
				Iterator<String> it1 = clientOutput.keySet().iterator();

				clientSet();

				while (dis != null) {
				}

			} catch (IOException io) { 
				clientOutput.remove(ID);
				clientScore.remove(ID);
				Close();
				Iterator<String> it1 = clientOutput.keySet().iterator();

				clientSet();
			}

		}

		public void Close() {
			try {
				if (dos != null)
					dos.close();
				if (dis != null)
					dis.close();
				if (s != null)
					s.close();
			} catch (IOException io) {
			}
		}
		
		public void clientSet() {
			String[] ID = new String[clientOutput.size()];
			int[] scores = new int[clientScore.size()];
			int idx = 0;
			for (Map.Entry<String, Integer> mapEntry : clientScore.entrySet()) {
				ID[idx] = mapEntry.getKey();
				scores[idx++] = mapEntry.getValue();
			}
			for (int i = 0; i < clientScore.size(); i++)
				serverMsg("_CList" + ID[i] + " " + scores[i] + "#" + i);
		}
		// 여기까지 그대로

		public void gameStart() {
			//Game game = new Game();
			//game.game_init();
			
			Iterator<String> it = clientOutput.keySet().iterator();
			ArrayList<String> droperList = new ArrayList<String>();
			
			while (it.hasNext())
				droperList.add(it.next());
			if (droper > clientOutput.size() - 1) {
				droper = 0;
			}
			serverMsg("_Droper" + droperList.get(droper++));
			
			gamestart = true;
			serverMsg("_Round" + (gameNum + 1));
			serverMsg("_Start"); // 명령어 : 게임 시작
		}
		
		public void command(String str)
		{
			String cmd = str.substring(0, 6);
			if (cmd.equals("_Ready")) 
			{
				
			}
			if(setEnd) {
				readyPlayer = 0;
				gameNum = 0;
				setEnd = false;
				Iterator<String> it1 = clientScore.keySet().iterator();
				while (it1.hasNext())
					clientScore.put(it1.next(), 0);

				clientSet();
			}
			readyPlayer++;
			if (readyPlayer == 2) {
				serverMsg("====" + "모든 참여자들이 준비되었습니다." + "====");
				if (gameNum == 0)
					gameStart();
			}
			else {
				setEnd = true;
				setNum++;
				
				serverMsg("======" + "세트가 종료되었습니다 !" + "======\n");
				List<Map.Entry<String, Integer>> list = new LinkedList<>(clientScore.entrySet());
				Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
					public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
						int comparision = (o1.getValue() - o2.getValue()) * -1;
						return comparision == 0 ? o1.getKey().compareTo(o2.getKey()) : comparision;
					}
				});
				
				Map<String, Integer> sortedMap = new LinkedHashMap<>();
				for (Iterator<Map.Entry<String, Integer>> iter = list.iterator(); iter.hasNext();) {
					Map.Entry<String, Integer> entry = iter.next();
					sortedMap.put(entry.getKey(), entry.getValue());
				}
				String[] nicks = new String[2];
				int[] scores = new int[2];
				int idx = 0;
				for (Map.Entry<String, Integer> mapEntry : sortedMap.entrySet()) {
					nicks[idx] = mapEntry.getKey();
					scores[idx++] = mapEntry.getValue();
				}
				String result = "";
				for (idx = 0; idx < 2; idx++) {
					if (nicks[idx] == null)
						break;
					result += nicks[idx] + " (" + scores[idx] + ")\n";
				}
				serverMsg("_StEnd" + result);
				gamestart = false;
			}
				
		}
	}	
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						server sv = new server();
						sv.init();
						sv.setVisible(true);
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}