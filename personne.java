package personne;

public class personne {
		private int CIN;
		private String nom;
		private String prenom;
		public personne(int CIN,String nom,String prenom) {
			this.CIN=CIN;
			this.nom=nom;
			this.prenom=prenom;
		}
		public void setNom(String nom) {this.nom=nom;}
		public void setPrenom(String prenom) {this.prenom=prenom;}
		public void setCIN(int CIN) {this.CIN=CIN;}
		public int getCIN() {
			return this.CIN;
		}
		public String getNom() {
			return this.nom;
		}
		public String getPrenom() {
			return this.prenom;
		}
		public String toString() {
			return "CIN:"+getCIN()+" NOM:"+getNom()+" prenom:"+getPrenom();
		}}

abstract class proprieté{
	private int id;
	private personne responsable;
	private String adresse;
	protected double surface;
	
	public proprieté(int id, personne responsable, String adresse, double surface) {
		this.setId(id);
		this.responsable=responsable;
		this.adresse=adresse;
		this.surface=surface;
	}
	public String toString() {
		return "id: "+this.getId()+" responsable:"+super.toString()+" adresse"+this.adresse+" surface:"+this.surface+"m²";
	}
	abstract double  calculImpot() ;

	public personne getResponsable() {
		return responsable;
	}
	public void setResponsable(personne responsable) {
		this.responsable = responsable;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}}
	
class proprietéPrivée extends proprieté{
	protected int nbPieces;
	public proprietéPrivée(int id,int nbPieces, personne responsable, String adresse, double surface) {
		super(id,responsable,adresse,surface);
		this.nbPieces=nbPieces;
	}
	 public String toString() {
	     	return super.toString() + " avecPieces=" +getnbPieces() ;
	     }
	
	double calculImpot() {
		return (50*surface/100)+(10*nbPieces);
	}
	public int getnbPieces() {
		return this.nbPieces;
	}
}

class PropriétéProfessionnelle extends proprieté{
	 int nbEmployés;
	 boolean estEtatique;
	public PropriétéProfessionnelle (int id, int nbEmployés,boolean estEtatique ,personne responsable, String adresse, double surface) {
		super(id,responsable,adresse,surface);
		this.nbEmployés=nbEmployés;
		this.estEtatique=estEtatique;
		
	}
	 public String toString() {
     	return super.toString() + " avecEmployes=" + this.nbEmployés+" estetatique?:"+this.estEtatique;
     }
	
	double calculImpot() {
		if(! estEtatique) {return 100/(100*surface) + 30*nbEmployés;
		
		}else {
		return 0;}
	}
}
class villa  extends proprietéPrivée{
	boolean avecPiscine;
	public villa (int id, boolean avecPiscine, personne responsable, String adresse, double surface,int nbPieces)
	{
		super(id, nbPieces, responsable, adresse, surface);
        this.avecPiscine = avecPiscine;
        }
         public String toString() 
         {
        	return super.toString() + " avecPiscine=" + this.avecPiscine;
        }
          double calculImpot() {
        	 double imp=super.calculImpot();
        	 if(avecPiscine) {
        		 imp+=200;
        	 }
        	 return imp;
        	 
     	}
         
	} 
class appartement  extends proprietéPrivée{
	int numEtage;
	public appartement (int id, int numEtage, personne responsable, String adresse, double surface,int nbPieces)
	{
		super(id, nbPieces, responsable, adresse, surface);
        this.numEtage = numEtage;
        }
         public String toString() 
         {
        	return super.toString() + " avecPiscine=" + this.numEtage;
        }}
class lotissement implements GestionPropriete {
	protected proprieté [] tabProp;
	protected int nombre;
	int capacité;
	public lotissement(int capacité) {
		this.capacité= MAX_PROPRIETES;		
		tabProp=new proprieté[capacité];
		this.nombre=0;
	}
	
	proprieté getpropriétéByIndex(int i) {
		if (i >= 0 && i < nombre) {
            return tabProp[i];
        }
        throw new IndexOutOfBoundsException("erreur" + i);	}
	int getnbPieces() {
		int totpieces=0;   
		for (int i = 0; i < nombre; i++) { 
			if(tabProp[i] instanceof proprietéPrivée) {
				proprietéPrivée pp = (proprietéPrivée) tabProp[i];
				totpieces +=pp.nbPieces;
			}
		}
		return totpieces;
	}
	public void afficherPropriétés() {
		for (int i = 0; i < nombre; i++) {
            System.out.println(tabProp[i].toString());
            System.out.println("Impots à payer : " + tabProp[i].calculImpot() + " DT");
        }
	}
	
	public boolean ajouter(proprieté p) {
		 if (nombre < tabProp.length) {
	            for (int i = 0; i < nombre; i++) {
	                if (tabProp[i].getId() ==p.getId()) {
	                    return false; 
	                }
	            }
	            tabProp[nombre++] = p;
	            return true;
	        }
	        return false;
	}
	
	public boolean supprimer(proprieté p) {
		for (int i = 0; i < nombre; i++) {
            if (tabProp[i].equals(p)) {
                for (int j = i; j < nombre - 1; j++) {
                    tabProp[j] = tabProp[j + 1];
                }
                tabProp[--nombre] = null; 
                return true;
            }
        }
        return false;
	}
	public proprietéPrivée getPropriétéPrivéeAvecMoinsImpots() {
		proprietéPrivée minPropriété = null;
        double minImpots =tabProp[0].calculImpot();

        for (int i = 1; i < nombre; i++) {
            if (tabProp[i] instanceof proprietéPrivée) {
            	proprietéPrivée prop = (proprietéPrivée) tabProp[i];
                double imp = prop.calculImpot();
                if (imp < minImpots) {
                    minImpots = imp;
                    minPropriété = prop;
                }
            }
        }
        return minPropriété;
    }
}
class lotissementPrivee extends lotissement {
    
    public lotissementPrivee(int capacité) {
        super(capacité); 
    }

   
    public boolean ajouter(proprieté p) {
        if (p instanceof proprietéPrivée) {
            return ajouter(p); 
        }
        return false;
    }

   
    public proprietéPrivée getpropriétéByIndex(int i) {
        if (i >= 0 && i < nombre && tabProp[i] instanceof proprietéPrivée) {
            return (proprietéPrivée) tabProp[i]; 
        }
        return null;
    }

  
    public int getnbPièces() {
        int totalPieces = 0;
        for (int i = 0; i < nombre; i++) {
            if (tabProp[i] instanceof proprietéPrivée) {
                totalPieces += ((proprietéPrivée) tabProp[i]).getnbPieces();
            }
        }
        return totalPieces;
    }
}




	
