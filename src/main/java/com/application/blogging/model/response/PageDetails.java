package com.application.blogging.model.response;

import java.util.List;

import lombok.Data;

@Data
public class PageDetails {

	private List<PostResponse> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean lastPage;
}
