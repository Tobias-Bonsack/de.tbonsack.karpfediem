package de.tbonsack.karpfediem.pokemon.cardmanager.model.objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Card extends ABasicCardObject {

	private int _number = -1;

	private boolean _isOwned = false;

	private Date _ownedSince;
	
	private List<CardSet> _containedIn = new ArrayList<CardSet>();

	public Card(String name, int number, String imgPath, boolean isOwned, Date ownedSince) {
		super(name, imgPath);
		_number = number;
		_isOwned = isOwned;
		_ownedSince = ownedSince;
	}

	public Card(String name, int number) {
		super(name);
		_number = number;
	}

	public void setContainedIn(List<CardSet> containedIn) {
		_containedIn = containedIn;
	}

	public void setOwned(boolean isOwned) {
		_isOwned = isOwned;
	}

	public void setOwnedSince(Date ownedSince) {
		_ownedSince = ownedSince;
	}

	public boolean isOwned() {
		return _isOwned;
	}

	public Date getOwnedSince() {
		return _ownedSince;
	}

	public int getNumber() {
		return _number;
	}

	public List<CardSet> getContainedIn() {
		return _containedIn;
	}
}
