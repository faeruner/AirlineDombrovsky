package by.pvt.module3.entity;

public class Airline {
	public static final String ID = "id";
	public static final String NAME = "name";

	Integer id;
	String name;

	public Airline() {
	}

	public Airline(String name) {
		this.name = name;
	}

	public Airline(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
