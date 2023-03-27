package data.loader;


import java.util.ArrayList;
import java.util.List;

import data.model.Student;

public class DataLoader {

	// Méthode de chargement de données à partir d'un fichier
	
	public static List<Student> loadDataFromFile(String fileName, String path) {

		// Ici : on fait seulement un bouchon ...
		
		ArrayList<Student> listStudentsTest = new ArrayList<Student>();
		
		listStudentsTest.add(new Student(1, "Laporte", "Sonia", "1A"));
		listStudentsTest.add(new Student(2, "Monte Le Poil", "Geoffroy", "2B"));
		listStudentsTest.add(new Student(3, "Deussan", "Anne-Alice", "3B"));
		listStudentsTest.add(new Student(4, "Pavumirza", "Xavier", "1B"));
		listStudentsTest.add(new Student(5, "Etronbiendemain", "Yves", "3A"));
		listStudentsTest.add(new Student(6, "Airien Kompry", "Johnny", "4B"));
		listStudentsTest.add(new Student(7, "Ductonbai Kanleutrin Passdessu Yoraimilmor", "Sylvia", "2A"));
		listStudentsTest.add(new Student(8, "Yala Couvertur Kigratt", "Sandra", "4A"));
		
		return listStudentsTest;
	}
}
