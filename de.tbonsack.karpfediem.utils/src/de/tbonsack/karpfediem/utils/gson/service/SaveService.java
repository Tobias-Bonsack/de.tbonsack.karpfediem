package de.tbonsack.karpfediem.utils.gson.service;

import java.util.List;

public interface SaveService {

	<E> void safeAsGson(ISerializable object, Class<E> objectType);

	<E> void safeAsGson(List<ISerializable> list, Class<E> objectType);

}
