package com.github.yeetmanlord.reflection_api.mappings;

public class MappingsException extends Exception {

	private static final long serialVersionUID = 1134534421L;

	public MappingsException(Mapping mapping) {

		this(mapping, "This means your plugin uses an unsupported version or mappings have not been added for your version");

	}
	
	public MappingsException(Mapping mapping, String extraInfo) {

		super("Failed to load " + mapping.getName() + "\nFull Data: " + mapping.toString() + "\n" + extraInfo);

	}

}
