package de.tbonsack.karpfediem.pokemon.cardmanager.services.internal;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.osgi.service.component.annotations.Component;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.PreferenceService;
import de.tbonsack.karpfediem.pokemon.cardmanager.services.impls.PreferenceServiceImpl;

@Component(service = IContextFunction.class, //
		property = "service.context.key=de.tbonsack.karpfediem.pokemon.cardmanager.model.services.PreferenceService")
public class PreferenceContextFunction extends ContextFunction {

	@Override
	public Object compute(IEclipseContext context) {
		var service = ContextInjectionFactory.make(PreferenceServiceImpl.class, context);
		context.get(MApplication.class).getContext().set(PreferenceService.class, service);
		return service;
	}
}
