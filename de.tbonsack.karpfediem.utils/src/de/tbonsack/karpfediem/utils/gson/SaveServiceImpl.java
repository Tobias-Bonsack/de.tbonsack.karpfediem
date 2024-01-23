package de.tbonsack.karpfediem.utils.gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.osgi.service.component.annotations.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import de.tbonsack.karpfediem.utils.gson.service.ISerializable;
import de.tbonsack.karpfediem.utils.gson.service.SaveService;

@Component
public class SaveServiceImpl implements SaveService {

	@Override
	public <E> void safeAsGson(Collection<ISerializable> list, Class<E> objectType) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		var type = new TypeToken<List<E>>() {
		}.getType();
		String json = gson.toJson(list, type);

		String dir = Platform.getInstanceLocation().getURL().getPath().substring(1);
		String pathName = list.iterator().next().getPathName();
		String fileName = list.iterator().next().getFileName();
		Path path = Paths.get(dir + File.separator + pathName + fileName + ".json");
		try {
			Files.write(path, json.getBytes(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			Platform.getLog(getClass()).error("Unable to Safe: " + path.toString(), e);
		}
	}

	@Override
	public <E> void safeAsGson(ISerializable object, Class<E> objectType) {
		Gson gson = new Gson();

		var type = TypeToken.getParameterized(objectType).getType();
		String json = gson.toJson(object, type);

		String dir = Platform.getInstanceLocation().getURL().getPath().substring(1);
		String pathName = object.getPathName();
		String fileName = object.getFileName();
		Path path = Paths.get(dir + File.separator + pathName + fileName + ".json");
		try {
			Files.write(path, json.getBytes(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			Platform.getLog(getClass()).error("Unable to Safe: " + path.toString(), e);
		}
	}
}
