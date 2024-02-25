package de.tbonsack.karpfediem.utils.image.service;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

public interface ImageLoaderService {

	ImageDescriptor getImageDescriptor(String path, boolean isAbsolutPath);

	Image resize(Image image, int width, int height);

}
