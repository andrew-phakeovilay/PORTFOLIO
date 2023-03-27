import gui.view.StudentListFrame;

import java.util.List;

import javax.swing.SwingUtilities;

import data.loader.DataLoader;
import data.model.Student;

public class Main {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				List<Student> listStudentsTest = DataLoader.loadDataFromFile("", "");			
				StudentListFrame slf = new StudentListFrame(listStudentsTest);
				slf.setVisible(true);
			}
		});
	}

}
/**
 * Où (classe, ligne) est déclaré l’attribut JTable affiché à l’écran ?
 * - Classe StudentListFrame ligne 47
 * Où (classe, ligne) est déclaré l’attribut du TableModel utilisé dans cette application ?
 * - Classe StudentListFrame ligne 35
 * Où (classe, ligne) est réellement créé l’objet JTable affiché à l’écran ?
 * - Classe StudentListFrame ligne 141
 * Où (classe, ligne) est réellement créé l’objet TableModel utilisé dans cette application ?
 * - Classe StudentListFrame ligne 57
 * Où (classe, ligne) sont reliés le JTable et le TableModel ?
 * - Classe StudentListFrame ligne 58
 * D’où viennent les données affichées à l’écran (les étudiants) ?
 * - Classe DataLoader
 * Où (classe, ligne) ces données sont réellement "mises" dans la fenêtre ?
 * - Ces données sont mises dans l'ArrayList d'étudiant ligne 17 classe DataLoader
 */