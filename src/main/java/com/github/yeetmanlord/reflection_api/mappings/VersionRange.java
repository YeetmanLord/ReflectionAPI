package com.github.yeetmanlord.reflection_api.mappings;

import com.github.yeetmanlord.reflection_api.Version;

public class VersionRange {

	public Version start, end;

	/**
	 * @param start Start of the version range (Inclusive)
	 * @param end   End of the version range (Exclusive)
	 */
	public VersionRange(Version start, Version end) {

		this.start = start;
		this.end = end;

	}

	/**
	 * @param start Start of the version range (Inclusive)
	 * @param end   End of the version range (Exclusive)
	 */
	public VersionRange(String start, String end) {

		this(new Version(start), new Version(end));

	}

	/**
	 * Checks if the given version is within this version range
	 * 
	 * @param ver The version to check if it is in this version range
	 * @return Returns true if and only if this specified version is the starting
	 *         version or is new than the starting version AND older than the ending
	 *         version
	 */
	public boolean isWithinRange(Version ver) {

		return ver.isNewer(start) && ver.isOlder(end);

	}

	@Override
	public String toString() {

		return "VersionRange{" + start.toString() + " - " + end.toString() + "}";

	}

}
