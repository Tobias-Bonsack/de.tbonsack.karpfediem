package de.tbonsack.karpfediem.utils.gson.service;

import java.util.Collection;

public interface LoadService {

	<E> E loadFromGson(String path, Class<E> objectType);

	<E> Collection<E> loadFromGsonArray(String path, Class<E> objectType);
}
