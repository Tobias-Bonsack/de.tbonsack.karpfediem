package de.tbonsack.karpfediem.pokemon.cardmanager.services.internal;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.osgi.service.component.annotations.Component;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardService;
import de.tbonsack.karpfediem.pokemon.cardmanager.services.impls.CardServiceImpl;

@Component(service = IContextFunction.class, //
		property = "service.context.key=de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardService")
public class CardContextFunction extends ContextFunction {

	@Override
	public Object compute(IEclipseContext context) {
		CardService service = ContextInjectionFactory.make(CardServiceImpl.class, context);
		service.init();
		context.get(MApplication.class).getContext().set(CardService.class, service);
		return service;
	}

}
