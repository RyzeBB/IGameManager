package com.igame.security.manager;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.util.Assert;

import com.igame.security.entity.SecACL;

/**
 * security 权限实体
 * 
 * @author Allen
 */
public class SecurityAclsConfig implements ConfigAttribute {
	private static final long serialVersionUID = 1L;

	private final String attrib;
	private final SecACL acl;

	// ~ Constructors
	// ===================================================================================================

	public SecurityAclsConfig(String config, SecACL _acl) {
		Assert.hasText(config, "You must provide a configuration attribute");
		this.attrib = config;
		this.acl = _acl;
	}

	// ~ Methods
	// ========================================================================================================

	public boolean equals(Object obj) {
		if (obj instanceof ConfigAttribute) {
			ConfigAttribute attr = (ConfigAttribute) obj;
			return this.attrib.equals(attr.getAttribute());
		}

		return false;
	}

	public String getAttribute() {
		return this.attrib;
	}

	public int hashCode() {
		return this.attrib.hashCode();
	}

	public String toString() {
		return this.attrib;
	}

	public SecACL getAcl() {
		return acl;
	}

}
