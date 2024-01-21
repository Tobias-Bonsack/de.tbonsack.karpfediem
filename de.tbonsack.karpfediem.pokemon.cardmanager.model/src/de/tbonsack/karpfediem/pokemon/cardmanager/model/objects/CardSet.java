package de.tbonsack.karpfediem.pokemon.cardmanager.model.objects;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CardSet extends ABasicCardObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Set<Integer> _cards = new HashSet<>();

	public CardSet(int id, String name, String imgPath) {
		super(id, name, imgPath);
	}

	public boolean addCard(int card) {
		return _cards.add(card);
	}

	public void addCards(Collection<Integer> cards) {
		cards.stream().forEach(this::addCard);
	}

	@Override
	public Object clone() {
		CardSet cardSet = new CardSet(getId(), getName(), getImgPath());
		cardSet.addCards(this.getCards());
		return cardSet;
	}

	public Set<Integer> getCards() {
		return _cards;
	}

	@Override
	public String getFileName() {
		return "cardsets";
	}

	@Override
	public String getPathName() {
		return "pokemon/cardmanager/";
	}

	public void setCards(Set<Integer> cards) {
		_cards = cards;
	}

}
