package com.espirit.modules.example1.dataaccess.datastream;

import com.espirit.modules.example1.ExamplePOJO;
import de.espirit.firstspirit.client.plugin.dataaccess.DataStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The FirstSpirit Example posting data stream.
 * Please see FirstSpirit API for more information.
 */
public class ExampleDataStream implements DataStream<ExamplePOJO> {

	//this class is used to provide the actual data stream for a report
	//it sets up filter variables, search queries and pagination support as well

	private final List<ExamplePOJO> examples;
	private int total = -1;
	private boolean hasNext = false;

	private int index = -1;

	private int page = -1;



	private static final Class<?> LOGGER = ExampleDataStream.class;

	public ExampleDataStream(final List<ExamplePOJO> examples) {
		this.examples = examples;
		this.total = this.examples.size();
		if (this.total > 0) {
			this.hasNext = true;
			this.index = 0;
			this.page = 1;
		}
	}

	@Override
	public void close() {
		// Nothing
	}

	@Override
	public List<ExamplePOJO> getNext(int count) {

		List<ExamplePOJO> result = new ArrayList<ExamplePOJO>();

		if (!hasNext()) {
			return Collections.emptyList();
		} else{

			int limit = count*this.page;

			while (this.index < limit) {
				result.add(this.examples.get(this.index));
				try {
					this.examples.get(this.index+1);
				} catch (IndexOutOfBoundsException ioe) {
					this.hasNext = false;
					break;
				}
				this.index++;
			}
			this.page++;
		}
		return result;
	}

	@Override
	public int getTotal() {
		return this.examples.size();
	}

	@Override
	public boolean hasNext() {
		return this.hasNext;
	}
}
