package newlecture.service;

import java.sql.SQLException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import newlecture.vo.Notice;

// 해당 인터페이스에 있는 모든 메서드는 트랜잭션 처리를 하겠다.
@Transactional(propagation = Propagation.REQUIRED)
public interface MemberShipService {
	
	// NLNoticeDao에서 분리
	public void insertAndPointUpOfMember(Notice notice, String id) throws ClassNotFoundException, SQLException;
	
}
