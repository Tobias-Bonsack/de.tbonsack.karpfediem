package de.tbonsack.karpfediem.pokemon.cardmanager.model.objects;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CardSet extends ABasicCardObject implements Serializable {

	private Set<Integer> _cards = new HashSet<>();

	public CardSet(int id, String name, String imgPath) {
		super(id, name, imgPath);
	}

	public boolean addCard(int card) {
		return _cards.add(card);
	}

	public Set<Integer> getCards() {
		return _cards;
	}

	@Override
	public String getPathName() {
		return getFileName();
	}

	public void setCards(Set<Integer> cards) {
		_cards = cards;
	}

}
