package com.espirit.modules.to_be_renamed.dataaccess.datastream;

import com.espirit.modules.to_be_renamed.To_be_renamedPOJO;
import de.espirit.firstspirit.client.plugin.dataaccess.DataStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The FirstSpirit data stream.
 * Please see FirstSpirit API for more information.
 */
public class To_be_renamedDataStream implements DataStream<To_be_renamedPOJO> {

	//this class is used to provide the actual data stream for a report
	//it sets up filter variables, search queries and pagination support as well

	private final List<To_be_renamedPOJO> objects;
	private int total = -1;
	private boolean hasNext = false;

	private int index = -1;

	private int page = -1;



	private static final Class<?> LOGGER = To_be_renamedDataStream.class;

	public To_be_renamedDataStream(final List<To_be_renamedPOJO> objects) {
		this.objects = objects;
		this.total = this.objects.size();
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
	public List<To_be_renamedPOJO> getNext(int count) {

		List<To_be_renamedPOJO> result = new ArrayList<To_be_renamedPOJO>();

		if (!hasNext()) {
			return Collections.emptyList();
		} else{

			int limit = count*this.page;

			while (this.index < limit) {
				result.add(this.objects.get(this.index));
				try {
					this.objects.get(this.index+1);
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
		return this.objects.size();
	}

	@Override
	public boolean hasNext() {
		return this.hasNext;
	}
}
