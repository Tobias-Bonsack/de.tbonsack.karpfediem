package de.tbonsack.karpfediem.pokemon.cardmanager.model.objects;

public abstract class ABasicCardObject {

	private String _name;
	
	private String _imgPath;
	
	protected ABasicCardObject(String name) {
		super();
		_name = name;
	}

	protected ABasicCardObject(String name, String imgPath) {
		super();
		_name = name;
		_imgPath = imgPath;
	}

	public void setImgPath(String imgPath) {
		_imgPath = imgPath;
	}
	
	public String getName() {
		return _name;
	}
	
	public String getImgPath() {
		return _imgPath;
	}
}
