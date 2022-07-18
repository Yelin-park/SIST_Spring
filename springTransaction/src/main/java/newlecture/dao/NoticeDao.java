package newlecture.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import newlecture.vo.Notice;

public interface NoticeDao {

	
	// 검색한 결과의 총레코드 수를 반환하는 메서드
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException;
	
	// 공지사항 페이징 처리하여 목록 가져오는 메서드(jdbcTemplate 사용)
	public List<Notice> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException;
	
	// 공지사항 삭제하는 메서드
	public int delete(String seq) throws ClassNotFoundException, SQLException;
	
	// 공지사항 수정하는 메서드
	public int update(Notice notice) throws ClassNotFoundException, SQLException;
	
	// 해당 공지사항 상세보기 메서드
	public Notice getNotice(String seq) throws ClassNotFoundException, SQLException;

	// 공지사항 추가하는 메서드
	public int insert(Notice notice) throws ClassNotFoundException, SQLException;
	
	// 트랜잭션 테스트 용도의 추상 메서드 추가
	public void insertAndPointUpOfMember(Notice notice, String id) throws ClassNotFoundException, SQLException;
	
} // class
