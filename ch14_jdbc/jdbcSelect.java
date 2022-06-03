package ch14_jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class jdbcSelect {

	public static void main(String[] args) {
		// 변수 정의
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		// 프로퍼티 읽어오기
//		Properties prop = new Properties();
//		InputStream reader = getClass().getClassLoader().getResourceAsStream("config/db.properties");
		

		// 1. 드라이버 로딩
		// (프로그램 시작할 때 딱 한번만 로딩해주면 됨)
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 등록 실패");
			System.exit(0); // 프로그램 종료
		}

		// localhost = 127.0.0.1 , 192.168.0.29
		// 터미널 열고 ifconfig 하면 본인 pc 아이피 나옴
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String id = "jdbc";
		String pw = "jdbc";

		try {
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("커넥션 성공");
			// 3. 쿼리문 객체를 생성
			StringBuffer query = new StringBuffer();
			query.append("SELECT"				);
			query.append("	stu_id AS id"				);
			query.append("	, stu_name AS name"			);
			query.append("	, stu_password AS password"		);
			query.append("	, stu_score AS score "		);
			query.append("FROM"					);
			query.append("	students"			);

//			ps = conn.prepareStatement("SELECT * FROM students");
			ps = conn.prepareStatement(query.toString());

			// 4. 쿼리문 실행과 동시에 결과를 ResultSet 객체에 담는다.
			rs = ps.executeQuery();

			// 5. 실행결과인 rs를 이용하여 데이터 조회
			// 데이터 조회
			while (rs.next()) {
				String stuId = rs.getString("id");
				String stuName = rs.getString("name");
				String stuPassword = rs.getString("password");
				int stuScore = rs.getInt("score");

				System.out
						.println("아이디: " + stuId + ", 이름: " + stuName + ", 비밀번호: " + stuPassword + ", 점수: " + stuScore);
			}

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("커넥션 실패");
		} finally {
			// 6. 자원 정리(close)
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			// Connection 객체를 나중에 닫고, 꼭 닫아야 함
			// 프로그램 강제 종료시, 커넥션이 닫히지 않고
			// 계속 쌓여서 나중에 에러 발생 (이클립스 재시작)
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
