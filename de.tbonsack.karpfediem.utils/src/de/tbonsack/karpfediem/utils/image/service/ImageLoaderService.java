package de.tbonsack.karpfediem.utils.image.service;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

public interface ImageLoaderService {

	ImageDescriptor getImageDescriptor(Class<?> clazz, String path);

	Image resize(Image image, int width, int height);

}
