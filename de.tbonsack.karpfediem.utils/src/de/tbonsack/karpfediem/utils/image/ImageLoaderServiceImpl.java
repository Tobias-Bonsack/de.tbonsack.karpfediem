package de.tbonsack.karpfediem.utils.image;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.osgi.service.component.annotations.Component;

import de.tbonsack.karpfediem.utils.image.service.ImageLoaderService;

@Component
public class ImageLoaderServiceImpl implements ImageLoaderService {

	@Override
	public ImageDescriptor getImageDescriptor(Class<?> clazz, String path, boolean isAbsolutPath) {
		String dir = isAbsolutPath ? "" : Platform.getInstanceLocation().getURL().getPath().substring(1);
		URL url = null;

		try {
			url = new File(dir + path).toURI().toURL();
		} catch (MalformedURLException e) {
			Platform.getLog(getClass()).error("Can not load: " + path, e);
		}

		return ImageDescriptor.createFromURL(url);
	}

	@Override
	public Image resize(Image image, int width, int height) {
		Image scaled = new Image(Display.getDefault(), width, height);
		GC gc = new GC(scaled);
		gc.setAntialias(SWT.ON);
		gc.setInterpolation(SWT.HIGH);
		gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, width, height);
		gc.dispose();
//      image.dispose(); // caller need to do this
		return scaled;
	}

}
