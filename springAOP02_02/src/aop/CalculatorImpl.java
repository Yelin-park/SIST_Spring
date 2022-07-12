package aop;

import org.springframework.stereotype.Component;

// @Component("calc")
@Component()
public class CalculatorImpl implements Calculator{
	
	@Override
	public int add(int x, int y) {
		// 전
		int result = x + y; // 핵심 기능
		// 후
		return result;
	}

	@Override
	public int sub(int x, int y) {
		int result = x - y; // 핵심 기능
		return result;
	}

	@Override
	public int mult(int x, int y) {
		int result = x * y; // 핵심 기능
		return result;
	}

	@Override
	public int div(int x, int y) {
		int result = x / y; // 핵심 기능
		return result;
	}


}
