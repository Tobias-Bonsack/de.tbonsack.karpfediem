package de.tbonsack.karpfediem.pokemon.cardmanager.services.impls;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.PreferenceService;

public class PreferenceServiceImpl implements PreferenceService {

	private static final String ID_COUNT = "id-count";

	private static final String NODEPATH = "de.tbonsack.karpfediem.pokemon.cardmanager";

	private int _idCount;

	private ILog _log;

	private final IEclipsePreferences _node;

	public PreferenceServiceImpl() {
		_log = Platform.getLog(getClass());
		_node = InstanceScope.INSTANCE.getNode(NODEPATH);
		_idCount = _node.getInt(ID_COUNT, Integer.MIN_VALUE);
		if (_idCount == Integer.MIN_VALUE) {
			_idCount++;
			_node.putLong(ID_COUNT, _idCount);
			try {
				_node.flush();
				_log.info("Create new ID counter");
			} catch (BackingStoreException e) {
				_log.error("Can't flush prefs", e);
			}
		}
		_log.info("Init prefs done");
	}

	@Override
	public int getNextID() {
		_node.putLong(ID_COUNT, ++_idCount);
		try {
			_node.flush();
			_log.info("New ID issued");
		} catch (BackingStoreException e) {
			_log.error("Can't flush prefs", e);

		}
		return _idCount;
	}

}
