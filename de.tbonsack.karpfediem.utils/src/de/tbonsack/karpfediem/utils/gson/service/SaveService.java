package de.tbonsack.karpfediem.utils.gson.service;

import java.util.List;

public interface SaveService<T extends ASerializable> {

	void safeAsGson(List<T> list);

	void safeAsGson(T object);

}
