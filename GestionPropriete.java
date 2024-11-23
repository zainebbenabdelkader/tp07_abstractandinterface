package personne;

public interface GestionPropriete {
int MAX_PROPRIETES = 100;
void afficherPropriétés();
boolean ajouter(proprieté p);
boolean supprimer(proprieté p);
}
