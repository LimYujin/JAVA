package tikitaka;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
public class login extends JFrame implements ActionListener {
	JPanel panel_id, panel_ip, panel_button;
	JLabel label_id, label_ip, pallete, eraser;
	JButton btn_login, btn_exit;
	JTextField txt_id, txt_ip;
	
	public static String ip, id;
	
	void init() {
		Container c = getContentPane();
		panel_id = new JPanel();
		panel_ip = new JPanel();
		panel_button = new JPanel();
		
		c.add(panel_button);
		c.add(panel_ip);
		c.add(panel_id);
		
		pack();
		label_id = new JLabel("ID(����� �̸�)");
		label_ip = new JLabel("IP Address");
		label_id.setFont(new Font("�����ٸ����", Font.BOLD, 13));
		label_ip.setFont(new Font("�����ٸ����", Font.BOLD, 13));
		txt_id = new JTextField(15);
		txt_ip = new JTextField(15);
		
		btn_login = new JButton("LOG-IN");
		btn_exit = new JButton("EXIT");
		
		panel_id.setBackground(new Color(247,243,222));
		panel_ip.setBackground(new Color(247,243,222));
		panel_button.setBackground(new Color(247,243,222));
		
		pallete = new JLabel(new ImageIcon("image\\color-palette.png"));
		eraser = new JLabel(new ImageIcon("image\\eraser2.png"));
		
		pallete.setBounds(15, 60, 50, 47);
		pallete.setOpaque(true);
		pallete.setBackground(new Color(247,243,222));
		c.add(pallete);
	
	
		eraser.setBounds(225,60,50,49);
		eraser.setOpaque(true);
		eraser.setBackground(new Color(247,243,222));
		c.add(eraser);
		
		add(panel_id, BorderLayout.NORTH);
		add(panel_ip, BorderLayout.CENTER);
		add(panel_button, BorderLayout.SOUTH);
		
		panel_id.setLayout(new FlowLayout());
		panel_ip.setLayout(new FlowLayout());
		panel_button.setLayout(new FlowLayout());
		
		
		txt_id.setForeground(Color.BLACK);
		txt_id.setBackground(new Color(247,243,222));

		txt_ip.setForeground(Color.BLACK);
		txt_ip.setBackground(new Color(247,243,222));
	
		
		panel_id.add(label_id);
		panel_id.add(txt_id);
		
		label_id.setForeground(Color.BLACK);
		label_ip.setForeground(Color.BLACK);
		panel_ip.add(label_ip);
		panel_ip.add(txt_ip);
		
		//bottom ��ư ����
		btn_login.setFocusPainted(false); //��Ŀ�� ǥ��
		btn_exit.setFocusPainted(false);
		btn_login.setBackground(Color.BLACK); 
		btn_exit.setBackground(Color.BLACK);
		btn_login.setForeground(Color.WHITE);
		btn_exit.setForeground(Color.WHITE);
		
		btn_login.setFont(new Font("�����ٸ����", Font.BOLD, 13));
		btn_exit.setFont(new Font("�����ٸ����", Font.BOLD, 13));
		
		//btn_login : ���ӹ�ư, btn_exit : �����ư
		panel_button.add(btn_login);
		panel_button.add(btn_exit);
		btn_login.addActionListener(this);
		btn_exit.addActionListener(this);
		

		setVisible(true);
		setTitle("LOGIN");
		setSize(300, 150);
		setResizable(false);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e){ 
		
		
		if(e.getSource() == btn_login){
			if(txt_id.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Input your ID", "ERROR!", JOptionPane.WARNING_MESSAGE);
			}else if(txt_ip.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Input your IP Address", "ERROR!", JOptionPane.WARNING_MESSAGE);
			}else if(txt_id.getText().trim().length() >=8){
				JOptionPane.showMessageDialog(null, "�г����� 7���ڱ����� �Է��Ҽ� �ֽ��ϴ�", "ERROR!", JOptionPane.WARNING_MESSAGE);
				txt_id.setText("");
			}else{
				id = txt_id.getText().trim(); //trim���� ���黩�� ���� - isempty�� �̿��ؼ��� ���� �� ����
				String temp = txt_ip.getText(); //���Խ� -- IP�� �޾ƿ�.
				if(temp.matches("(^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$)")){
					ip = temp;
					JOptionPane.showMessageDialog(null, "             �α��� ����!", "JAVA CatchMind LOGIN", JOptionPane.INFORMATION_MESSAGE);
					btn_login.setEnabled(false); //��ư ��Ȱ��ȭ
					txt_id.setEnabled(false);
					txt_ip.setEnabled(false);
					setVisible(false);
					
					//set game
					//new GUI();
					
					client client = new client();
					
				}else{
					JOptionPane.showMessageDialog(null, "Correct your IP Address ", "ERROR!", JOptionPane.WARNING_MESSAGE);
				}
			}
		}else if(e.getSource() == btn_exit){
			System.exit(0);
		}
	}
	
}*/