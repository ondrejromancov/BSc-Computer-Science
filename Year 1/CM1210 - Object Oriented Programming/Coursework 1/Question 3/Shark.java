//Ondrej Romancov
//1731713

public class Shark extends Fish{

	private int teeth;
	
	public Shark(String name, String color, String habitat, String[] diet, int max_age, int age, String water, int teeth){
		super(name, color, habitat, diet, max_age, age, water); 

		this.teeth = teeth;
	}

	public int getTeeth(){
		return this.teeth;
	}

	public void setTeeth(int teeth){
		this.teeth = teeth;
	}

}