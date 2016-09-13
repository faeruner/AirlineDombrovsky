package by.pvt.module3.entity;

public class Airport {
	public static final String ID = "id";
	public static final String NAME = "name";

	private Integer id;
	private String name;

	public Airport() {
	}

	public Airport(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Airport(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
