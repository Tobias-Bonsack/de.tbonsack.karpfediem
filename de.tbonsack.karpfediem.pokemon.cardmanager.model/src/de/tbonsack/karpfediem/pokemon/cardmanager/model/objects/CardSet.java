package de.tbonsack.karpfediem.pokemon.cardmanager.model.objects;

import java.util.HashSet;
import java.util.Set;

public class CardSet extends ABasicCardObject {

	private Set<Card> _cards = new HashSet<>();

	public CardSet(String name, String imgPath) {
		super(name, imgPath);
	}

	public boolean addCard(Card card) {
		return _cards.add(card);
	}

	public Set<Card> getCards() {
		return _cards;
	}

}
