package gui.view;


import gui.model.StudentTableModel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import data.model.Student;

/**
 * @author André PENINOU
 * @author Fabrice PELLEAU
 */

public class StudentListFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	///  fenêtre d'édition d'un Student (créé à la première utilisation)
	private StudentDialog studentDialog = null;

	// TableModel utilisé
	private StudentTableModel studentTableModel = null;

	// =======================================================================
	// Attributs graphiques (gérés par V.E.)
	// =======================================================================
	private JPanel    jContentPane = null;
	private JPanel    topPanel = null;
	private JButton   butAjout = null;
	private JButton   butSuppr = null;
	private JButton   butModif = null;
	private JScrollPane jScrollPaneTable = null;

	private JTable studentTable = null;
	
	private JTable studentTableDeuxieme = null;
	private JScrollPane jScrollPaneTableDeuxieme = null;

	/**
	 * This is the default constructor
	 */
	public StudentListFrame() {
		super();
		this.initialize();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.studentTableModel = new StudentTableModel();;
		this.studentTable.setModel(this.studentTableModel);
		this.studentTableDeuxieme.setModel(this.studentTableModel);
	}

	/**
	 * Variante du constructeur avec initialisation de la liste des students à traiter
	 */
	public StudentListFrame(List<Student> listeStudentsInitiale) {
		super();
		this.initialize();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.studentTableModel = new StudentTableModel();;
		this.studentTableModel.loadDatas(listeStudentsInitiale);
		this.studentTable.setModel(this.studentTableModel);
		this.studentTableDeuxieme.setModel(studentTableModel);
	}

	// =======================================================================
	// Initialisation et accesseur des composants graphiques (géré par V.E.)
	// =======================================================================

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {

		this.studentDialog = new StudentDialog(this);

		this.setSize(300, 200);
		this.setContentPane(this.getJContentPane());
		this.setTitle("Gestion des Students");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return JPanel
	 */
	private JPanel getJContentPane() {

		if (this.jContentPane == null) {

			// Panel du haut et ses boutons 

			this.jContentPane = new JPanel();
			this.jContentPane.setLayout(new BorderLayout());

			this.butAjout = new JButton();
			this.butAjout.setText("Ajouter");
			this.butAjout.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					StudentListFrame.this.ajoutStudent();
				}
			});

			this.butSuppr = new JButton();
			this.butSuppr.setText("Supprimer");
			this.butSuppr.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					StudentListFrame.this.supprStudent();
				}
			});

			this.butModif = new JButton();
			this.butModif.setText("Modifier");
			this.butModif.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					StudentListFrame.this.modifStudent();
				}
			});

			this.topPanel = new JPanel();
			this.topPanel.setLayout(new BorderLayout());
			this.topPanel.add(this.butAjout, BorderLayout.WEST);
			this.topPanel.add(this.butSuppr, BorderLayout.EAST);
			this.topPanel.add(this.butModif, BorderLayout.CENTER);

			// JTable et JScrollpane qui la contient

			this.studentTable = new JTable();
			this.studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

			this.studentTableDeuxieme = new JTable();
			this.studentTableDeuxieme.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			this.studentTableDeuxieme.setEnabled(false);
			
			this.jScrollPaneTable = new JScrollPane();
			this.jScrollPaneTable.setViewportView(this.studentTable);
			this.jScrollPaneTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			this.jScrollPaneTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			
			this.jScrollPaneTableDeuxieme = new JScrollPane();
			this.jScrollPaneTableDeuxieme.setViewportView(this.studentTableDeuxieme);
			this.jScrollPaneTableDeuxieme.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			this.jScrollPaneTableDeuxieme.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

			// Fenêtre de l'application 

			this.jContentPane.add(this.topPanel, BorderLayout.NORTH);
	        this.jContentPane.add(this.jScrollPaneTable, BorderLayout.WEST);
	        this.jContentPane.add(this.jScrollPaneTableDeuxieme, BorderLayout.EAST);

		}
		return this.jContentPane;
	}

	// =======================================================================
	// Méthodes internes liées aux fonctionnalités du composant
	// =======================================================================


	/**
	 * Ajout d'un nouveau Student dans la base de données
	 */
	private void ajoutStudent() {

		Student stud = new Student();

		boolean  popupResult;

		popupResult = this.studentDialog.ouvrirDialogue( stud, StudentDialog.ModeEdition.CREATION ); 

		if (popupResult) {
			studentTableModel.fireTableDataChanged();
			studentTableModel.addStudent(stud);
		} else {
			JOptionPane.showMessageDialog(this,"ok on n'ajoute rien");
		}
	}

	/**
	 * Modification d'un student de la base de données
	 */
	private void modifStudent() {

		int selectedRow = this.studentTable.getSelectedRow();

		if ( selectedRow >= 0 ) {
			Student stud = this.studentTableModel.getStudentAt( selectedRow );
			if ( stud != null ) {

				boolean  popupResult;

				// Création ou réutilisation de la boite de dialog "studentDialog"
				// ouverture en mode "ajout"
				// récupération du code de retour (true/false) et des données saisies (stud) 
				popupResult = this.studentDialog.ouvrirDialogue( stud, StudentDialog.ModeEdition.MODIFICATION ); 

				if (popupResult) {
					this.studentTableModel.fireTableDataChanged();
					this.studentTableModel.updateStudentAt(stud, selectedRow);
				} else {
					// action non validée
					JOptionPane.showMessageDialog(this,"ok on ne modifie rien");
				}
			}
		} else {
			JOptionPane.showMessageDialog(this,"Veuillez sélectionner la ligne à modifier");
		}
	}

	/**
	 * Suppression d'un student de la base de données
	 */
	private void supprStudent() {

		int selectedRow = this.studentTable.getSelectedRow();

		if ( selectedRow >= 0 ) {
			Student stud = this.studentTableModel.getStudentAt( selectedRow );
			if ( stud != null ) {

				boolean  popupResult;

				popupResult = this.studentDialog.ouvrirDialogue( stud, StudentDialog.ModeEdition.SUPPRESSION ); 

				if ( popupResult) {
					this.studentTableModel.fireTableDataChanged();
					this.studentTableModel.removeStudentAt(selectedRow);
				} else {
					JOptionPane.showMessageDialog(this,"ok on ne supprime rien");
				}
			}
		} else {
			JOptionPane.showMessageDialog(this,"Veuillez sélectionner la ligne à modifier");
		}
	}	

}