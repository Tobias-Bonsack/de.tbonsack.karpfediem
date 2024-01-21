package de.tbonsack.karpfediem.utils.gson.service;

import java.util.List;

public interface SaveService {

	<E> void safeAsGson(ASerializable object, Class<E> objectType);

	<E> void safeAsGson(List<ASerializable> list, Class<E> objectType);

}
