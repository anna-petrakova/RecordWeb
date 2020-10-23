package common;

public enum Gender {
	MALE("Male"), FEMALE("Female");
	
	private String label;
	
	private Gender(String str) {
		this.label = str;
	}
	
	public String toString() {
		return this.label;
	}

}
