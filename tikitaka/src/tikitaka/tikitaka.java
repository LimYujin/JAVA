package tikitaka;

import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/////////////�ּ� ����/////////////
/* �ش� �ڵ忡 ���� ������ //�� �ּ�ó��
 * �ش� �κп� �����ؾ� �� �ڵ� ������ ////�� �ּ�ó��
 * GUI ���� ��û�� ///GUI///�� �ּ�ó��
 * ���� ���� ��û�� ///SOCKET///�� �ּ�ó��
 */

public class tikitaka {

}

class Game{
	//���� �ִ� ����
	
	int[] block_order = new int[9]; //ó�� ������ ���� ���� �� ������ ���� �迭
	Block[] block;
	//Block choose_block;
	int num_block; //�����ִ� �� ����
	Player player1;
	Player player2;
	Player current_player;
	
	void game_init() {
		
		//block_order�� �������� 0~8 ���� �־��ֱ�
		LinkedList<Integer> order_list = new LinkedList<Integer>();
		
		for(int i = 1; i< 10;i++)	 	order_list.addLast(i);
		Random random = new Random();
		int index;
		for(int i = order_list.size(); i> 0; i--) {
			index = random.nextInt(i);
			this.block_order[9-i] = order_list.get(index);
			System.out.println("block["+(9-i)+"] : " + this.block_order[9-i]); //Ȯ�ο�
			order_list.remove(index);
		}
		
		//block 9���� ���� �־��ֱ� �� ���� ����, ���� ������ ������ ����
		this.block = new Block[9];
		this.block[0] = new Block("red", block_order[0]);
		this.block[1] = new Block("orange", block_order[1]);
		this.block[2] = new Block("yellow", block_order[2]);
		this.block[7] = new Block("green", block_order[3]);
		this.block[3] = new Block("sky", block_order[4]);
		this.block[4] = new Block("blue", block_order[5]);
		this.block[5] = new Block("purple", block_order[6]);
		this.block[6] = new Block("pink", block_order[7]);
		this.block[8] = new Block("gray", block_order[8]);
		
		//player 2�� ����
		this.player1 = new Player("player1");
		this.player2 = new Player("player2");
		this.current_player = player1;
		
		//�����ǿ� �����ִ� �� ����
		this.num_block = 9;
		
		///GUI///���� �ʱ� ȭ�� �־��ֱ�
		
		System.out.println("game start!");
	}

	void gameOver() {
		//2���� player�� ī�带 ��� ���� ���� ����
		if(player1.action_card.num_card == 0 & player2.action_card.num_card == 0)
		{
			//player ���� ����ϱ�
			player1.calculation(player1.score);
			player2.calculation(player2.score);
			//���� ����
			System.out.println("game over!");
			///GUI///�������� �־��ֱ�
			///GUI///�� player�� ���� ���
			System.out.println("player1's score : " + player1.score);
			System.out.println("player2's score : " + player2.score);
			
		}
	}
}

class Player extends Game{
	//������
	
	String id;
	ActionCard action_card; //player�� �׼�ī��
	int score; //����
	MissonCard misson = new MissonCard(); //������ �ִ� �̼� ī�� ; �����ڷ� �������� �ϳ� �����Բ�
	///GUI///�⺻�� �׳� ī�� ���� �׸��̾��ٰ� ��ư ������ �̼�ī�� ���̰Բ� �ϸ� ���� �� ������, �̰� ��� �ؾ��ұ�
	
	void calculation(int score) {
		//���� ��� �� player�� score ������Ʈ
		//�������� �̼�ī���� 1,2,3�� ������� ���ӿ� �����ϴ� 1,2,3�� ������ ��
		Block temp = new Block();
		for( int i=0 ; i<9 ; i++)
		{
			//�����ǿ��� 1���� ���� ���� player�� �̼�ī���� 1�� ���� ������ 7�� �ο�
			if(block[i].order == 1) {
				temp = block[i];
				if(	this.misson.first_color == temp.color) { 
					this.score += 7; 
				}
			}
			//�����ǿ��� 2���� ���� ���� player�� �̼�ī���� 1�� �Ǵ� 2�� ���� ������ 5�� �ο�
			else if(block_order[i] == 2)
			{
				temp = block[i];
				if( this.misson.second_color == temp.color) {
					this.score += 5;
				}
				else if(this.misson.first_color == temp.color) {
					this.score += 5;
				}
			}
			//�����ǿ��� 2���� ���� ���� player�� �̼�ī���� 1��,2��,3�� ���� ������ 5�� �ο�
			else if(block_order[i] == 3)
			{
				temp = block[i];
				if( this.misson.third_color == temp.color) {
					this.score += 3;
				}
				else if(this.misson.first_color == temp.color) {
					this.score += 3;
				}
				else if(this.misson.second_color == temp.color) {
					this.score += 3;
				}
			}
		}
	}
	//������ : ó�� ��ü ���� �� �⺻ ����
	Player() { this.action_card = new ActionCard(); action_card.ActionCard_init(); this.score = 0; }
	Player(String id) { this.action_card = new ActionCard(); action_card.ActionCard_init(); this.score = 0; this.id = id;}
	
}

class Block {
	//����
	
	String color = new String(); //�� ����
	int order; //�� ����(1~9)
	boolean exist; //�����ǿ� �����ִ��� Ȯ��
	
	Block() {}
	//�� ��° ������: ��ü ó���� ������ �� �������� ���� ������ �÷��� �־ ���� �����ϱ�
	Block(String color, int order) { 
		this.color = color; this.order = order; this.exist = true;
	}
	///GUI/// ���� �Ű������� ������ �� ��Ͽ� �ش��ϴ� �̹����� ����ó�� ������ �ְ� �� �� �ֳ�?
}

class MissonCard {
	//�̼�ī��
	
	//�ڽ��� �̼� ����
	String first_color;
	String second_color;
	String third_color;
	
	//�����ڷ� �������� �̾Ƽ� �־��ֱ�
	MissonCard() {
		LinkedList<String> color_list = new LinkedList<String>();
		
		color_list.addLast("red");
		color_list.addLast("orange");
		color_list.addLast("yellow");
		color_list.addLast("green");
		color_list.addLast("sky");
		color_list.addLast("blue");
		color_list.addLast("purple");
		color_list.addLast("pink");
		color_list.addLast("gray");
		
		Random random = new Random();
		
		int index;
		int i = color_list.size();
		index = random.nextInt(i);
		this.first_color = color_list.get(index);
		System.out.println("first_color :  " + this.first_color); //Ȯ�ο�
		color_list.remove(index);
		i--;
		
		index = random.nextInt(i);
		this.second_color = color_list.get(index);
		System.out.println("second_color :  " + this.second_color); //Ȯ�ο�
		color_list.remove(index);
		i--;
		
		index = random.nextInt(i);
		this.third_color = color_list.get(index);
		System.out.println("third_color :  " + this.third_color); //Ȯ�ο�
		color_list.remove(index);
	}
	
	///GUI/// �굵 �� ���� ���� ������ �׿� �ش��ϴ� �̹����� ����ó�� ������ �ְ� �� �� �ֳ�?
}

class ActionCard {
	//7���� �׼�ī��
	int num_card; //���� �׼� ī�� ��
	///GUI/// ȭ�� �� ���� �ִ� ī�� ���̰Բ�
	Block choose_block; //������ �� ��ü
	//void fuction() {}
	UpOne upone;
	UpTwo uptwo;
	UpThree upthree;
	RemoveCard remove;
	DownCard down;
	
	void ActionCard_init() {
		upone = new UpOne();
		uptwo = new UpTwo();
		upthree = new UpThree();
		remove = new RemoveCard();
		down = new DownCard();
	}
	
	ActionCard() {
		this.num_card = 7;
	}
	
	void next_turn(Game game) {
		//���� �÷��̾� ������ �Ѿ��
		///GUI///�ε� � �÷��̾� �������� ǥ�����ֱ�
		if(game.current_player == game.player1)		game.current_player = game.player2;
		else game.current_player = game.player2;
	} 
}

class RemoveCard {
	//������ ��� ������
	int num_thiscard;
	
	RemoveCard() { this.num_thiscard = 2; } //ó���� �־��� ī�� 2��
	
	void function(Game game) {
		//������ ��� ������, �̶� choose�� game�� ������ �� �־������
		for(int i=0;i<game.num_block;i++)
		{
			if(game.block[i].order == game.num_block) //������������� Ȯ��
			{
				game.block[i].exist = false; //�ش� ��� ������
				System.out.println("block[" + i +"] is last block");
				///GUI///�ٲ� ���� �����ִ� �׼�ī�� �ݿ�
			}
		}
		this.num_thiscard --; //�ش� �׼� ī�� ���� -1
		game.current_player.action_card.num_card--; //��ü �׼� ī�� ���� -1
		game.current_player.action_card.next_turn(game); //���� �÷��̾�� �Ѿ��
		game.num_block--;
		System.out.println("removeCard" + game.num_block);
	}
}

class DownCard {
	//������ ��� ���������� ������
	int num_thiscard;
	
	DownCard() {this.num_thiscard = 1; } //ó���� �־��� ī�� 1��
	
	///GUI/// �̶� ȭ�鿡�� Ŭ���� ���� Block������ choose_block �Ű������� ���Բ� �� �� �ֳ�?
	void function(Game game) {
		//������ ��� ������ ������ �ٲٱ�, ������ ��� �Ű�������
		int choose_block_order = game.current_player.action_card.choose_block.order;
		Block[] temp = new Block[9];
		for(int i=0 ; i<9;i++)
			temp[i] = null;
		for(int i=0;i<9;i++) {
			if(game.block[i].exist) {
				//������ ��� �Ʒ� �ִ� �͵� �� ĭ�� ����
				if(game.block[i].order > choose_block_order) {
					temp[i] = game.block[i];
				}
			}
		}
		for(int i=0;i<9;i++) {
			if(temp[i] != null) temp[i].order++; //������ ��� �Ʒ� �ִ� �͵� �� ĭ�� ����
		}
		game.current_player.action_card.choose_block.order = game.num_block;
		this.num_thiscard --; //�ش� �׼� ī�� ���� -1
		game.current_player.action_card.num_card--; //��ü �׼� ī�� ���� -1
		///GUI///�ٲ� ���� �����ִ� �׼�ī�� �ݿ�
		game.current_player.action_card.next_turn(game); //���� ������ �Ѿ��
	}
	
	boolean possible(Game game) { 
		if(game.current_player.action_card.choose_block.order < game.num_block) //���������� ���� �ִ� ������ Ȯ��
			return true; 
		else return false;  //��� ������ ��Ҵ� �׻� true
	}
}

class UpOne {
	//������ ��� ���� 1ĭ �ø���
	int num_thiscard;
	
	UpOne() { System.out.println("upone"); this.num_thiscard = 2; } //ó���� �־��� ī�� 2��

	///GUI/// �̶� ȭ�鿡�� Ŭ���� ���� Block������ choose_block �Ű������� ���Բ� �� �� �ֳ�?
	void function(Game game) {
		System.out.println("Who's turn : " + game.current_player.id);
		//������ ��� ���� 1ĭ �ø���
		int choose_block_order = game.current_player.action_card.choose_block.order;
		Block temp1 = null; //������ ���� ���
		Block temp2 = null; //���� �ö� ���
		for(int i=0;i<game.num_block;i++) {
			//������ ��� ���� ��� �� ĭ �Ʒ���
			if(game.block[i].order == choose_block_order-1) {
				temp1 = game.block[i];
			}
			//������ ��� ��ĭ ����
			if(game.block[i].order == choose_block_order) {
				temp2 = game.block[i];
			}
		}
		temp1.order++;
		System.out.println("UpOne: block[" + "temp1" + "] : " + temp1.order);
		temp2.order--;
		System.out.println("UpOne: block[" + "temp2" + "] : " +temp2.order);
		this.num_thiscard --; //�ش� �׼� ī�� ���� -1
		game.current_player.action_card.num_card--; //��ü �׼� ī�� ���� -1
		///GUI///�ٲ� ���� �����ִ� �׼�ī�� �ݿ�
		game.current_player.action_card.next_turn(game); //���� ������ �Ѿ��
		System.out.println("UpOne////");
	}
	
	boolean possible(Game game) {
		//������ �������� Ȯ�� �� ����
		if(game.current_player.action_card.choose_block.order > 1) {
		//���� ����� ������ 1���� ū��
		return true;
		}
		else return false;
	}
}
class UpTwo {
	//������ ��� ���� 2ĭ �ø���
	int num_thiscard;
	
	UpTwo() { this.num_thiscard = 1; } //ó���� �־��� ī�� 1��
	
	///GUI/// �̶� ȭ�鿡�� Ŭ���� ���� Block������ choose_block �Ű������� ���Բ� �� �� �ֳ�?
	void function(Game game) {
		//������ ��� ���� 2ĭ �ø���
		int choose_block_order = game.current_player.action_card.choose_block.order;
		Block temp1 = null; //1ĭ ������ ���
		Block temp2 = null; //1ĭ ������ ���
		Block temp3 = null; //2ĭ �ö� ���
		for(int i=0;i<game.num_block;i++) {
		//������ ��� ���� �� ��� �� ĭ�� �Ʒ���
			if(game.block[i].order == choose_block_order-1)  temp1 = game.block[i];
			else if(game.block[i].order == choose_block_order-2) 	temp2 = game.block[i];
			else if(game.block[i].order == choose_block_order) temp3 = game.block[i];
		}
		temp1.order++;
		temp2.order++;
		temp3.order = temp3.order-2;
		System.out.println("UpTwo: block[" + "temp1" + "] : " +temp1.order);
		System.out.println("UpTwo: block[" + "temp2" + "] : " +temp2.order);
		System.out.println("UpTwo: block[" + "temp3" + "] : " +temp3.order);
		this.num_thiscard --; //�ش� �׼� ī�� ���� -1
		game.current_player.action_card.num_card--; //��ü �׼� ī�� ���� -1
		///GUI///�ٲ� ���� �����ִ� �׼�ī�� �ݿ�
		game.current_player.action_card.next_turn(game);  //���� ������ �Ѿ��
	}
		
	boolean possible(Game game) {
		//������ �������� Ȯ�� �� ����
		if(game.current_player.action_card.choose_block.order > 2) {
		//���� ����� ������ 2���� ū��
		return true;
		}
		else return false;
	}
}
class UpThree {
	//������ ��� ���� 3ĭ �ø���
	int num_thiscard;
	
	UpThree() { this.num_thiscard = 1; } //ó���� �־��� ī�� 1��
	
	///GUI/// �̶� ȭ�鿡�� Ŭ���� ���� Block������ choose_block �Ű������� ���Բ� �� �� �ֳ�?
	void function(Game game) {
		//������ ��� ���� 3ĭ �ø���
		int choose_block_order = game.current_player.action_card.choose_block.order;
		Block temp1 = null; //1ĭ ������ ���
		Block temp2 = null; //1ĭ ������ ���
		Block temp3 = null; //1ĭ �ö� ���
		Block temp4 = null; //3ĭ �ö� ���
		for(int i=0;i<game.num_block;i++) {
		//������ ��� ���� �� ��� �� ĭ�� �Ʒ���
			if(game.block[i].order == choose_block_order-1)  temp1 = game.block[i];
			else if(game.block[i].order == choose_block_order-2)	temp2 = game.block[i];
			else if(game.block[i].order == choose_block_order-3) temp3 = game.block[i];
			else if(game.block[i].order == choose_block_order) temp4 = game.block[i];
		}
		temp1.order++;
		temp2.order++;
		temp3.order++;
		temp4.order = temp2.order-3;
		System.out.println("UpThree: block[" + "temp1" + "] : " +temp1.order);
		System.out.println("UpThree: block[" + "temp2" + "] : " +temp2.order);
		System.out.println("UpThree: block[" + "temp3" + "] : " +temp3.order);
		System.out.println("UpThree: block[" + "temp4" + "] : " +temp4.order);
		this.num_thiscard --; //�ش� �׼� ī�� ���� -1
		game.current_player.action_card.num_card--; //��ü �׼� ī�� ���� -1
		///GUI///�ٲ� ���� �����ִ� �׼�ī�� �ݿ�
		game.current_player.action_card.next_turn(game); //���� ������ �Ѿ��
	}
	
	boolean possible(Game game) {
		//������ �������� Ȯ�� �� ����
		if(game.current_player.action_card.choose_block.order > 3) {
		//���� ����� ������ 3���� ū��
		return true;
		}
		else return false;
	}
}

