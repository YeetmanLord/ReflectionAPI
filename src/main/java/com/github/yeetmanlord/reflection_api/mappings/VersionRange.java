package com.github.yeetmanlord.reflection_api.mappings;

import com.github.yeetmanlord.reflection_api.Version;

public class VersionRange {

	public Version start, end;

	public VersionRange(Version start, Version end) {

		this.start = start;
		this.end = end;

	}

	public VersionRange(String start, String end) {

		this(new Version(start), new Version(end));

	}

	public boolean isWithinRange(Version ver) {

		return ver.isNewer(start) && ver.isOlder(end);

	}

	@Override
	public String toString() {
	
		return "VersionRange{" + start.toString() + " - " + end.toString() + "}";
	
	}

}
