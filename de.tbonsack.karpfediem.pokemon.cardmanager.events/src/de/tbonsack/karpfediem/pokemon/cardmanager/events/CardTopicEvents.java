package de.tbonsack.karpfediem.pokemon.cardmanager.events;

public interface CardTopicEvents {

	String card = "card";

	/**
	 * Only for registration, no event send possible
	 */
	String card_all = "card/*";
}
