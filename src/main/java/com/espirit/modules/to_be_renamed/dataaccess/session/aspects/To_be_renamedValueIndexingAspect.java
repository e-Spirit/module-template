package com.espirit.modules.to_be_renamed.dataaccess.session.aspects;

import de.espirit.firstspirit.access.Language;
import de.espirit.firstspirit.access.editor.ValueIndexer;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.ValueIndexing;

/**
 * Basic class to internally handle report listings
 * Please see FirstSpirit API for more information.
 */
public class To_be_renamedValueIndexingAspect implements ValueIndexing {

    @Override
    public void appendIndexData(String identifier, Language language, boolean recursive, ValueIndexer indexer) {
        indexer.append(ValueIndexer.VALUE_FIELD, identifier);
    }
}
