package newlecture.dao;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import newlecture.vo.Member;

@Repository
public class NLMemberDao implements MemberDao{
	
	// private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	// 멤버 정보를 가져오는 메서드
	@Override
	public Member getMember(String id) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * "
				+ "FROM MEMBER "
				// + "WHERE id = :파라미터이름";
				+ "WHERE id = :id";

		// 왜 MapSqlParameterSource 사용? key, value가 한 쌍으로 저장되니까 파라미터를 키 값으로 저장할 수 있기 때문에!
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		// parameterSource.addValue("파라미터이름", 파라미터값);
		// sql에서 :파라미터이름을 주고 해당 파라미터 이름을 키 값으로 준다. 그 다음 파라미터 값을 매개변수로 준다.
		parameterSource.addValue("id", id);
		
		// (String, SqlParameterSource, RowMapper<T>)
		Member member = this.jdbcTemplate.queryForObject(sql
							, parameterSource
							, ParameterizedBeanPropertyRowMapper.newInstance(Member.class));

		return member;
	} // getMember

	// [2]
	// 회원가입메서드
	@Override
	public int insert(Member member) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO MEMBER "
				+ "(ID, PWD, NAME, GENDER, BIRTH, IS_LUNAR, CPHONE, EMAIL, HABIT, REGDATE) "
				+ " VALUES( :id, :pwd, :name, :gender, :birth, :is_lunar, :cphone, :email, :habit, SYSDATE)";
		
		// member 객체의 getter를 사용하여 getId => :id 파라미터 설정
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(member);

		int result = this.jdbcTemplate.update(sql, parameterSource);

		return result;
	} // insert
	
	/*
	// [1]
	// 회원가입메서드
	@Override
	public int insert(Member member) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO MEMBER "
				+ "(ID, PWD, NAME, GENDER, BIRTH, IS_LUNAR, CPHONE, EMAIL, HABIT, REGDATE) "
				+ " VALUES( :id, :pwd, :name, :gender, :birth, :is_lunar, :cphone, :email, :habit, SYSDATE)";
		
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", member.getId());		
		parameterSource.addValue("pwd", member.getPwd());		
		parameterSource.addValue("name", member.getName());		
		parameterSource.addValue("gender", member.getGender());		
		parameterSource.addValue("birth", member.getBirth());		
		parameterSource.addValue("is_lunar", member.getIs_lunar());		
		parameterSource.addValue("cphone", member.getCphone());		
		parameterSource.addValue("email", member.getEmail());		
		parameterSource.addValue("habit", member.getHabit());		

		int result = this.jdbcTemplate.update(sql, parameterSource);

		return result;
	} // insert
	*/
}
