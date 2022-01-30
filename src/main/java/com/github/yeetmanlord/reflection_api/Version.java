package com.github.yeetmanlord.reflection_api;

public class Version implements Comparable<Version> {
	private String version;
	private int majorVersion, minorVersion;
	
	public Version(String version) 
	{
		this.version = version;
		String[] split = version.replace(".", "_").split("_");
		this.majorVersion = Integer.valueOf(split[1]);
		this.minorVersion = 0;
		if(split.length == 3)
		{
			this.minorVersion = Integer.valueOf(split[2]);
		}
	}
	
	public Version(int major, int minor)
	{
		if(major < 0)
		{
			major = 0;
		}
		this.version = "1." + major;
		if(minor > 0)
		{
			this.version = version + "." + minor;
		}
		this.majorVersion = major;
		this.minorVersion = minor;
	}
	
	/**
	 * @param object Can be a {@link String} or {@link Version}
	 */
	@Override
	public boolean equals(Object object) {
		if(object instanceof Version)
		{
			return ((Version)object).getVersion().equals(this.version);
		}
		else if(object instanceof String)
		{
			return object == this.version;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return version;
	}

	@Override
	public int compareTo(Version version) {
		return compare(this.majorVersion, version.majorVersion, this.minorVersion, version.minorVersion);
	}
	
	public int compare(int major1, int major2, int minor1, int minor2)
	{
		if(major1 < major2 || (major1 == major2 && minor1 < minor2))
		{
			return -1;
		}
		else if(major1 == major2 && minor1 == minor2)
		{
			return 0;
		}
		return 1;
	}
	
	public String getVersion() {
		return version;
	}
	
	public int getMajorVersion() {
		return majorVersion;
	}
	
	public int getMinorVersion() {
		return minorVersion;
	}

}
