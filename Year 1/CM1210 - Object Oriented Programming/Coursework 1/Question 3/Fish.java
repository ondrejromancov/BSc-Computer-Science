//Ondrej Romancov
//1731713

public class Fish extends Pet{

	private String water_type;

	public Fish(String name, String color, String habitat, String[] diet, int max_age, int age, String water){
		super(name, color, habitat, diet, max_age, age);

		this.water_type = water_type;

	}

	public String getWaterType(){
		return this.water_type;
	}

	public void setWaterType(String water){
		this.water_type = water;
	}
}