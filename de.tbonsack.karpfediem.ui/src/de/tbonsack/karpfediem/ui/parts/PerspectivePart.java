package de.tbonsack.karpfediem.ui.parts;

import java.util.List;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class PerspectivePart {

	@PostConstruct
	public void postConstruct(MApplication app, EPartService partService, EModelService modelService) {
		List<MPerspective> perspectives = modelService.findElements(app, null, MPerspective.class, null);
		System.out.println(perspectives.size());

		for (MPerspective mPerspective : perspectives) {
			System.out.println(mPerspective.getLabel());
		}
	}

}
