package com.espirit.modules.example1.dataaccess.session;

import com.espirit.modules.example1.ExamplePOJO;
import com.espirit.modules.example1.dataaccess.session.ExampleDataAccessSession;
import de.espirit.firstspirit.access.BaseContext;
import de.espirit.firstspirit.client.plugin.dataaccess.DataAccessSession;
import de.espirit.firstspirit.client.plugin.dataaccess.DataAccessSessionBuilder;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.SessionBuilderAspectMap;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.SessionBuilderAspectType;

/**
 * Basic class to create a Data Access Session.
 * Please see FirstSpirit API for more information.
 */
public class ExampleDataAccessSessionBuilder implements DataAccessSessionBuilder<ExamplePOJO> {

    private final SessionBuilderAspectMap _aspects = new SessionBuilderAspectMap();

    @Override
    public DataAccessSession<ExamplePOJO> createSession(BaseContext context) {
        return new ExampleDataAccessSession(context);
    }

    @Override
    public <A> A getAspect(SessionBuilderAspectType<A> aspectType) {
        return _aspects.get(aspectType);
    }
}
