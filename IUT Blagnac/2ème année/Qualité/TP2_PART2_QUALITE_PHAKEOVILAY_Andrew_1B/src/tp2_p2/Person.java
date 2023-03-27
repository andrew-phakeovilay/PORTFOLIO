package tp2_p2;

import java.util.Objects;

public class Person {
	
	private String lastName;
	private String firstName;
	

	public Person(String _lastName, String _firstName) {
		super();
		this.lastName = _lastName;
		this.firstName = _firstName;
	}


	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}
}
