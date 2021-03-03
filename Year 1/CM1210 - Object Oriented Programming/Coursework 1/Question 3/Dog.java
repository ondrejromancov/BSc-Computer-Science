//Ondrej Romancov
//1731713

public class Dog extends Pet{

	private String hair_type;

	public Dog(String name, String color, String habitat, String[] diet, int max_age, int age, String hair){
		super(name, color, habitat, diet, max_age, age);

		this.hair_type = hair;

	}

	public String getHairType(){
		return this.hair_type;
	}

	public void setHairType(String hair){
		this.hair_type = hair;
	}
}