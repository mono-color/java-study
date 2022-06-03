package ch14_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class jdbcUpdate {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");

			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 등록 실패");
			System.exit(0); // 프로그램 종료
		}

		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String id = "jdbc";
		String pw = "jdbc";

		try {
			conn = DriverManager.getConnection(url, id, pw);
			
			StringBuffer query = new StringBuffer();
			query.append("UPDATE 					");
			query.append("	students		 		");
			query.append("SET 						");
			query.append("	 stu_password = ?		");
			query.append("WHERE 1 = 1			");
			query.append("	AND stu_id = ?		");
			
			ps = conn.prepareStatement(query.toString());
			
			int idx = 1;
			ps.setString(idx++, "123451");
			ps.setString(idx++, "dsl777");
			
			// insert, delete, update 문은
			// ps.executeUpdate() 로 실행하며
			// 결과는 int 타입으로 업데이트된 행의 개수를 리턴
			
			int cnt = ps.executeUpdate();
			
			if(cnt>0) {
				System.out.println(cnt + "행이 업데이트 되었습니다.");
			}else {
				System.out.println("문제가 발생했습니다.");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
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
		}
		
		
	}

}
