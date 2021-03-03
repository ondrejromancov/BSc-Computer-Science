//Ondrej Romancov
//1731713

public class Tetra extends Fish{

	private int group_size;
	
	public Tetra(String name, String color, String habitat, String[] diet, int max_age, int age, String water, int group){
		super(name, color, habitat, diet, max_age, age, water); 

		this.group_size = group;
	}

	public int getGroupSize(){
		return this.group_size;
	}

	public void setGroupSize(int group){
		this.group_size = group;
	}

}