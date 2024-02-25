package de.tbonsack.karpfediem.pokemon.cardmanager.events;

public interface CardEvents {

	String card = "card";

	/**
	 * Only for registration, no event send possible
	 */
	String card_all = "card/*";

	String card_delete = "card/delete";

	String card_select = "card/select";

	String card_update = "card/update";
}
