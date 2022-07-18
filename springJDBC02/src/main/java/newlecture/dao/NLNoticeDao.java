package newlecture.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import newlecture.vo.Notice;

// @Component // 이거 사용해도 됨
@Repository // 자동으로 스캔이 되는 대상인데 DAO이다. 이름은 noticeDao로 잡힘
public class NLNoticeDao implements NoticeDao{
	
	// private JdbcTemplate jdbcTemplate;
	@Autowired // 자동으로 주입
	private NamedParameterJdbcTemplate jdbcTemplate; // ***
	
	// 검색한 결과의 총레코드 수를 반환하는 메서드
	@Override
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException {
		String sql = "SELECT COUNT(*) "
					+ "CNT FROM NOTICES "
					+ "WHERE " + field + " LIKE :query";
		
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("query", query);
		
		return this.jdbcTemplate.queryForInt(sql, parameterSource);
		
	} // getCount
	
	// 공지사항 페이징 처리하여 목록 가져오는 메서드(jdbcTemplate 사용)
	@Override
	public List<Notice> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException {					
		
		int srow = 1 + (page - 1) * 15; // 1, 16, 31, 46, 61, ... an = a1 + (n-1)*d (한페이지당 15개 뿌림)
		int erow = 15 + (page - 1) * 15; //15, 30, 45, 60, 75, ...
		
		String sql = "SELECT * "
					+ "FROM ("
					+ "			SELECT ROWNUM NUM, N.* "
					+ " 		FROM ("
					+ "					SELECT * "
					+ "					FROM NOTICES "
					+ "					WHERE " + field + " LIKE :query "
					+ "					ORDER BY REGDATE DESC"
					+ "					) N"
					+ "			) "
					+ "WHERE NUM BETWEEN :srow AND :erow"; // srow와 erow 들어감
		
		Map<String, Object> paramMap = new HashMap();
		paramMap.put("query", "%"+query+"%");
		paramMap.put("srow", srow);
		paramMap.put("erow", erow);
		
		// paramMap을 사용
		List<Notice> list = this.jdbcTemplate.query(
								sql
								, paramMap
								// , new Object[] {"%"+query+"%", srow, erow}
								, new BeanPropertyRowMapper<Notice>(Notice.class)
								);
		return list;
		
	} // getNotices
	
	// 공지사항 삭제하는 메서드
	@Override
	public int delete(String seq) throws ClassNotFoundException, SQLException {

		String sql = "DELETE NOTICES "
					+ "WHERE SEQ = :seq ";
		
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("seq", seq);
				
		return this.jdbcTemplate.update(sql, parameterSource);
		
	} // delete
	
	// 공지사항 수정하는 메서드
	@Override
	public int update(Notice notice) throws ClassNotFoundException, SQLException {
		
		String sql = "UPDATE NOTICES "
					+ "SET TITLE = :title, CONTENT = :content, FILESRC = :filesrc "
					+ "WHERE SEQ = :seq";
		
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(notice);
		
		return this.jdbcTemplate.update(sql, parameterSource);
		
	} // update
	
	// 해당 공지사항 상세보기 메서드
	@Override
	public Notice getNotice(String seq) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * "
					+ "FROM NOTICES "
					+ "WHERE SEQ=:seq";
		
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("seq", seq);
		
		 Notice notice =  this.jdbcTemplate.queryForObject(
				 sql
                 , parameterSource
                 , ParameterizedBeanPropertyRowMapper.newInstance(Notice.class)
                 );
		 
		return notice;
	} // getNotice

	// 공지사항 추가하는 메서드
	@Override
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {

		String sql = "INSERT INTO "
					+ "NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) "
					+ "VALUES("
					+ "			(SELECT MAX(TO_NUMBER(SEQ))+1 FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";
		
		/*
		Map<String, Object> paramMap = new HashMap();
		paramMap.put("title", notice.getTitle());
		paramMap.put("content", notice.getContent());
		paramMap.put("writer", notice.getWriter());
		paramMap.put("filesrc", notice.getFilesrc());
	
		return this.jdbcTemplate.update(sql, paramMap);
		*/
		
		// SqlParameterSource 사용하면 Notice 객체로 바로 사용 가능
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(notice);
		return this.jdbcTemplate.update(sql, parameterSource);
		
	} // insert
	
} // class
