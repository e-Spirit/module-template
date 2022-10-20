package com.espirit.modules.to_be_renamed.dataaccess.session;

import com.espirit.modules.to_be_renamed.To_be_renamedPOJO;
import de.espirit.firstspirit.access.BaseContext;
import de.espirit.firstspirit.client.plugin.dataaccess.DataAccessSession;
import de.espirit.firstspirit.client.plugin.dataaccess.DataAccessSessionBuilder;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.SessionBuilderAspectMap;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.SessionBuilderAspectType;

/**
 * Basic class to create a Data Access Session.
 * Please see FirstSpirit API for more information.
 */
public class To_be_renamedDataAccessSessionBuilder implements DataAccessSessionBuilder<To_be_renamedPOJO> {

    private final SessionBuilderAspectMap _aspects = new SessionBuilderAspectMap();

    @Override
    public DataAccessSession<To_be_renamedPOJO> createSession(BaseContext context) {
        return new To_be_renamedDataAccessSession(context);
    }

    @Override
    public <A> A getAspect(SessionBuilderAspectType<A> aspectType) {
        return _aspects.get(aspectType);
    }
}
