//Ondrej Romancov
//1731713

public class Boxer extends Dog{

	private boolean onguard;

	public Boxer(String name, String color, String habitat, String[] diet, int max_age, int age, String hair, boolean onguard){
		super(name, color, habitat, diet, max_age, age, hair);
		this.onguard = onguard;
	}

	public boolean getOnGuard(){
		return this.onguard;
	}

	public void setOnGuard(boolean onguard){
		this.onguard = onguard;
	}

}

