package de.tbonsack.karpfediem.utils.gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import org.eclipse.core.runtime.Platform;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.tbonsack.karpfediem.utils.gson.service.LoadService;

public class LoadServiceImpl implements LoadService {

	private String getJson(String path) {
		String dir = Platform.getInstanceLocation().getURL().getPath().substring(1);
		String json = "";
		Path pathToJson = Paths.get(dir + path + ".json");
		try {
			json = Files.readString(pathToJson);
		} catch (IOException e) {
			Platform.getLog(getClass()).error("Can not load: " + path, e);
		}
		return json;
	}

	@Override
	public <E> E loadFromGson(String path, Class<E> objectType) {
		String json = getJson(path);

		E loadedObject = new Gson().fromJson(json, objectType);
		return loadedObject;
	}

	@Override
	public <E> Collection<E> loadFromGsonArray(String path, Class<E> objectType) {
		String json = getJson(path);

		Type typeToken = new TypeToken<Collection<E>>() {
		}.getType();
		Collection<E> loadedColl = new Gson().fromJson(json, typeToken);
		return loadedColl;
	}
}
