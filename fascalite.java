package personne;

public class fascalite {
	public static void main(String[] args) {
	personne p1= new personne(1,"zaineb","bae");
	personne p2= new personne(1,"mido","bae");
	personne p3= new personne(1,"ahmed","bae");
	proprieté[] tabProp=new proprieté[10];
	lotissement lot= new lotissement(15);
	lot.ajouter(new proprietéPrivée(1,10,p1,"Corniche",350));
    lot.ajouter(new villa(2,true, p2, "Dar Chaabane", 400.6,10));
    lot.ajouter(new appartement(3,3,p1, "Hammamet", 1200.8,10));
    lot.ajouter(new PropriétéProfessionnelle(4,5,true, p3, "korba", 1000.50));
    lot.ajouter(new PropriétéProfessionnelle(5,5,false, p2, "BirBouragba", 2500.400));
    System.out.println("les prop: ");
    lot.afficherPropriétés();
    double nbpieces=lot.getnbPieces();
    System.out.println("nombre global:"+nbpieces);
   try { proprietéPrivée min= lot.getPropriétéPrivéeAvecMoinsImpots();
    System.out.println("\nproprietéPrivée moins impots: ");
    System.out.println("proprietaire: " + min.getResponsable());} catch(NullPointerException e) {
    	System.out.println("empty");
    }
    System.out.println("\nsuprimer Hammamet: ");
    try{boolean sup = lot.supprimer(new appartement(3,3,p1, "Hammamet", 1200.8,10));
    System.out.println("supprimé");
    } catch(NullPointerException e) {
        System.out.println("n'existe pas");
    }
    lotissement lt = new lotissementPrivee(10);
	System.out.println("\nProprietés lotissement privé :");
    lt.afficherPropriétés();
    double nbpiecelt=lt.getnbPieces();
    System.out.println("nombre global:"+nbpiecelt);
    System.out.println("\npour index 1:");
    try{proprietéPrivée prop = ((lotissementPrivee) lt).getpropriétéByIndex(1);
    System.out.println(prop.toString());}catch(NullPointerException e) {
    	System.out.println("empty");
    }
    
    }}
