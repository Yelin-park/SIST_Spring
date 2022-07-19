package newlecture.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import newlecture.dao.NoticeDao;
import newlecture.vo.Notice;

@Service
public class NLMemberShipService implements MemberShipService{

	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public void insertAndPointUpOfMember(Notice notice, String id) throws ClassNotFoundException, SQLException {

		this.noticeDao.insert(notice);
		notice.setTitle(notice.getTitle() + "-2"); // 같은 제목으로 2번 쓰여지기 때문에 오류 발생함으로 위에서 제목 설정
		this.noticeDao.insert(notice);

	} // insertAndPointUpOfMember

}
