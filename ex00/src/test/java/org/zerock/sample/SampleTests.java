package org.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

// p62
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleTests {
	
	// @Autowired와의 차이점은 setter를 lombok으로 만들고 자동으로 주입을 받겠다.
	@Setter(onMethod_ = @Autowired)
	private Restaurant restaurant;

	@Test
	public void test() {
		assertNotNull(restaurant);
	    log.info(restaurant);
	    log.info("------------------------------");
	    log.info(restaurant.getChef());
	}

}
