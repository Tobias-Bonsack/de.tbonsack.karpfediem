package de.tbonsack.karpfediem.utils.gson.service;

import java.util.Collection;

public interface SaveService {

	<E> void safeAsGson(Collection<ISerializable> list, Class<E> objectType);

	<E> void safeAsGson(ISerializable object, Class<E> objectType);

}
