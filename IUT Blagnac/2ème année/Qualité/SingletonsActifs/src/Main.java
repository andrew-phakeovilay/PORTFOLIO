public class Main {
	public static void main(String[] args) {
		CompteBancaire cb1 = new CompteBancaire("123456789");
		cb1.deposerArgent(100);
		cb1.retirerArgent(80);

		CompteBancaire cb2 = new CompteBancaire("987654321");
		cb2.retirerArgent(10);
		cb2.deposerArgent(50);
		cb2.retirerArgent(100);

		cb1.retirerArgent(20);
		cb1.retirerArgent(100);
		cb1.deposerArgent(-100);
		cb1.retirerArgent(-100);
		
		Journalisation j = Journalisation.getInstance(TypesOperation.operation);
		Journalisation j2 = Journalisation.getInstance(TypesOperation.erreur);
		System.out.println(j.getLog());
		System.out.println("\n" + j2.getLog());
		

		
		
	}
}