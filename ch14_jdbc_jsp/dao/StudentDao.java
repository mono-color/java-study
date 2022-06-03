package ch14_jdbc_jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ch14_jdbc_jsp.model.StudentVO;

public class StudentDao {

	private static StudentDao instance = new StudentDao();

	public static StudentDao getInstance() {
		return instance;
	}

	private StudentDao() {

	}

	// 학생 목록 조회(SELECT)
	public ArrayList<StudentVO> getStuList(Connection conn) throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT							");
		query.append("		  stu_id	AS id			");
		query.append("		, stu_name	AS name			");
		query.append("		, stu_password	AS password	");
		query.append("		, stu_score	 AS score		");
		query.append("FROM								");
		query.append("		students					");
		query.append("		order by stu_score			");

		PreparedStatement ps = conn.prepareStatement(query.toString());

		// 4. 쿼리문 실행과 동시에 결과를
		// ResultSet 객체에 담는다.
		ResultSet rs = ps.executeQuery();

		ArrayList<StudentVO> result = new ArrayList<>();

		// 5. 실행결과인 rs를 이용하여
		// 데이터 조회
		while (rs.next()) {
			StudentVO temp = new StudentVO();

			temp.setStuId(rs.getString("id"));
			temp.setStuName(rs.getString("name"));
			temp.setStuPassword(rs.getString("password"));
			temp.setStuScore(rs.getInt("score"));

			result.add(temp);
		}

		if (ps != null)
			ps.close();
		if (rs != null)
			rs.close();

		return result;
	}

	// 로그인 (SELECT, WHERE)
	public StudentVO loginStu(Connection conn, String id) throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT							");
		query.append("		  stu_id	AS id			");
		query.append("		, stu_name	AS name			");
		query.append("		, stu_password	AS password	");
		query.append("		, stu_score	 AS score		");
		query.append("FROM								");
		query.append("		students					");
		query.append("WHERE 1=1							");
		query.append("  AND stu_id = ?					");

		PreparedStatement ps = conn.prepareStatement(query.toString());

		int idx = 1;
		ps.setString(idx++, id);

		ResultSet rs = ps.executeQuery();

		StudentVO result = new StudentVO();

		// 5. 실행결과인 rs를 이용하여
		// 데이터 조회
		while (rs.next()) {
			result.setStuId(rs.getString("id"));
			result.setStuName(rs.getString("name"));
			result.setStuPassword(rs.getString("password"));
			result.setStuScore(rs.getInt("score"));
		}

		if (ps != null)
			ps.close();
		if (rs != null)
			rs.close();

		return result;
	}

	// 회원가입 (INSERT)
	public int insertStu(Connection conn, StudentVO stu) throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO				");
		query.append("		students		  	");
		query.append("VALUES	(				");
		query.append("		?					");
		query.append("		, ?					");
		query.append("		, ?					");
		query.append("		, ?					");
		query.append("		)					");

		PreparedStatement ps = conn.prepareStatement(query.toString());

		int idx = 1;
		ps.setString(idx++, stu.getStuId());
		ps.setString(idx++, stu.getStuName());
		ps.setString(idx++, stu.getStuPassword());
		ps.setInt(idx++, stu.getStuScore());

		int cnt = ps.executeUpdate();
//		System.out.println(cnt);
		if (ps != null)
			ps.close();

		return cnt;
	}

	// 회원 수정(UPDATE)
	public int updateStu(Connection conn, StudentVO stu) throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE					");
		query.append("		students		  	");
		query.append("SET 						");
		query.append("		stu_name = ?		");
		query.append("	,	stu_password = ?	");
		query.append("	,	stu_score = ?		");
		query.append("WHERE 1=1					");
		query.append("	AND stu_id = ?			");

		PreparedStatement ps = conn.prepareStatement(query.toString());

		int idx = 1;
		ps.setString(idx++, stu.getStuName());
		ps.setString(idx++, stu.getStuPassword());
		ps.setInt(idx++, stu.getStuScore());
		ps.setString(idx++, stu.getStuId());

		int cnt = ps.executeUpdate();

		if (ps != null)
			ps.close();

		return cnt;
	}

}
