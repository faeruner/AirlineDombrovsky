package by.pvt.module3.entity;

import java.io.Serializable;

@Entity
public class Airline implements Serializable {
    public static final String ID = "id";
    public static final String NAME = "name";

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String name;

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
