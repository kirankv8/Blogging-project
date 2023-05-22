package com.application.blogging.service;

import com.application.blogging.exceptionhandler.ResourceNotFoundException;
import com.application.blogging.model.dto.CommentDto;

public interface CommentService {


	CommentDto createComment(CommentDto commentDto, Long postId) throws ResourceNotFoundException;

	void deleteComment(Long commentId)throws ResourceNotFoundException;

}
