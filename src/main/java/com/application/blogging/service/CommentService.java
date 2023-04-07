package com.application.blogging.service;

import com.application.blogging.model.dto.CommentDto;

public interface CommentService {


	CommentDto createComment(CommentDto commentDto, Integer postId);

	void deleteComment(Integer commentId);

}
