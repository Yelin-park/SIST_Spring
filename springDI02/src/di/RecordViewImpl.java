package di;

import java.util.Scanner;

// RecordViewImpl 객체는 RecordImpl 객체에 의존한다(의존관계) = RecordViewImpl + RecordImpl
// Engin + Car 클래스 [has-a 관계 (의존관계)], is-a 관계
//		의존성 주입(DI)
public class RecordViewImpl implements RecordView{
	
	// p50 의존 객체를 직접 생성하는 방식의 단점 : 결합력이 높은 코딩 == 드라이버가 일체화이면 고장나면 다 버려야하는 데 분리가 되면 교체가 가능하다)
	// private RecordImpl record = new RecordImpl(); // 국영수 점수와 총점, 평균 구하는 객체 생성
	
	private RecordImpl record = null;

	// p55 [생성자 방식]과 프로퍼티 설정 방식
	public RecordViewImpl() {
		this.record = new RecordImpl();
	}
	
	// [생성자 DI(의존성 주입)방식]
	public RecordViewImpl(RecordImpl record) {
		super();
		this.record = record;
	}
	
	// [프로퍼티 설정]
	public void setRecord(RecordImpl record) {
		this.record = record;
	}
	
	@Override
	public void input() {
		try( Scanner scanner = new Scanner(System.in) ) {
			System.out.println("> kor, int, mat input? ");
			int kor = scanner.nextInt(); // kor
			int eng = scanner.nextInt(); // eng
			int mat = scanner.nextInt(); // mat

			this.record.setKor(kor);
			this.record.setEng(eng);
			this.record.setMat(mat);
			
		} catch (Exception e) {
			e.printStackTrace();
		} // try
	}

	@Override
	public void output() {
		System.out.printf("> kor : %d, eng : %d, mat : %d, tot = %d, avg = %.2f\n"
				, this.record.getKor()
				, this.record.getEng()
				, this.record.getMat()
				, this.record.total()
				, this.record.avg());
		
	}

}
