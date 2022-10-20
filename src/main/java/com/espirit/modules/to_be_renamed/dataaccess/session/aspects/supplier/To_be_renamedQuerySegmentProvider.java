package com.espirit.modules.to_be_renamed.dataaccess.session.aspects.supplier;

import de.espirit.firstspirit.client.search.SegmentProvider;

public class To_be_renamedQuerySegmentProvider implements SegmentProvider {

    private final String _segment;

    public To_be_renamedQuerySegmentProvider(String segment) {
        _segment = segment;
    }

    @Override
    public String getSegment() {
        return _segment;
    }
}
