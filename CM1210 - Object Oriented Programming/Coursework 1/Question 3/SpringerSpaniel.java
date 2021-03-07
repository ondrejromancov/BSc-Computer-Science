//Ondrej Romancov
//1731713

public class SpringerSpaniel extends Dog{

	private boolean likes_flushing;
	
	public SpringerSpaniel(String name, String color, String habitat, String[] diet, int max_age, int age, String hair, boolean likes_flushing){
		super(name, color, habitat, diet, max_age, age, hair);
		this.likes_flushing = likes_flushing;
	}

	public boolean getLikesFlushing(){
		return this.likes_flushing;
	}

	public void setOnGuard(boolean likes_flushing){
		this.likes_flushing = likes_flushing;
	}

}