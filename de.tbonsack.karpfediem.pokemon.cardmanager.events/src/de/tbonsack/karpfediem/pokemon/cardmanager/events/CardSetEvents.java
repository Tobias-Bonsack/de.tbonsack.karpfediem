package de.tbonsack.karpfediem.pokemon.cardmanager.events;

/**
 * @noimplement This interface is not intenden to be implemented by clients
 *
 *              Only used for constant definition
 */
public interface CardSetEvents {

	String cardset = "cardset";

	/**
	 * Only for registration, no event send possible
	 */
	String cardset_all = "cardset/*";

	String cardset_select = "cardset/select";
}
