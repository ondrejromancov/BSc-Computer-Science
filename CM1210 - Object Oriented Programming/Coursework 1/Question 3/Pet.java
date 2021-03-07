//Ondrej Romancov
//1731713

public class Pet{

	private String name;
	private String color;
	private String habitat;
	private String[] diet;
	private int max_age;
	private int age;

	public Pet(String name, String color, String habitat, String[] diet, int max_age, int age){
		this.name = name;
		this.color = color;
		this.habitat = habitat;
		this.diet = diet;
		this.max_age = max_age;
		this.age = age;
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getColor(){
		return this.color;
	}

	public void setColor(String color){
		this.color = color;
	}

	public String getHabitat(){
		return this.habitat;
	}

	public void setHabitat(String habitat){
		this.habitat = habitat;
	}

	public String[] getDiet(){
		return this.diet;
	}

	public void setDiet(String[] name){
		this.diet = diet;
	}

	public int getMaxAge(){
		return this.max_age;
	}

	public void setMaxAge(int name){
		this.max_age = max_age;
	}

	public int getAge(){
		return this.age;
	}

	public void setAge(int name){
		this.age = age;
	}

}