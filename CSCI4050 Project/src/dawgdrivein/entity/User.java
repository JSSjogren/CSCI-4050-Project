package dawgdrivein.entity;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Transient;

import dawgdrivein.db.UserDBA;

@MappedSuperclass
@Table(name = "User")
public class User implements Serializable {

	//Should allow us to auto-increment the User ID's
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserId")
	protected int id;
	
	@Column(name = "FirstName")
	protected String firstName;
	@Column(name = "LastName")
	protected String lastName;
	@Column(name = "Email")
	protected String email;
	@Column(name = "Password")
	protected String password;
	@Column(name = "Status")
	protected int status;
	@Column(name = "TypeId")
	protected int rank;
	@Column(name = "SubPref")
	protected boolean sub_pref;
	@Transient
	protected UserDBA userDBA;
	
	public User(int id,  String firstName, String lastName, String email, String password,  int rank, int status, boolean sub_pref)
	{
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.status = status;
		this.rank = rank;
		this.sub_pref = sub_pref;
		
		userDBA = new UserDBA();
	}
	
	public User()
	{
		this.id = -1;
		this.email = null;
		this.password = null;
		this.firstName = null;
		this.lastName = null;
		this.status = -1;
		this.rank = -1;
		this.sub_pref = false;
		
		userDBA = new UserDBA();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public boolean isSub_pref() {
		return sub_pref;
	}

	public void setSub_pref(boolean sub_pref) {
		this.sub_pref = sub_pref;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", status=" + status + ", rank=" + rank + ", sub_pref=" + sub_pref + "]";
	}
	
	/**
	 * Turns the user's password into a hashValue
	 * 
	 * @param input the user's password
	 * @return the hashed version of the user's password
	 */
	protected static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' ,'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			// handle error here.
		}

		return hash.toString();
	}
	
	/**
	 * Checks to see if passed in user information
	 * can be validated for login.
	 * 
	 * @param email the user's primary key
	 * @param password the user's password
	 * @return validation success/failure
	 */
	public User validate(String email, String password)
	{
		return (User) userDBA.validateUser(email, generateHash(password));
	}
	
	public boolean setTemporaryPassword(String email, String temporaryPassword)
	{
		return userDBA.setTemporaryPassword(email, generateHash(temporaryPassword));
	}

	public boolean isTemporary()
	{
		return userDBA.isTemporary(id);
	}
	
	public void changePassword(String password, String confirmPassword)
	{
		if (password.equals(confirmPassword))
			userDBA.changePassword(getId(), generateHash(password));
	}
}

