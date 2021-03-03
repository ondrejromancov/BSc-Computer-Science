//Ondrej Romancov
//1731713

public class StudentRecord{

	private String name;
	private String snumber;
	private String coursename;
	private String courseid;
	private String house;
	private String street;
	private String town;
	private String postcode;

	public void setName(String name){
		this.name = name;
	}

	public void setSNumber(String snumber){
		this.snumber = snumber;
	}

	public void setCourseName(String coursename){
		this.coursename = coursename;
	}

	public String getCourseName(){
		return this.coursename;
	}

	public void setCourseID(String courseid){
		this.courseid = courseid;
	}

	public void setHouse(String house){
		this.house = house;
	}

	public String getHouse(){
		return this.house;
	}

	public void setStreet(String street){
		this.street = street;
	}

	public String getStreet(){
		return this.street;
	}

	public void setTown(String town){
		this.town = town;
	}

	public String getTown(){
		return this.town;
	}

	public void setPostcode(String postcode){
		this.postcode = postcode;
	}

	public String getPostcode(){
		return this.postcode;
	}

	public String toString(){
		String s = name + ", " + snumber + ", " + coursename + ", " + courseid + ", " 
		+ house + ", " + street + ", " + town + ", " + postcode;

		return s;
	}
}