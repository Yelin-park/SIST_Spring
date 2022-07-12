package di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// xml 파일을 대신할 자바 설정 파일(DI)
// @Configuration과 @Bean 어노테이션을 사용해야된다.

@Configuration // 클래스를 스프링 설정으로 사용함을 의미
public class Config {

	// 순수 자바 코딩으로 객체 생성
	// RecordImple record = new RecordImpl();
	// xml 파일에서 <bean> 태그로 생성한 빈 객체 <bean id="record" class="di.RecordImpl"></bean>
	@Bean // 메서드의 리턴 값을 빈 객체로 사용함을 의미
	public RecordImpl record() {
		return new RecordImpl();
	}
	
	@Bean(name="rvi") // 이름을 안주면 아래 함수명으로 가져오면 됨
	public RecordViewImpl getRecordViewImpl() {
		RecordViewImpl rvi = new RecordViewImpl();
		rvi.setRecord( record() ); // setter 프로퍼티를 통해서 의존성 주입(DI) - 메서드를 호출해서 주입
		return rvi;
	}
	
} // class
