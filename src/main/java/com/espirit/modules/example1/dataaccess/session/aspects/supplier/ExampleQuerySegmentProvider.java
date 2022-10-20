package com.espirit.modules.example1.dataaccess.session.aspects.supplier;

import de.espirit.firstspirit.client.search.SegmentProvider;

public class ExampleQuerySegmentProvider implements SegmentProvider {

    private final String _segment;

    public ExampleQuerySegmentProvider(String segment) {
        _segment = segment;
    }

    @Override
    public String getSegment() {
        return _segment;
    }
}
