package com.realworld.backend.api.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;

@JsonSerialize(using = ErrorResourceSerializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@JsonRootName("errors")
public class ErrorResource {
	private List<FieldErrorResource> fieldErrors;

	public ErrorResource(List<FieldErrorResource> fieldErrorResources) {
		this.fieldErrors = fieldErrorResources;
	}
}
