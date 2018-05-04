import java.io.Serializable;

public class Message implements Serializable {
	
	private String srcUser;
	private String dstUser;
	
	public Message(String srcUser, String dstUser) {
		super();
		this.srcUser = srcUser;
		this.dstUser = dstUser;
	}

	public String getSrcUser() {
		return srcUser;
	}

	public void setSrcUser(String srcUser) {
		this.srcUser = srcUser;
	}

	public String getDstUser() {
		return dstUser;
	}

	public void setDstUser(String dstUser) {
		this.dstUser = dstUser;
	}
}

class LoginMessage extends Message{
	private String password;
	private boolean isUserExist = false;
	private boolean isCorrectPwd = false;
	public LoginMessage(String srcUser, String password) {
		super(srcUser, null);
		this.password = password;
	}

	public boolean isUserExist() {
		return isUserExist;
	}

	public void setUserExist(boolean isUserExist) {
		this.isUserExist = isUserExist;
	}


	public boolean isCorrectPwd() {
		return isCorrectPwd;
	}


	public void setCorrectPwd(boolean isCorrectPwd) {
		this.isCorrectPwd = isCorrectPwd;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

class RegistryMessage extends Message{
	private String password;
	private String telePhone;
	private boolean isRegistrySucccess = false;
	public RegistryMessage(String srcUser, String password, String telePhone) {
		super(srcUser, null);
		this.password = password;
		this.telePhone = telePhone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTelePhone() {
		return telePhone;
	}
	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}
	public boolean isRegistrySucccess() {
		return isRegistrySucccess;
	}
	public void setRegistrySucccess(boolean isRegistrySucccess) {
		this.isRegistrySucccess = isRegistrySucccess;
	}
	
}

class ExpressionMessage extends Message{
	
	private String expression;
	public ExpressionMessage(String srcUser, String dstUser,String expression) {
		super(srcUser, dstUser);
		this.expression =  expression;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	
}
