package com.github.yeetmanlord.reflection_api.mappings.types;

import java.util.Iterator;
import java.util.Map;

import com.github.yeetmanlord.reflection_api.ReflectionApi;
import com.github.yeetmanlord.reflection_api.mappings.Mapping;
import com.github.yeetmanlord.reflection_api.mappings.MappingsException;
import com.github.yeetmanlord.reflection_api.mappings.VersionRange;

public class ValueMapping<Type> implements Mapping<Type> {

	public Map<VersionRange, Type> mappings;

	public String name;

	public ValueMapping(String name, Map<VersionRange, Type> mappings) {

		this.mappings = mappings;
		this.name = name;

	}

	@Override
	public String getName() {

		return name;

	}

	@Override
	public Map<VersionRange, Type> getMappings() {

		return this.mappings;

	}

	@Override
	public void addMapping(VersionRange range, Type value) {

		this.mappings.put(range, value);

	}

	public Type getValue() throws MappingsException {

		for (VersionRange range : mappings.keySet()) {

			if (range.isWithinRange(ReflectionApi.version)) {
				return mappings.get(range);
			}

		}
		
		throw new MappingsException(this, "Failed to get mapping value");

	}

	@Override
	public String toString() {

		String maps = "Mappings[";
		Iterator<VersionRange> iter = mappings.keySet().iterator();

		while (iter.hasNext()) {
			VersionRange range = iter.next();

			if (iter.hasNext()) {
				maps += "(versionRange: " + range.toString() + ", mappingValue: " + mappings.get(range) + "), ";
			}
			else {
				maps += "(versionRange: " + range.toString() + ", mappingValue: " + mappings.get(range) + ")]";
			}

		}

		for (int x = 0; x < mappings.keySet().size(); x++) {
		}

		return "ValueMapping{name: " + name + ", mappings: " + maps + "}";

	}

}
