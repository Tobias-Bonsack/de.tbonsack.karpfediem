package de.tbonsack.karpfediem.pokemon.cardmanager.ui;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.widgets.WidgetFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.osgi.framework.FrameworkUtil;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.Card;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.CardSet;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardService;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardSetService;

public class testcardpart {

	@Inject
	CardService _cardService;

	@Inject
	CardSetService _setService;

	@PostConstruct
	public void postConstruct(Composite parent) {
		CardSet set = _setService.getAllSets().iterator().next();
		int idFromCard = set.getCards().iterator().next();
		Card next = _cardService.getCard(idFromCard);

		GridLayoutFactory.fillDefaults().numColumns(1).applyTo(parent);

		WidgetFactory.label(SWT.None).text(next.getName()).layoutData(GridDataFactory.fillDefaults().create())
				.create(parent);

		Label label = WidgetFactory.label(SWT.None).layoutData(GridDataFactory.fillDefaults().create()).create(parent);
		var manager = new LocalResourceManager(JFaceResources.getResources(), label);
		Image image = manager.create(ImageDescriptor.createFromURL(
				FileLocator.find(FrameworkUtil.getBundle(getClass()), new Path("images/alakazam-base-set-bs-1.jpg"))));

		label.setImage(image);
	}

}
