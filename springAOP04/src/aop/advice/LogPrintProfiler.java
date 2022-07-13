package aop.advice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

// before, after, around advice를 대신할 공통 기능을 구현할 클래스
@Component("logPrintProfiler") // 안주면 클래스명 앞글자만 소문자로 변한 것으로!
@Aspect // <aop:aspect id="traceAspect" ref="logPrintProfiler"> 코딩과 같음
public class LogPrintProfiler {

	// <aop:after method="afterFinally" pointcut="execution(public * aop..*.*(*,*))" />
	@Pointcut("execution(public * aop..*.*(*,*))") 
	public void publicMethod() {}
	
	// 3. After Advice
	// @After(value="execution(public * aop..*.*(*,*))")
	@After(value = "publicMethod()")
	public void afterFinally(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Log log = LogFactory.getLog(this.getClass());
		
		log.info(">>> " + methodName + "() :  LogPrintProfiler.afterFinally() 가 호출 됨");
	} // afterReturning
	
	// 2. Before Advice
	// public void before() { // 매개변수가 없어도 되고
	// @Before(value = "execution(public * aop..*.*(*,*))")
	@Before(value = "publicMethod()")
	public void before(JoinPoint joinPoint) { // JoinPoint를 주어도 됨
		// 공통 기능 구현 + 인증
		String methodName = joinPoint.getSignature().getName(); // 호출한 메서드의 이름
		Log log = LogFactory.getLog(this.getClass());
		
		log.info(">>> " + methodName + "() : LogPrintProfiler.before() 가 호출 됨");
	} // before
	
	// 1. Around Advice - ProceedingJoinPoint joinPoint 매개변수를 꼭 주어야 함
	// @Around(value = "execution(public * aop..*.*(*,*))")
	@Around(value = "publicMethod()")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable{
		String signature = joinPoint.getSignature().toShortString();

		Log log = LogFactory.getLog(this.getClass());      
		StopWatch sw = new StopWatch();      
		log.info("> " + signature + "() start.");
		sw.start();

		Object result = null;
		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			sw.stop();
			log.info("> " + signature + "() stop.");
			log.info("> " + signature + "() 처리 시간 :  " + sw.getTotalTimeMillis() + "ms");
		}
		return result;
	} // trace

} // class
