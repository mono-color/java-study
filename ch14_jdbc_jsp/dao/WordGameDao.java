package ch14_jdbc_jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ch14_jdbc_jsp.model.WordGameVO;

public class WordGameDao {

	private static WordGameDao instance = new WordGameDao();

	public static WordGameDao getInstance() {
		return instance;
	}

	private WordGameDao() {

	}

	// 단어 목록 조회(SELECT)
	public ArrayList<WordGameVO> getWordList(Connection conn) throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT							");
		query.append("		  words						");
		query.append("FROM								");
		query.append("		wordgame					");

		PreparedStatement ps = conn.prepareStatement(query.toString());

		// 4. 쿼리문 실행과 동시에 결과를
		// ResultSet 객체에 담는다.
		ResultSet rs = ps.executeQuery();

		ArrayList<WordGameVO> result = new ArrayList<>();

		// 5. 실행결과인 rs를 이용하여
		// 데이터 조회
		while (rs.next()) {
			WordGameVO temp = new WordGameVO();
			temp.setWords(rs.getString("words"));
			result.add(temp);
		}

		if (ps != null) ps.close();
		if (rs != null) rs.close();

		return result;
	}

}
