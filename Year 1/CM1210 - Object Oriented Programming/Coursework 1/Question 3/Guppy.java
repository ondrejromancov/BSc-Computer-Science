//Ondrej Romancov
//1731713

public class Guppy extends Fish{

	private String tail_type;
	
	public Guppy(String name, String color, String habitat, String[] diet, int max_age, int age, String water, String tail_type){
		super(name, color, habitat, diet, max_age, age, water); 

		this.tail_type = tail_type;
	}

	public String getTailType(){
		return this.tail_type;
	}

	public void setTailType(String tail_type){
		this.tail_type = tail_type;
	}

}