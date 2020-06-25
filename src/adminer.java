
public class adminer {
	private String aid;
	private String password="123456";
	private String ident="adm";
	
	public adminer(String aid,String password){
		this.aid=aid;
		this.password=password;
	}
	public String getIdent() {
		return ident;
	}
	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
