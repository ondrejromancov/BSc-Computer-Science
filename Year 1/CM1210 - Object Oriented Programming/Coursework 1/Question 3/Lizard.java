//Ondrej Romancov
//1731713

public class Lizard extends Pet{
	
	private boolean poisonous;
	private boolean can_fly;

	public Lizard(String name, String color, String habitat, String[] diet, int max_age, int age, boolean poisonous, boolean can_fly){
		super(name, color, habitat, diet, max_age, age);

		this.poisonous = poisonous;
		this,can_fly = can_fly;
	}

	public boolean getPoisonous(){
		return this.poisonous;
	}

	public void setPoisonou(boolean poisonous){
		this.poisonous = poisonous;
	}

	public boolean getCanFly(){
		return this.can_fly;
	}

	public void setCanFly(boolean can_fly){
		this.can_fly = can_fly;
	}
}