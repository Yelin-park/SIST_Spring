package aop.advice;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;

// 핵심기능이 수행 후에 예외 발생하지 않았을 경우
public class LogPrintAfterReturningAdvice implements AfterReturningAdvice{

	@Override
	public void afterReturning(
				Object returnValue // 리턴값
				, Method method // 호출한 메서드
				, Object[] args // 매개변수
				, Object target // 실제 핵심기능 객체
				) throws Throwable {
		
		String methodName = method.getName();
		Log log = LogFactory.getLog(this.getClass());
		
		log.info(">>> " + methodName + "() : LogPrintAfterReturningAdvice가 호출 됨 : " + returnValue);
		
	}

}
