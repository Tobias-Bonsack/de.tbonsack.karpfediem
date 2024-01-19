package de.tbonsack.karpfediem.pokemon.cardmanager.services.impls;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.PreferenceService;

public class PreferenceServiceImpl implements PreferenceService {

	private static final String ID_COUNT = "id-count";
	private static final String NODEPATH = "de.tbonsack.karpfediem.pokemon.cardmanager";

	private Long _idCount;

	public PreferenceServiceImpl() {
		System.out.println(Platform.getInstanceLocation().getURL().getPath());
		IEclipsePreferences node = InstanceScope.INSTANCE.getNode(NODEPATH);
		_idCount = node.getLong(ID_COUNT, Long.MIN_VALUE);
		if (_idCount == Long.MIN_VALUE) {
			_idCount++;
			node.putLong(ID_COUNT, _idCount);
			try {
				node.flush();
			} catch (BackingStoreException e) {
				// Ignore
			}
		}
	}

	@Override
	public long getNextID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
