package tikitaka;

import java.util.LinkedList;
import java.util.Random;

/////////////주석 설명/////////////
/* 해당 코드에 대한 설명은 //로 주석처리
 * 해당 부분에 구현해야 될 코드 설명은 ////로 주석처리
 * GUI 관련 요청은 ///GUI///로 주석처리
 * 소켓 관련 요청은 ///SOCKET///로 주석처리
 */

public class tikitaka {

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();
		game.game_init();
	}*/

}

class Game{
	//블럭이 있는 보드
	
	int[] block_order = new int[9]; //처음 생성할 때만 쓰일 블럭 순서를 담은 배열
	Block[] block;
	int num_block; //남아있는 블럭 개수
	Player player1;
	Player player2;
	Player current_player;
	
	void game_init() {
		
		//block_order에 랜덤으로 0~8 순서 넣어주기
		LinkedList<Integer> order_list = new LinkedList<Integer>();
		
		for(int i = 1; i< 10;i++)	 	order_list.addLast(i);
		Random random = new Random();
		int index;
		for(int i = order_list.size(); i> 0; i--) {
			index = random.nextInt(i);
			this.block_order[9-i] = order_list.get(index);
			System.out.println("block["+(9-i)+"] : " + this.block_order[9-i]); //확인용
			order_list.remove(index);
		}
		
		//block 9개에 정보 넣어주기 각 블럭은 색상, 순서 정보를 가지고 있음
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
		
		//player 2명 생성
		this.player1 = new Player();
		this.player2 = new Player();
		this.current_player = player1;
		
		//보드판에 남아있는 블럭 갯수
		this.num_block = 9;
		
		///GUI///게임 초기 화면 넣어주기
		
		System.out.println("game start!");
	}

	void gameOver() {
		//2명의 player가 카드를 모두 쓰는 순간 종료
		if(player1.action_card.num_card == 0 & player2.action_card.num_card == 0)
		{
			//player 점수 계산하기
			player1.calculation(player1.score);
			player2.calculation(player2.score);
			//게임 종료
			System.out.println("game over!");
			///GUI///게임종료 넣어주기
			///GUI///각 player의 점수 출력
			System.out.println("player1's score : " + player1.score);
			System.out.println("player2's score : " + player2.score);
			
		}
	}
}

class Player extends Game{
	//참여자
	
	String id;
	ActionCard action_card; //player의 액션카드
	int score; //점수
	MissonCard misson = new MissonCard(); //가지고 있는 미션 카드 ; 생성자로 랜덤으로 하나 뽑히게끔
	///GUI///기본은 그냥 카드 덮은 그림이었다가 버튼 누르면 미션카드 보이게끔 하면 좋을 것 같은데, 이걸 어떻게 해야할까
	
	void calculation(int score) {
		//점수 계산 후 player의 score 업데이트
		//참여자의 미션카드의 1,2,3등 블럭색깔과 게임에 존재하는 1,2,3등 블럭색깔 비교
		Block temp = new Block();
		for( int i=0 ; i<9 ; i++)
		{
			//보드판에서 1등인 블럭의 색이 player의 미션카드의 1등 색과 같으면 7점 부여
			if(block[i].order == 1) {
				temp = block[i];
				if(	this.misson.first_color == temp.color) { 
					this.score += 7; 
				}
			}
			//보드판에서 2등인 블럭의 색이 player의 미션카드의 1등 또는 2등 색과 같으면 5점 부여
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
			//보드판에서 2등인 블럭의 색이 player의 미션카드의 1등,2등,3등 색과 같으면 5점 부여
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
	//생성자 : 처음 객체 생성 시 기본 셋팅
	Player() { this.action_card = new ActionCard(); this.score = 0; }
	
}

class Block {
	//블럭들
	
	String color = new String(); //블럭 색깔
	int order; //블럭 순서(1~9)
	boolean exist; //보드판에 남아있는지 확인
	
	Block() {}
	//두 번째 생성자: 객체 처음에 생성할 때 랜덤으로 돌린 순서랑 컬러만 넣어서 블럭들 생성하기
	Block(String color, int order) { this.color = color; this.order = order; this.exist = true;	}
	///GUI/// 색깔 매개변수로 넣으면 각 블록에 해당하는 이미지도 변수처럼 가지고 있게 할 수 있나?
}

class MissonCard {
	//미션카드
	
	//자신의 미션 색깔
	String first_color;
	String second_color;
	String third_color;
	
	//생성자로 랜덤으로 뽑아서 넣어주기
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
		System.out.println("first_color :  " + this.first_color); //확인용
		color_list.remove(index);
		i--;
		
		index = random.nextInt(i);
		this.second_color = color_list.get(index);
		System.out.println("second_color :  " + this.second_color); //확인용
		color_list.remove(index);
		i--;
		
		index = random.nextInt(i);
		this.third_color = color_list.get(index);
		System.out.println("third_color :  " + this.third_color); //확인용
		color_list.remove(index);
	}
	
	///GUI/// 얘도 세 가지 색깔 넣으면 그에 해당하는 이미지를 변수처럼 가지고 있게 할 수 있나?
}

class ActionCard extends Game {
	//7개의 액션카드
	int num_card; //남은 액션 카드 수
	///GUI/// 화면 상에 남아 있는 카드 보이게끔
	Block choose_block; //선택한 블럭 객체
	
	UpOne upone = new UpOne();
	UpTwo uptwo = new UpTwo();
	UpThree upthree = new UpThree();
	RemoveCard remove = new RemoveCard();
	DownCard down = new DownCard();
	
	void next_turn() {
		//다음 플레이어 순서로 넘어가기
		///GUI///로도 어떤 플레이어 순서인지 표시해주기
		if(current_player == player1)		current_player = player2;
		else current_player = player2;
	} 
}

class RemoveCard extends ActionCard {
	//마지막 블록 버리기
	int num_thiscard;
	
	RemoveCard() { this.num_thiscard = 2; } //처음에 주어진 카드 2개
	
	void removeCard() {
		//마지막 블록 버리기, 이때 choose에 game의 마지막 블럭 넣어줘야함
		for(int i=0;i<num_block;i++)
		{
			if(block[i].order == num_block) //마지막블록인지 확인
			{
				choose_block.exist = false; //해당 블록 버려짐
				this.num_card --; //해당 액션 카드 갯수 -1
				num_thiscard--;
				///GUI///바뀐 블럭과 남아있는 액션카드 반영
				this.next_turn(); //다음 플레이어로 넘어가기
			}
		}
	}
	
	boolean possible() { 
		//이 액션 카드 사용 가능한지 확인 후 리턴 
		if(this.num_thiscard > 0) //남아있는 카드인지 확인
			return true; 
		else return false;
		//얘는 나머지 요소는 항상 true
		} 
}

class DownCard extends ActionCard {
	//선택한 블록 마지막으로 내리기
	int num_thiscard;
	
	DownCard() {this.num_thiscard = 1; } //처음에 주어진 카드 1개
	
	///GUI/// 이때 화면에서 클릭한 블럭이 Block형으로 choose_block 매개변수로 들어가게끔 할 수 있나?
	void downCard( Block choose_block) {
		//선택한 블록 마지막 순서로 바꾸기, 선택한 블록 매개변수로
		int choose_block_order = choose_block.order;
		for(int i=0;i<num_block;i++) {
			//선택한 블록 아래 있는 것들 한 칸씩 위로
			if(block[i].order > choose_block_order) {
				block[i].order++;
			}
			//선택한 블록 맨 아래로
			if(block[i].order == choose_block_order) {
				block[i].order = num_block;
			}
		}
		this.num_card --; //해당 액션 카드 갯수 -1
		num_thiscard--;
		///GUI///바뀐 블럭과 남아있는 액션카드 반영
		this.next_turn(); //다음 순서로 넘어가기
	}
	
	boolean possible() { 
		if(this.num_thiscard > 0) //남아있는 카드인지 확인
			return true; 
		else return false; } //얘는 나머지 요소는 항상 true
}

class UpOne extends ActionCard {
	//선택한 블록 위로 1칸 올리기
	int num_thiscard;
	
	UpOne() { System.out.println("upone"); this.num_thiscard = 2; } //처음에 주어진 카드 2개
	
	///GUI/// 이때 화면에서 클릭한 블럭이 Block형으로 choose_block 매개변수로 들어가게끔 할 수 있나?
	void upOne(Block choose_block) {
		//선택한 블록 위로 1칸 올리기
		int choose_block_order = choose_block.order;
		for(int i=0;i<num_block;i++) {
			//선택한 블록 위의 블록 한 칸 아래로
			if(block[i].order == choose_block_order+1) {
				block[i].order--;
			}
			//선택한 블록 한칸 위로
			if(block[i].order == choose_block_order) {
				block[i].order++;
			}
		}
		this.num_card --; //해당 액션 카드 갯수 -1
		num_thiscard--;
		///GUI///바뀐 블럭과 남아있는 액션카드 반영
		this.next_turn(); //다음 순서로 넘어가기
	}
	
	boolean possible() {
		//가능한 조건인지 확인 후 리턴
		if(this.num_thiscard > 0) {
			if(choose_block.order > 1) {
			//선택 블록의 순서가 1보다 큰지
			return true;
			}
			else return false;
		}
		else return false;
	}
}
class UpTwo extends ActionCard {
	//선택한 블록 위로 2칸 올리기
	int num_thiscard;
	
	UpTwo() { this.num_thiscard = 1; } //처음에 주어진 카드 1개
	
	///GUI/// 이때 화면에서 클릭한 블럭이 Block형으로 choose_block 매개변수로 들어가게끔 할 수 있나?
	void upTwo(Block choose_block) {
		//선택한 블록 위로 2칸 올리기
		int choose_block_order = choose_block.order;
		for(int i=0;i<num_block;i++) {
		//선택한 블록 위의 두 블록 한 칸씩 아래로
			if(block[i].order == choose_block_order+1 | block[i].order == choose_block_order+2) {
				block[i].order--;
			}
			//선택한 블록 2칸 위로
			if(block[i].order == choose_block_order) {
				block[i].order = block[i].order+2;
			}
		}
		this.num_card --; //해당 액션 카드 갯수 -1
		num_thiscard--;
		///GUI///바뀐 블럭과 남아있는 액션카드 반영
		this.next_turn(); //다음 순서로 넘어가기
	}
		
	boolean possible() {
		//가능한 조건인지 확인 후 리턴
		if(this.num_thiscard > 0) {
			if(choose_block.order > 2) {
			//선택 블록의 순서가 2보다 큰지
			return true;
			}
			else return false;
		}
		else return false;
	}
}
class UpThree extends ActionCard {
	//선택한 블록 위로 3칸 올리기
	int num_thiscard;
	
	UpThree() { this.num_thiscard = 1; } //처음에 주어진 카드 1개
	
	///GUI/// 이때 화면에서 클릭한 블럭이 Block형으로 choose_block 매개변수로 들어가게끔 할 수 있나?
	void upThree(Block choose_block) {
		//선택한 블록 위로 3칸 올리기
		int choose_block_order = choose_block.order;
		for(int i=0;i<num_block;i++) {
			//선택한 블록 위의 세 블록 한 칸씩 아래로
			if(block[i].order >= choose_block_order+1 & block[i].order <= choose_block_order+3) {
				block[i].order--;
			}
			//선택한 블록  3칸 위로
			if(block[i].order == choose_block_order) {
				block[i].order = block[i].order+3;
			}
		}
		this.num_card --; //해당 액션 카드 갯수 -1
		num_thiscard--;
		///GUI///바뀐 블럭과 남아있는 액션카드 반영
		this.next_turn(); //다음 순서로 넘어가기
	}
	
	boolean possible() {
		//가능한 조건인지 확인 후 리턴
		if(this.num_thiscard > 0) {
			if(choose_block.order > 3) {
			//선택 블록의 순서가 3보다 큰지
			return true;
			}
			else return false;
		}
		else return false;
	}
}

