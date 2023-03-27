package gui.view;


import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.SwingConstants;

import data.model.Student;

/**
 * Boite de dialogue utilisée créer ou modifier une donnée de type Student.
 * 
 * La boite de dialogue est activée par la méthode publique "ouvrirDialog".
 * Il existe 3 modes de fonctionnement distincts (Cf. enumeration "ModeEdition").
 * 
 * @author Fabrice PELLEAU
 */

public class StudentDialog extends JDialog {
	
	public enum ModeEdition {
		CREATION, MODIFICATION, SUPPRESSION
	};

	private Student student = null;
	private boolean etatValidation = false;
	private ModeEdition modeActuel;

	private static final long serialVersionUID = 1L;

	// =======================================================================
	// Attributs graphiques (gérés par V.E.)
	// =======================================================================
	
	private JPanel jContentPane = null;
	private JPanel panGauche = null;
	private JPanel panCentre = null;
	private JLabel labID = null;
	private JLabel lanName = null;
	private JLabel labFirstname = null;
	private JLabel labGroup = null;
	private JTextField txtID = null;
	private JTextField txtName = null;
	private JTextField txtFirstname = null;
	private JTextField txtGroup = null;
	private JPanel panBas = null;
	private JButton butValider = null;
	private JButton butAnnuler = null;
	private JLabel labMessage = null;

	// =======================================================================
	// Méthodes publiques
	// =======================================================================
	
	/**
	 * Constructeur permettant l'association de la JDialog avec la fenêtre appelante.
	 * 
	 * @param owner fenetre appellante.
	 */
	public StudentDialog(Frame owner) {
		super(owner);
		this.initialize();
	}
	
	/**
     * Ouverture de la boite de dialogue.
	 *
	 * @param stud  Objet de type Student à éditer (jamais null).
	 * @param mode  Mode d'ouverture (CREATION, MODIFICATION, SUPPRESSION)
	 * @return true si l'action est validée / false sinon
	 */
	public boolean ouvrirDialogue(Student stud, StudentDialog.ModeEdition mode) {
		this.modeActuel = mode;
		switch (mode) {
		case CREATION:
			stud.id=0;
			stud.surname = "";
			stud.firstname = "";
			stud.TPgroup="";
			this.student = stud;
			this.txtID.setEnabled(false);
			this.txtName.setEnabled(true);
			this.txtFirstname.setEnabled(true);
			this.txtGroup.setEnabled(true);
			this.labMessage.setText("Description du Student à créer");
			this.butValider.setText("Ajouter");
			this.butAnnuler.setText("Annuler");
			break;
		case MODIFICATION:
			this.student = stud;
			this.txtID.setEnabled(false);
			this.txtName.setEnabled(true);
			this.txtFirstname.setEnabled(true);
			this.txtGroup.setEnabled(true);
			this.labMessage.setText("Student actuellement en base");
			this.butValider.setText("Modifier");
			this.butAnnuler.setText("Annuler");
			break;
		case SUPPRESSION:
			this.student = stud;
			this.txtID.setEnabled(false);
			this.txtName.setEnabled(false);
			this.txtFirstname.setEnabled(false);
			this.txtGroup.setEnabled(false);
			this.labMessage.setText("Voulez-vous réellement supprimer ce Student ?");
			this.butValider.setText("Supprimer");
			this.butAnnuler.setText("Conserver");
			break;
		}
		this.updateFields();
		this.etatValidation  = false;
		this.setModal(true);
		this.setLocationRelativeTo(this.getParent());		
		
		this.setVisible(true);
		
		// le programme appelant est bloqué jusqu'au masquage de la JDialog.
		return this.etatValidation;
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
		this.setSize(386, 200);
		this.setTitle("Gestion d'un Student");
		this.setContentPane(this.getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (this.jContentPane == null) {
			
			// Panel de gauche et ses labels d'affichage
			
			this.labGroup = new JLabel();
			this.labGroup.setText("Groupe TP");
			this.labFirstname = new JLabel();
			this.labFirstname.setText("Prénom");
			this.lanName = new JLabel();
			this.lanName.setText("Nom");
			this.labID = new JLabel();
			this.labID.setText("ID");
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(4);
			this.panGauche = new JPanel();
			this.panGauche.setLayout(gridLayout);
			this.panGauche.add(this.labID, null);
			this.panGauche.add(this.lanName, null);
			this.panGauche.add(this.labFirstname, null);
			this.panGauche.add(this.labGroup, null);

			// Panel du centre et ses champs de saisie

			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(4);
			this.panCentre = new JPanel();
			this.panCentre.setLayout(gridLayout1);
			this.txtID = new JTextField();
			
			this.txtName = new JTextField();
			this.txtName.addFocusListener(new java.awt.event.FocusAdapter() {
				@Override
				public void focusLost(java.awt.event.FocusEvent e) {
					StudentDialog.this.student.surname = StudentDialog.this.txtName.getText().trim();
				}
			});
			
			this.txtFirstname = new JTextField();
			this.txtFirstname.addFocusListener(new java.awt.event.FocusAdapter() {
				@Override
				public void focusLost(java.awt.event.FocusEvent e) {
					StudentDialog.this.student.firstname = StudentDialog.this.txtFirstname.getText().trim();
				}
			});
			
			this.txtGroup = new JTextField();
			this.txtGroup.addFocusListener(new java.awt.event.FocusAdapter() {
				@Override
				public void focusLost(java.awt.event.FocusEvent e) {
					StudentDialog.this.student.TPgroup = StudentDialog.this.txtGroup.getText().trim();
				}
			});
			
			this.panCentre.add(this.txtID, null);
			this.panCentre.add(this.txtName, null);
			this.panCentre.add(this.txtFirstname, null);
			this.panCentre.add(this.txtGroup, null);
			
			// Panel du bas et ses boutons
			
			GridLayout gridLayout2 = new GridLayout();
			gridLayout2.setRows(1);
			gridLayout2.setColumns(2);
			this.panBas = new JPanel();
			this.panBas.setLayout(gridLayout2);
			
			this.butAnnuler = new JButton();
			this.butAnnuler.setText("txt annuler");
			this.butAnnuler.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					StudentDialog.this.actionAnnuler();
				}
			});
			this.butValider = new JButton();
			this.butValider.setText("txt valider");
			this.butValider.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					StudentDialog.this.actionValider();
				}
			});
			this.panBas.add(this.butValider, null);
			this.panBas.add(this.butAnnuler, null);
			
			// Contenu de la fenêtre et messages en haut
			
			this.labMessage = new JLabel();
			this.labMessage.setText("Action à réaliser");
			this.labMessage.setHorizontalTextPosition(SwingConstants.CENTER);
			this.labMessage.setHorizontalAlignment(SwingConstants.CENTER);
			this.labMessage.setPreferredSize(new Dimension(93, 32));
			this.jContentPane = new JPanel();
			this.jContentPane.setLayout(new BorderLayout());
			this.jContentPane.add(this.panGauche, BorderLayout.WEST);
			this.jContentPane.add(this.panCentre, BorderLayout.CENTER);
			this.jContentPane.add(this.labMessage, BorderLayout.NORTH);
			this.jContentPane.add(this.panBas, BorderLayout.SOUTH);
		}
		return this.jContentPane;
	}


	// =======================================================================
	// Méthodes internes liées aux fonctionnalités du composant
	// =======================================================================

	/**
	 * Mise à jour des zones d'édition en fonction de l'objet "Student" actuellement associé.
	 */
	private void updateFields() {
		this.txtID.setText(""+this.student.id);
		this.txtName.setText(this.student.surname);
		this.txtFirstname.setText(this.student.firstname);
		this.txtGroup.setText(this.student.TPgroup);
	}
	/**
	 * Controle de validité sur les champs modifiables.
	 *  - l'attribut "studnt" est automatiquement actualisé (évènements focusLost des textField)
	 *  - la validité de l'age a déjà ete testée : entier d'interval [0..120]
	 *  
	 * @return true si tous les champs sont valides.
	 */
	private boolean isStudentValide() {
		if ( this.student.surname.isEmpty() ) {
	    	JOptionPane.showMessageDialog(this, "Le nom ne doit pas être vide");
	    	this.txtName.requestFocus();
			return false;
		}
		if ( this.student.firstname.isEmpty() ) {
	    	JOptionPane.showMessageDialog(this, "Le prénom ne doit pas être vide");
	    	this.txtFirstname.requestFocus();
			return false;
		}
		if ( this.student.TPgroup.isEmpty() ) {
	    	JOptionPane.showMessageDialog(this, "Le groupe TP ne doit pas être vide");
	    	this.txtGroup.requestFocus();
			return false;
		}
		this.student.TPgroup = this.student.TPgroup.toUpperCase();
		if ( this.student.TPgroup.length() != 2 
				||  ! (""+this.student.TPgroup.charAt(0)).matches("1|2|3|4")
				||  ! (""+this.student.TPgroup.charAt(1)).matches("A|B")
			) {
	    	JOptionPane.showMessageDialog(this, "Groupe TP invalide (1|2|3|4  A|B)");
	    	this.txtGroup.requestFocus();
			return false;
		}

		return true;
	}
	
	/**
	 * mise en oeuvre d'une Validation (en fonction du contexte)
	 *
	 */
	private void actionValider() {
		switch (this.modeActuel) {
		case CREATION:
			if (this.isStudentValide()) {
				this.etatValidation = true;
				this.setVisible(false);
			}
			break;
		case MODIFICATION:
			if (this.isStudentValide()) {
				this.etatValidation = true;
				this.setVisible(false);
			}
			break;
		case SUPPRESSION:
			this.etatValidation = true;
			this.setVisible(false);
			break;
		}
	}
	
	/**
	 * mise en oeuvre d'une Annulation (en fonction du contexte)
	 */
	private void actionAnnuler() {
		this.etatValidation = false;
		this.setVisible(false);
	}
}