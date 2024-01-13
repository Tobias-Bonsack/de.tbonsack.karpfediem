package de.tbonsack.karpfediem.pokemon.cardmanager.model.objects;

public abstract class ABasicCardObject {

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

	public int getId() {
		return _id;
	}

	public String getImgPath() {
		return _imgPath;
	}

	public String getName() {
		return _name;
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
