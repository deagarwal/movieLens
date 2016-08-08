package movie.lens.data.models;

import movie.lens.data.util.MovieLensConstants;

/**
 * User Model class.
 *
 */
public class User {
    
    private int userId;
    private String name;
    private String gender;
    private int age;
    private String occupation;
    private String pincode;
    
    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}
	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	
	/**
     * @return the pincode
     */
    public String getPincode() {
        return pincode;
    }
    /**
     * @param pincode the pincode to set
     */
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
    
    
    @Override
	public String toString(){
		StringBuilder sB = new StringBuilder();
		sB.append(userId);
		sB.append(MovieLensConstants.COMMA);
		sB.append(name);
		sB.append(MovieLensConstants.COMMA);
		sB.append(age);
		sB.append(MovieLensConstants.COMMA);
		sB.append(gender);
		sB.append(MovieLensConstants.COMMA);
		sB.append(occupation);
		sB.append(MovieLensConstants.COMMA);
        sB.append(pincode);
		return sB.toString();
	}
}
