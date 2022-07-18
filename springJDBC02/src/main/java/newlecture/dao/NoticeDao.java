package newlecture.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import newlecture.vo.Notice;

// @Component // 이거 사용해도 됨
@Repository // 자동으로 스캔이 되는 대상인데 DAO이다. 이름은 noticeDao로 잡힘
public class NoticeDao {
	
	@Autowired // 자동으로 주입
	private JdbcTemplate jdbcTemplate;
	
	// 검색한 결과의 총레코드 수를 반환하는 메서드
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException {
		String sql = "SELECT COUNT(*) "
					+ "CNT FROM NOTICES "
					+ "WHERE " + field + " LIKE ?";
		
		// return this.jdbcTemplate.queryForInt(sql, "%"+query+"%");
		return this.jdbcTemplate.queryForInt(sql, new Object[] {"%"+query+"%"});
		
	} // getCount
	
	// 공지사항 페이징 처리하여 목록 가져오는 메서드(jdbcTemplate 사용)
	public List<Notice> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException {					
		
		int srow = 1 + (page - 1) * 15; // 1, 16, 31, 46, 61, ... an = a1 + (n-1)*d (한페이지당 15개 뿌림)
		int erow = 15 + (page - 1) * 15; //15, 30, 45, 60, 75, ...
		
		String sql = "SELECT * "
					+ "FROM ("
					+ "			SELECT ROWNUM NUM, N.* "
					+ " 		FROM ("
					+ "					SELECT * "
					+ "					FROM NOTICES "
					+ "					WHERE " + field + " LIKE ? "
					+ "					ORDER BY REGDATE DESC"
					+ "					) N"
					+ "			) "
					+ "WHERE NUM BETWEEN ? AND ?"; // srow와 erow 들어감

		// 주의! 테이블의 컬럼명과 Notice DTO의 필드명이 반드시 일치해야된다. (일치안하면 가공해야됨)
		List<Notice> list = this.jdbcTemplate.query(sql
								, new Object[] {"%"+query+"%", srow, erow} // 2번째 매개변수 Object[]는 ?, ?, ? 물음표에 해당하는 값을 줌 
								, new BeanPropertyRowMapper<Notice>(Notice.class)
								);
		return list;
		
	} // getNotices
	
	// 공지사항 삭제하는 메서드
	public int delete(String seq) throws ClassNotFoundException, SQLException {

		String sql = "DELETE NOTICES "
					+ "WHERE SEQ=? ";
				
		return this.jdbcTemplate.update(sql, seq);
		
	} // delete
	
	// 공지사항 수정하는 메서드
	public int update(Notice notice) throws ClassNotFoundException, SQLException {
		
		String sql = "UPDATE NOTICES "
					+ "SET TITLE=?, CONTENT=?, FILESRC=? "
					+ "WHERE SEQ=?";
		
		return this.jdbcTemplate.update(sql, notice.getTitle(), notice.getContent(), notice.getFilesrc(), notice.getSeq());
		
	} // update
	
	// 해당 공지사항 상세보기 메서드
	public Notice getNotice(String seq) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * "
					+ "FROM NOTICES "
					+ "WHERE SEQ=?";
		
		 Notice notice =  this.jdbcTemplate.queryForObject(
				 sql
                 , new Object[] { seq }
                 , ParameterizedBeanPropertyRowMapper.newInstance(Notice.class)
                 );
		 
		return notice;
	} // getNotice

	// 공지사항 추가하는 메서드
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {

		String sql = "INSERT INTO "
					+ "NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) "
					+ "VALUES("
					+ "			(SELECT MAX(TO_NUMBER(SEQ))+1 FROM NOTICES), ?, ?, ?, SYSDATE, 0, ?)";
		
		return this.jdbcTemplate.update(sql, notice.getTitle(), notice.getContent(), notice.getWriter(), notice.getFilesrc());
		
	} // insert
	
} // class
