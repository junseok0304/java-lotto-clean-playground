import controller.LottoMachineController;

public class Application {

    public static void main(String[] args) {
        LottoMachineController lottoMachine = new LottoMachineController();
        lottoMachine.run();
    }
}

// 일단 고민을 해보자.
// 로또 수동 모델과 로또 자동 모델을 나누어서 진행하는 방안이 어떨까?
// 1. 사용자 구입 금액 입력 -> 수동 구매 장수 입력 -> 수동 구매 번호 입력 ->
// 2. 수동 n장 자동 m장 수량 출력 -> 수동 구매 번호 출력, 자동 구매 번호 출력
// 3. 지난 주 당첨 번호 입력(6개), 보너스 볼(1개) 입력
// 4. 당첨 통계 출력 (3,4,5,5+보,6 개 일치) 일치 갯수, 총 수익률 출력
// 로또 서비스를 만들자 (자동,수동 로또 생성을 담당하게 해서 컨트롤러의 기능을 분산해줌)
// 당첨 결과를 분리해서 만들자.
