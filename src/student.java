
public class student {
	private String sid;
	private String sname;
	private double chinese;
	private double math;
	private double english;
	private String password="123456";
	private String ident="stu";
	
	public student(String sid,String sname,double chinese,double math,double english) {
		this.sid=sid;
		this.sname=sname;
		this.chinese=chinese;
		this.math=math;
		this.english=english;
	}
	public String getIdent() {
		return ident; 
	}
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public double getChinese() {
		return chinese;
	}

	public void setChinese(double chinese) {
		this.chinese = chinese;
	}

	public double getMath() {
		return math;
	}

	public void setMath(double math) {
		this.math = math;
	}

	public double getEnglish() {
		return english;
	}

	public void setEnglish(double english) {
		this.english = english;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
