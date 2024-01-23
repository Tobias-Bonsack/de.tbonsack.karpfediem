package de.tbonsack.karpfediem.pokemon.cardmanager.model.objects;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CardSet extends ABasicCardObject {

	public static final String FILE = "cardsets";

	public static final String PATH = "pokemon/cardmanager/";

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
		return FILE;
	}

	@Override
	public String getPathName() {
		return PATH;
	}

	public void setCards(Set<Integer> cards) {
		_cards = cards;
	}

}
