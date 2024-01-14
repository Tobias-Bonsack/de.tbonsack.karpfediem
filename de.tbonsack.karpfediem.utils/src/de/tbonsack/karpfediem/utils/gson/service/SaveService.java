package de.tbonsack.karpfediem.utils.gson.service;

import java.util.List;

public interface SaveService<T extends ASerializable> {

	<E> void safeAsGson(List<T> list, Class<E> objectType);

	<E> void safeAsGson(T object, Class<E> objectType);

}
