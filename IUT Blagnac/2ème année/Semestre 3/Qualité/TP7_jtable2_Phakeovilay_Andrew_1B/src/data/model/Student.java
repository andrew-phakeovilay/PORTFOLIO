package data.model;


/**
 * Données liées à un étudiant.
 */ 


public class Student {
	public int    id;
	public String surname;
	public String firstname;
	public String TPgroup;

	public Student() {
		this.id = 0;
		this.surname = this.firstname = this.TPgroup = null;
	}

	public Student(int id, String nom, String prenom, String TPgroup) {
		this.id     = id;
		this.surname    = nom;
		this.firstname = prenom;
		this.TPgroup    = TPgroup;
	}

	public Student( Student stud ) {
		this.id     = stud.id;
		this.surname    = stud.surname;
		this.firstname = stud.firstname;
		this.TPgroup    = stud.TPgroup;
	}
}
