package de.tbonsack.karpfediem.utils.gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.tbonsack.karpfediem.utils.gson.service.ASerializable;
import de.tbonsack.karpfediem.utils.gson.service.SaveService;

@Component
public class SaveServiceImpl implements SaveService<ASerializable> {

	@Override
	public <E> void safeAsGson(ASerializable object, Class<E> objectType) {
//		String dir = System.getProperty("user.dir");
		String dir = "C:\\others";
		Gson gson = new Gson();

		var type = TypeToken.getParameterized(objectType).getType();
		String json = gson.toJson(object, type);

		try {
			String pathName = object.getPathName();
			Files.write(Paths.get(dir + File.separator + pathName + ".json"), json.getBytes(),
					StandardOpenOption.CREATE);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	@Override
	public <E> void safeAsGson(List<ASerializable> list, Class<E> objectType) {
		list.stream().forEach(i -> safeAsGson(i, objectType));
	}
}
