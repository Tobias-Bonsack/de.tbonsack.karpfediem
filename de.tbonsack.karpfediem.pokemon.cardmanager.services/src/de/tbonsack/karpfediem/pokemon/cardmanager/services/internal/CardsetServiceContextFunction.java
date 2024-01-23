package de.tbonsack.karpfediem.pokemon.cardmanager.services.internal;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.osgi.service.component.annotations.Component;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardSetService;
import de.tbonsack.karpfediem.pokemon.cardmanager.services.impls.CardSetServiceImpl;

@Component(service = IContextFunction.class, //
		property = "service.context.key=de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardSetService")
public class CardsetServiceContextFunction extends ContextFunction {

	@Override
	public Object compute(IEclipseContext context) {
		CardSetService service = ContextInjectionFactory.make(CardSetServiceImpl.class, context);
		service.init();
		context.get(MApplication.class).getContext().set(CardSetService.class, service);
		return service;
	}
}
