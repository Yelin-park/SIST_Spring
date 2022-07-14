package newlecture.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import newlecture.vo.Notice;

// @Component // 이거 사용해도 됨
@Repository // 자동으로 스캔이 되는 대상인데 DAO이다. 이름은 noticeDao로 잡힘
public class NoticeDao {
	
	// 검색한 결과의 총레코드 수를 반환하는 메서드
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException {
		String sql = "SELECT COUNT(*) "
					+ "CNT FROM NOTICES "
					+ "WHERE " + field + " LIKE ?";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT", "tiger");

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%" + query + "%");
		
		ResultSet rs = st.executeQuery();
		rs.next();
		
		int cnt = rs.getInt("cnt");
		
		rs.close();
		st.close();
		con.close();
		
		return cnt;
	} // getCount
	
	// 공지사항 페이징 처리하여 목록 가져오는 메서드
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

		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT", "tiger");

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%" + query + "%");
		st.setInt(2, srow);
		st.setInt(3, erow);

		ResultSet rs = st.executeQuery();
		
		List<Notice> list = new ArrayList<Notice>();
		
		while(rs.next()){
			Notice n = new Notice();
			n.setSeq(rs.getString("seq"));
			n.setTitle(rs.getString("title"));
			n.setWriter(rs.getString("writer"));
			n.setRegdate(rs.getDate("regdate"));
			n.setHit(rs.getInt("hit"));
			n.setContent(rs.getString("content"));
			n.setFilesrc(rs.getString("fileSrc"));
			
			list.add(n);
		} // while
		
		rs.close();
		st.close();
		con.close();
		
		return list;
	} // getNotices
	
	// 공지사항 삭제하는 메서드
	public int delete(String seq) throws ClassNotFoundException, SQLException {

		String sql = "DELETE NOTICES "
					+ "WHERE SEQ=? ";

		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT", "tiger");

		PreparedStatement st = con.prepareStatement(sql);	
		st.setString(1, seq);
		
		int af = st.executeUpdate();
		
		return af;
	} // delete
	
	// 공지사항 수정하는 메서드
	public int update(Notice notice) throws ClassNotFoundException, SQLException {
		
		String sql = "UPDATE NOTICES "
					+ "SET TITLE=?, CONTENT=?, FILESRC=? "
					+ "WHERE SEQ=?";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT", "tiger");
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, notice.getTitle());
		st.setString(2, notice.getContent());
		st.setString(3, notice.getFilesrc());
		st.setString(4, notice.getSeq());		
		
		int af = st.executeUpdate();
		
		return af;
	} // update
	
	// 해당 공지사항 상세보기 메서드
	public Notice getNotice(String seq) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * "
					+ "FROM NOTICES "
					+ "WHERE SEQ=" + seq;

		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT", "tiger");
		
		Statement st = con.createStatement();

		ResultSet rs = st.executeQuery(sql);
		rs.next();
		
		Notice notice = new Notice();
		notice.setSeq(rs.getString("seq"));
		notice.setTitle(rs.getString("title"));
		notice.setWriter(rs.getString("writer"));
		notice.setRegdate(rs.getDate("regdate"));
		notice.setHit(rs.getInt("hit"));
		notice.setContent(rs.getString("content"));
		notice.setFilesrc(rs.getString("fileSrc"));
		
		rs.close();
		st.close();
		con.close();
		
		return notice;
	} // getNotice

	// 공지사항 추가하는 메서드
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {

		String sql = "INSERT INTO "
					+ "NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) "
					+ "VALUES("
					+ "			(SELECT MAX(TO_NUMBER(SEQ))+1 FROM NOTICES), ?, ?, ?, SYSDATE, 0, ?)"; // 글쓴이는 yaliny

		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT", "tiger");
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, notice.getTitle());
		st.setString(2, notice.getContent());
		st.setString(3, notice.getWriter());
		st.setString(4, notice.getFilesrc());
		
		int af = st.executeUpdate();
		
		st.close();
		con.close();
		
		return af;
	} // insert
	
} // class
