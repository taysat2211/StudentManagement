

public class Student {
	private String name;
	private String address;
	private int year;
	private float point;
	
	
	public Student(String name, String address, int year, float point) {
		super();
		this.name = name;
		this.address = address;
		this.year = year;
		this.point = point;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public float getPoint() {
		return point;
	}
	public void setPoint(float point) {
		this.point = point;
	}
	
	
	
}
