package fr.lirobi.skybattle.game;

public class Kit {

    private SbPlayer owner;
    private String name;

    public Kit(SbPlayer player, String name) {
        // TODO : Charger le kit depuis la database du joueur renseigné pour avoir le placement des items.
    }

    public void giveKit() {
        // TODO : faire une surcharge, si pas de paramètre give défault sinon give celui avec le nom donné. Pas de vérification si le joueur le possède car sinon il ne se serait pas crée

    }

    public void saveKit() {
        // TODO : Sauvegarder le kit dans la database du joueur.
    }

    public void editKit() {
        // TODO : Ouvrir une interface pour éditer le kit.
    }

}
