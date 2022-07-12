package aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

// Around Advice가 되려면
// aopalliance-1.0.0.jar 추가 후 MethodInterceptor 인터페이스를 구현해야한다.
public class LogPrintAroundAdvice implements MethodInterceptor{

	// method == add() / sub() / mult() / div()
	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		String methodName = method.getMethod().getName(); // 메서드의 이름 확인
		
		// 스프링에서 제공하는 StopWatch 클래스 사용 -> syso로 처리안하고 로그로 처리
		Log log = LogFactory.getLog(this.getClass()); // 로그 생성(해당 클래스에 생성하겠다)
		StopWatch sw = new StopWatch(); // 스탑워치 생성
	log.info("> " + methodName + " () start.");
		sw.start(); // 메서드 실행 전 스탑워치 시작
		Object result = method.proceed(); // 핵심기능 / 메서드를 실행시키고 결과 값을 Object로 돌려줌 - ex) add() 실행시키면 결과를 돌려줌
		sw.stop(); // 메서드 실행이 끝나면 스탑워치 종료
	log.info("> " + methodName + " () stop.");
	log.info("> " + methodName + " () 처리 시간 : " + sw.getTotalTimeMillis() +"ms");
		// 로그처리
		
		return result;
	}
	
	
} // class
