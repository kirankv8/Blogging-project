package com.application.blogging.serviceImpl;

import org.springframework.stereotype.Service;

import com.application.blogging.model.dto.CommentDto;
import com.application.blogging.service.CommentService;

@Service
public class CommentServiceImpl  implements CommentService{

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		
	}
	
}	