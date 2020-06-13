package tikitaka;

import java.util.LinkedList;
import java.util.Random;

/////////////�ּ� ����/////////////
/* �ش� �ڵ忡 ���� ������ //�� �ּ�ó��
 * �ش� �κп� �����ؾ� �� �ڵ� ������ ////�� �ּ�ó��
 * GUI ���� ��û�� ///GUI///�� �ּ�ó��
 * ���� ���� ��û�� ///SOCKET///�� �ּ�ó��
 */

public class tikitaka {

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();
		game.game_init();
	}*/

}

class Game{
	//���� �ִ� ����
	
	int[] block_order = new int[9]; //ó�� ������ ���� ���� �� ������ ���� �迭
	Block[] block;
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
		this.block[1] = new Block("orange", block_order[0]);
		this.block[2] = new Block("yellow", block_order[0]);
		this.block[7] = new Block("green", block_order[0]);
		this.block[3] = new Block("sky", block_order[0]);
		this.block[4] = new Block("blue", block_order[0]);
		this.block[5] = new Block("purple", block_order[0]);
		this.block[6] = new Block("pink", block_order[0]);
		this.block[8] = new Block("gray", block_order[0]);
		
		//player 2�� ����
		this.player1 = new Player();
		this.player2 = new Player();
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
	Player() { this.action_card = new ActionCard(); this.score = 0; }
	
}

class Block {
	//����
	
	String color = new String(); //�� ����
	int order; //�� ����(1~9)
	boolean exist; //�����ǿ� �����ִ��� Ȯ��
	
	Block() {}
	//�� ��° ������: ��ü ó���� ������ �� �������� ���� ������ �÷��� �־ ���� �����ϱ�
	Block(String color, int order) { this.color = color; this.order = order; this.exist = true;	}
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

class ActionCard extends Game {
	//7���� �׼�ī��
	int num_card; //���� �׼� ī�� ��
	///GUI/// ȭ�� �� ���� �ִ� ī�� ���̰Բ�
	Block choose_block; //������ �� ��ü
	
	UpOne upone = new UpOne();
	UpTwo uptwo = new UpTwo();
	UpThree upthree = new UpThree();
	RemoveCard remove = new RemoveCard();
	DownCard down = new DownCard();
	
	void next_turn() {
		//���� �÷��̾� ������ �Ѿ��
		///GUI///�ε� � �÷��̾� �������� ǥ�����ֱ�
		if(current_player == player1)		current_player = player2;
		else current_player = player2;
	} 
}

class RemoveCard extends ActionCard {
	//������ ��� ������
	int num_thiscard;
	
	RemoveCard() { this.num_thiscard = 2; } //ó���� �־��� ī�� 2��
	
	void removeCard() {
		//������ ��� ������, �̶� choose�� game�� ������ �� �־������
		for(int i=0;i<num_block;i++)
		{
			if(block[i].order == num_block) //������������� Ȯ��
			{
				choose_block.exist = false; //�ش� ��� ������
				this.num_card --; //�ش� �׼� ī�� ���� -1
				num_thiscard--;
				///GUI///�ٲ� ���� �����ִ� �׼�ī�� �ݿ�
				this.next_turn(); //���� �÷��̾�� �Ѿ��
			}
		}
	}
	
	boolean possible() { 
		//�� �׼� ī�� ��� �������� Ȯ�� �� ���� 
		if(this.num_thiscard > 0) //�����ִ� ī������ Ȯ��
			return true; 
		else return false;
		//��� ������ ��Ҵ� �׻� true
		} 
}

class DownCard extends ActionCard {
	//������ ��� ���������� ������
	int num_thiscard;
	
	DownCard() {this.num_thiscard = 1; } //ó���� �־��� ī�� 1��
	
	///GUI/// �̶� ȭ�鿡�� Ŭ���� ���� Block������ choose_block �Ű������� ���Բ� �� �� �ֳ�?
	void downCard( Block choose_block) {
		//������ ��� ������ ������ �ٲٱ�, ������ ��� �Ű�������
		int choose_block_order = choose_block.order;
		for(int i=0;i<num_block;i++) {
			//������ ��� �Ʒ� �ִ� �͵� �� ĭ�� ����
			if(block[i].order > choose_block_order) {
				block[i].order++;
			}
			//������ ��� �� �Ʒ���
			if(block[i].order == choose_block_order) {
				block[i].order = num_block;
			}
		}
		this.num_card --; //�ش� �׼� ī�� ���� -1
		num_thiscard--;
		///GUI///�ٲ� ���� �����ִ� �׼�ī�� �ݿ�
		this.next_turn(); //���� ������ �Ѿ��
	}
	
	boolean possible() { 
		if(this.num_thiscard > 0) //�����ִ� ī������ Ȯ��
			return true; 
		else return false; } //��� ������ ��Ҵ� �׻� true
}

class UpOne extends ActionCard {
	//������ ��� ���� 1ĭ �ø���
	int num_thiscard;
	
	UpOne() { System.out.println("upone"); this.num_thiscard = 2; } //ó���� �־��� ī�� 2��
	
	///GUI/// �̶� ȭ�鿡�� Ŭ���� ���� Block������ choose_block �Ű������� ���Բ� �� �� �ֳ�?
	void upOne(Block choose_block) {
		//������ ��� ���� 1ĭ �ø���
		int choose_block_order = choose_block.order;
		for(int i=0;i<num_block;i++) {
			//������ ��� ���� ��� �� ĭ �Ʒ���
			if(block[i].order == choose_block_order+1) {
				block[i].order--;
			}
			//������ ��� ��ĭ ����
			if(block[i].order == choose_block_order) {
				block[i].order++;
			}
		}
		this.num_card --; //�ش� �׼� ī�� ���� -1
		num_thiscard--;
		///GUI///�ٲ� ���� �����ִ� �׼�ī�� �ݿ�
		this.next_turn(); //���� ������ �Ѿ��
	}
	
	boolean possible() {
		//������ �������� Ȯ�� �� ����
		if(this.num_thiscard > 0) {
			if(choose_block.order > 1) {
			//���� ����� ������ 1���� ū��
			return true;
			}
			else return false;
		}
		else return false;
	}
}
class UpTwo extends ActionCard {
	//������ ��� ���� 2ĭ �ø���
	int num_thiscard;
	
	UpTwo() { this.num_thiscard = 1; } //ó���� �־��� ī�� 1��
	
	///GUI/// �̶� ȭ�鿡�� Ŭ���� ���� Block������ choose_block �Ű������� ���Բ� �� �� �ֳ�?
	void upTwo(Block choose_block) {
		//������ ��� ���� 2ĭ �ø���
		int choose_block_order = choose_block.order;
		for(int i=0;i<num_block;i++) {
		//������ ��� ���� �� ��� �� ĭ�� �Ʒ���
			if(block[i].order == choose_block_order+1 | block[i].order == choose_block_order+2) {
				block[i].order--;
			}
			//������ ��� 2ĭ ����
			if(block[i].order == choose_block_order) {
				block[i].order = block[i].order+2;
			}
		}
		this.num_card --; //�ش� �׼� ī�� ���� -1
		num_thiscard--;
		///GUI///�ٲ� ���� �����ִ� �׼�ī�� �ݿ�
		this.next_turn(); //���� ������ �Ѿ��
	}
		
	boolean possible() {
		//������ �������� Ȯ�� �� ����
		if(this.num_thiscard > 0) {
			if(choose_block.order > 2) {
			//���� ����� ������ 2���� ū��
			return true;
			}
			else return false;
		}
		else return false;
	}
}
class UpThree extends ActionCard {
	//������ ��� ���� 3ĭ �ø���
	int num_thiscard;
	
	UpThree() { this.num_thiscard = 1; } //ó���� �־��� ī�� 1��
	
	///GUI/// �̶� ȭ�鿡�� Ŭ���� ���� Block������ choose_block �Ű������� ���Բ� �� �� �ֳ�?
	void upThree(Block choose_block) {
		//������ ��� ���� 3ĭ �ø���
		int choose_block_order = choose_block.order;
		for(int i=0;i<num_block;i++) {
			//������ ��� ���� �� ��� �� ĭ�� �Ʒ���
			if(block[i].order >= choose_block_order+1 & block[i].order <= choose_block_order+3) {
				block[i].order--;
			}
			//������ ���  3ĭ ����
			if(block[i].order == choose_block_order) {
				block[i].order = block[i].order+3;
			}
		}
		this.num_card --; //�ش� �׼� ī�� ���� -1
		num_thiscard--;
		///GUI///�ٲ� ���� �����ִ� �׼�ī�� �ݿ�
		this.next_turn(); //���� ������ �Ѿ��
	}
	
	boolean possible() {
		//������ �������� Ȯ�� �� ����
		if(this.num_thiscard > 0) {
			if(choose_block.order > 3) {
			//���� ����� ������ 3���� ū��
			return true;
			}
			else return false;
		}
		else return false;
	}
}

