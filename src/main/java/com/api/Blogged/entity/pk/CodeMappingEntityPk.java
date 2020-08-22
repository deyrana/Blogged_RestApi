package com.api.Blogged.entity.pk;

import java.io.Serializable;

public class CodeMappingEntityPk implements Serializable {

	private static final long serialVersionUID = 3945916262156491895L;
	private String Category;
	private String Code;

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Category == null) ? 0 : Category.hashCode());
		result = prime * result + ((Code == null) ? 0 : Code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CodeMappingEntityPk other = (CodeMappingEntityPk) obj;
		if (Category == null) {
			if (other.Category != null)
				return false;
		} else if (!Category.equals(other.Category))
			return false;
		if (Code == null) {
			if (other.Code != null)
				return false;
		} else if (!Code.equals(other.Code))
			return false;
		return true;
	}

}
