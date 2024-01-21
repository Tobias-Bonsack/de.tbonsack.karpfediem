package de.tbonsack.karpfediem.pokemon.cardmanager.model.objects;

import de.tbonsack.karpfediem.utils.gson.service.ISerializable;

public abstract class ABasicCardObject implements ISerializable, Cloneable {

	private int _id;

	private String _imgPath;

	private String _name;

	protected ABasicCardObject(int id, String name) {
		super();
		_name = name;
		_id = id;
	}

	protected ABasicCardObject(int id, String name, String imgPath) {
		super();
		_name = name;
		_imgPath = imgPath;
		_id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !getClass().isInstance(obj))
			return false;
		return _id == ((ABasicCardObject) obj).getId();
	}

	public int getId() {
		return _id;
	}

	public String getImgPath() {
		return _imgPath;
	}

	public String getName() {
		return _name;
	}

	@Override
	public int hashCode() {
		return _id;
	}

	/**
	 * Only for gson read! Do not use!
	 */
	@Deprecated
	public void setId(int id) {
		_id = id;
	}

	public void setImgPath(String imgPath) {
		_imgPath = imgPath;
	}

	public void setName(String name) {
		_name = name;
	}

}
