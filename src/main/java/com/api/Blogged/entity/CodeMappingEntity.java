package com.api.Blogged.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.api.Blogged.entity.pk.CodeMappingEntityPk;

@Entity
@Table(name = "code_mapping")
@IdClass(CodeMappingEntityPk.class)
public class CodeMappingEntity {

	@Id
	@Column(name = "Category")
	private String Category;
	@Id
	@Column(name = "Code", length = 3)
	private String Code;
	@Column(name = "Value")
	private String Value;

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

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	@Override
	public String toString() {
		return "CodeMappingEntity [Category=" + Category + ", Code=" + Code + ", Value=" + Value + "]";
	}

}
