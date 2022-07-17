package newlecture.dao;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import newlecture.vo.Member;

@Repository
public class MemberDaoJdbc {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 멤버 정보를 가져오는 메서드
	public Member getMember(String id) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT * "
					+ "FROM MEMBER "
					+ "WHERE id = ?";
		
		Member member = this.jdbcTemplate.queryForObject(sql, new Object[] {id} , ParameterizedBeanPropertyRowMapper.newInstance(Member.class));
		
		return member;
	}
	
	// 회원가입메서드
	public int insert(Member member) throws ClassNotFoundException, SQLException
	{
		String sql = "INSERT INTO MEMBER "
					+ "(ID, PWD, NAME, GENDER, BIRTH, IS_LUNAR, CPHONE, EMAIL, HABIT, REGDATE) "
					+ " VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		
		int result = this.jdbcTemplate.update(sql, member.getId(), member.getPwd(), member.getName(), member.getGender(),
								member.getBirth(), member.getIs_lunar(), member.getCphone(), member.getEmail(), member.getHabit());
		
		return result;
	}
}
