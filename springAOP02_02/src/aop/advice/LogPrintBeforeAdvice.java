package aop.advice;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

@Component
public class LogPrintBeforeAdvice implements MethodBeforeAdvice {

	// 핵심기능을 실행하기 전
	// calc.add(4,2)
	@Override
	public void before(
						Method method // 호출한 메서드(ex. add())
						, Object[] args // 메서드의 매개변수(ex. 4,2)
						, Object target // 핵심 기능이 구현된 실제 객체(ex. calc)
						) throws Throwable {
		
		String methodName = method.getName();
		Log log = LogFactory.getLog(this.getClass());
		
		log.info(">>> " + methodName + "() : LogPrintBeforeAdvice가 호출 됨");
		
	} // before

}
