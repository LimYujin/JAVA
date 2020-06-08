package tikitaka;

public class tikitaka {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}


class Game{
	//블럭이 있는 보드
	
	int[] block_order = new int[9];
	Block[] block =  new Block[9];
	int num_block; //남아있는 블럭 개수
	Player player1 = new Player();
	Player player2 = new Player();
	
	void Init() {
		//block_order에 랜덤으로 0~8 순서 넣어주기
		block[0] = new Block("red", block_order[0]);
		block[1] = new Block("orange", block_order[0]);
		block[2] = new Block("yellow", block_order[0]);
		block[7] = new Block("green", block_order[0]);
		block[3] = new Block("sky", block_order[0]);
		block[4] = new Block("blue", block_order[0]);
		block[5] = new Block("purple", block_order[0]);
		block[6] = new Block("pink", block_order[0]);
		block[8] = new Block("gray", block_order[0]);
	}
	
	void gameOver() {
		if(player1.num_card == 0 & player2.num_card == 0)
		{
			//게임 종료
		}
	}
	
	
}

class Player {
	//참여자
	
	int num_card;
	int score;
	MissonCard misson = new MissonCard();
	void calculation(Game game, int score) {
		//점수 계산 후 반환
		//참여자의 미션카드의 1,2,3등 블럭색깔과 게임에 존재하는 1,2,3등 블럭색깔 비교
		//예를 들어 1등 블럭 성공했을 때
		Block temp = new Block();
		for( int i=0 ; i<9 ; i++)
		{
			if(game.block[i].order == 1)
				temp = game.block[i];
				break;
		}
		if(misson.first.color == temp.color)
			this.score += 10;
		
	}
	Player() { this.num_card = 7; this.score = 0; }
	
}

class Block {
	//블럭들
	
	String color = new String();
	int order;
	boolean exist;
	
	Block() {}
	//순서 랜덤으로 뽑고 객체 처음에 생성할 때 컬러만 넣어서 블럭들 생성하기
	Block(String color, int order) { this.color = color; this.order = order; this.exist = true;	}
}

class MissonCard {
	Block first;
	Block second;
	Block third;
	
	//랜덤으로 뽑아서 넣어주기
}

class ActionCard {
	//7개의 액션카드

	int num;
	Block choose_block; //선택한 블럭 객체
}

class RemoveCard extends ActionCard {
	//마지막 블록 버리기
	
	RemoveCard() { this.num = 2; } //처음에 존재하는 갯수
	
	void removeCard(Block choose_block) {
		//마지막 블록 버리기, 이때 choose에 game의 마지막 블럭 넣어줘야함
		choose_block.exist = false;
		num --; //해당 카드 갯수 -1
	}
	
	boolean possible() { return true; } //가능한 조건인지 확인 후 리턴 //얘는 항상 true
	
}

class DownCard extends ActionCard {
	//선택한 블록 마지막으로 내리기
	
	DownCard() {this.num = 1; }
	
	boolean possible() { return true; } //가능한 조건인지 확인 후 리턴 //얘는 항상 true
}
class UpOne extends ActionCard {
	//선택한 블록 위로 1칸 올리기
	
	UpOne() { this.num = 2; }
	
	boolean possible() {
		if(choose_block.order > 0)
		//가능한 조건인지 확인 후 리턴
			return true;
		else return false;
	}
}
class UpTwo extends ActionCard {
	//선택한 블록 위로 2칸 올리기
	
	UpTwo() { this.num = 1; }
	
	boolean possible() {
		if(choose_block.order > 1)
		//가능한 조건인지 확인 후 리턴
			return true;
		else return false;
	}
}
class UpThree extends ActionCard {
	//선택한 블록 위로 3칸 올리기
	
	UpThree() { this.num = 1; }
	
	boolean possible() {
		if(choose_block.order > 2)
		//가능한 조건인지 확인 후 리턴
			return true;
		else return false;
	}
}

