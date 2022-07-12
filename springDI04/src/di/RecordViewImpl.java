package di;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// <bean></bean> 되어진 것
@Component // 컴포넌트 스캔을 이용한 빈 자동 등록을 해라
// @Component("rvi") // 이름(id) 등록
public class RecordViewImpl implements RecordView{
	
	// RecordImpl 스프링 빈 객체가 생성되면 (<bean id="record" class="di.RecordImpl"></bean>)
	@Autowired // 자동으로 의존 주입(DI)을 해라
	private RecordImpl record = null;

	// p55 [생성자 방식]과 프로퍼티 설정 방식
	public RecordViewImpl() {
		// this.record = new RecordImpl();
	}
	
	// [생성자 DI(의존성 주입)방식]
	// @Autowired // 생성자에도 어노테이션을 줄 수 있고
	public RecordViewImpl(RecordImpl record) {
		super();
		this.record = record;
	}
	
	// [프로퍼티 설정]
	// @Autowired // setter에도 어노테이션을 줄 수 있음
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
