package data;

public class Song {
	public Song(String name, String path,String duration,int num) {
		this.name = name;
		this.path = path;
		this.duration=duration;
		this.num=num;
	}
	private String name;
	private String path;
	private String duration;
	private int num; 
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
