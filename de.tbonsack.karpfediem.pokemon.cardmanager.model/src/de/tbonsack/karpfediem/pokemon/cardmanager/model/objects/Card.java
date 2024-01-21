package de.tbonsack.karpfediem.pokemon.cardmanager.model.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Card extends ABasicCardObject implements Serializable {

	private List<Integer> _containedInCardSet = new ArrayList<>();

	private boolean _isOwned = false;

	private int _number = -1;

	private Date _ownedSince;

	public Card(int id, String name, int number) {
		super(id, name);
		_number = number;
	}

	public Card(int id, String name, int number, String imgPath, boolean isOwned, Date ownedSince) {
		super(id, name, imgPath);
		_number = number;
		_isOwned = isOwned;
		_ownedSince = ownedSince;
	}

	public boolean addToSet(int idFromSet) {
		return _containedInCardSet.add(idFromSet);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	public List<Integer> getContainedInCardSet() {
		return _containedInCardSet;
	}

	public int getNumber() {
		return _number;
	}

	public Date getOwnedSince() {
		return _ownedSince;
	}

	@Override
	public String getPathName() {
		return this.getFileName();
	}

	public boolean isOwned() {
		return _isOwned;
	}

	public void setContainedIn(List<Integer> containedIn) {
		_containedInCardSet = containedIn;
	}

	public void setContainedInCardSet(List<Integer> containedInCardSet) {
		_containedInCardSet = containedInCardSet;
	}

	public void setNumber(int number) {
		_number = number;
	}

	public void setOwned(boolean isOwned) {
		_isOwned = isOwned;
	}

	public void setOwnedSince(Date ownedSince) {
		_ownedSince = ownedSince;
	}
}
