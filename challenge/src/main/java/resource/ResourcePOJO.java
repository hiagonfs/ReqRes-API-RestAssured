package resource;

public class ResourcePOJO {
	
	private int id;
	private String name;
	private int year;
	private String color;
	private String pantone_value;
	
	public ResourcePOJO(int id, String name, int year, String color, String pantone_value) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.color = color;
		this.pantone_value = pantone_value;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getYear() {
		return year;
	}

	public String getColor() {
		return color;
	}

	public String getPantone_value() {
		return pantone_value;
	}

}
