package ch14_jdbc_jsp.model;

public class StudentVO {
	private String stuId;
	private String stuName;
	private String stuPassword;
	private int stuScore;
	
	public StudentVO() {
		
	}
	
	public StudentVO(String stuId, String stuName, String stuPassword, int stuScore) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
		this.stuPassword = stuPassword;
		this.stuScore = stuScore;
	}

	@Override
	public String toString() {
		return "[아이디: " + stuId + ", 이름: " + stuName 
				+ ", 점수: " + stuScore + "]";
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuPassword() {
		return stuPassword;
	}

	public void setStuPassword(String stuPassword) {
		this.stuPassword = stuPassword;
	}

	public int getStuScore() {
		return stuScore;
	}

	public void setStuScore(int stuScore) {
		this.stuScore = stuScore;
	}
	
}
