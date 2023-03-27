package gui.model;


import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import data.model.Student;

/**
 * @author André PENINOU
 * @author Fabrice PELLEAU
 */

public class StudentTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<Student> studentlist = null;

	/**
	 * Constructeur.
	 */ 
	public StudentTableModel(  ) {
		this.studentlist = new ArrayList<>();
	}

	// =======================================================================
	// Surcharges des méthodes abstraites de la classe AbstractTableModel
	// =======================================================================

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		int nbRow = 0;
		if (this.studentlist!=null) {
			nbRow = this.studentlist.size();
		}
		return nbRow;
	}

	@Override
	public Object getValueAt(int lig, int col) {
		if (this.studentlist==null) { return "!!!"; }

		switch (col) {
		case 0:
			return ""+this.studentlist.get(lig).id;
		case 1:
			return this.studentlist.get(lig).surname;
		case 2:
			return this.studentlist.get(lig).firstname;
		case 3:
			return ""+this.studentlist.get(lig).TPgroup;
		default:
			return "???";
		}

	}

	@Override
	public String getColumnName(int column) {
		String result = "";
		String noms[] = {"Id", "Nom", "Prénom", "Groupe"};
		for(int i = 0; i <= column; i++) {
			result = noms[i];
		}
		return result;
	}



	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(rowIndex >= 0 && rowIndex < getRowCount() && columnIndex >= 1 && columnIndex < getColumnCount() ) {
			return true;
		}
		return super.isCellEditable(rowIndex, columnIndex);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if(columnIndex == 3) {
			if(aValue.toString().equals("1A") || aValue.toString().equals("1B") ||
					aValue.toString().equals("2A") || aValue.toString().equals("2B") ||
					aValue.toString().equals("3A") || aValue.toString().equals("3B") ||
					aValue.toString().equals("4A") || aValue.toString().equals("4B")){
				this.studentlist.get(rowIndex).TPgroup = aValue.toString();
				this.fireTableDataChanged();
			}
			else if(aValue.toString().equals("1a") || aValue.toString().equals("1b") ||
					aValue.toString().equals("2a") || aValue.toString().equals("2b") ||
					aValue.toString().equals("3a") || aValue.toString().equals("3b") ||
					aValue.toString().equals("4a") || aValue.toString().equals("4b")) {
				this.studentlist.get(rowIndex).TPgroup = aValue.toString().toUpperCase();
				this.fireTableDataChanged();
			}
			else {}
		}
		if(!(aValue.toString().compareTo("") == 0 && (columnIndex == 1 || columnIndex == 2))) {
			switch(columnIndex) {
			case(1):
			this.studentlist.get(rowIndex).surname = aValue.toString();
			this.fireTableDataChanged();
			break;
			case(2):
			this.studentlist.get(rowIndex).firstname = aValue.toString();
			this.fireTableDataChanged();
			break;
			}
		}
	}

	// =======================================================================
	// Méthodes spécifiques à la classe StudentTableModel
	// =======================================================================
	/**
	 * Lecture (ou relecture forcée) des données dans la base
	 * 
	 * @param listeDonnees	  ArrayList contenant les students à présenter dans la table
	 */
	public void loadDatas( List<Student> listeDonnees ) {
		this.studentlist = listeDonnees;
		this.fireTableDataChanged();
	}


	/**
	 * Retourner la copie d'un élément de type Student représentant l'enregistrement de la ligne "lig".
	 * 
	 * @param lig numéro de la ligne (dans la table).
	 * 
	 * @return l'élément concerné ou NULL en cas d'erreur
	 */
	public Student getStudentAt(int lig) {
		Student stud = null;
		if ( this.studentlist != null ) {
			if (this.studentlist.size()>lig) {
				stud = new Student(this.studentlist.get(lig));
			}
		}
		return stud;
	}

	/**
	 * Met à jour l'étudiant à la ligne sélectionné
	 * @param studentUpdated Le nouvel étudiant modifié
	 * @param lig la ligne sélectionné
	 */
	public void updateStudentAt(Student studentUpdated, int lig) {
		this.studentlist.set(lig, studentUpdated);
	}

	/**
	 * Ajoute un étudiant dans la liste
	 * @param stud le student à ajouter
	 */
	public void addStudent(Student stud) {
		this.studentlist.add(stud);
	}

	/**
	 * Enlève un étudiant de la liste
	 * @param lig la ligne où se situe l'étudiant
	 */
	public void removeStudentAt(int lig) {
		this.studentlist.remove(lig);
	}

}
