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
	public void safeAsGson(ASerializable object) {
		String dir = System.getProperty("user.dir");
		Gson gson = new Gson();

		var type = new TypeToken<List<ASerializable>>() {
		}.getType();
		String json = gson.toJson(object, type);

		try {
			Files.write(Paths.get(dir + File.separator + object.getPathName()), json.getBytes(),
					StandardOpenOption.CREATE);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	@Override
	public void safeAsGson(List<ASerializable> list) {
		list.stream().forEach(this::safeAsGson);
	}
}
