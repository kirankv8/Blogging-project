package com.application.blogging.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.blogging.exceptionhandler.ResourceNotFoundException;
import com.application.blogging.model.Comments;
import com.application.blogging.model.dto.CommentDto;
import com.application.blogging.repository.CommentRepository;
import com.application.blogging.repository.PostRepository;
import com.application.blogging.service.CommentService;

@Service
public class CommentServiceImpl  implements CommentService{


	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PostRepository postRepository;

	@Override
	public CommentDto createComment(CommentDto commentDto, Long postId) throws ResourceNotFoundException {

		postRepository.findById(postId).orElseThrow( ()-> new ResourceNotFoundException("given Id not present in the db", 404));
		Comments comments=mapper.map(commentDto, Comments.class);
		commentRepository.save(comments);
          return this.mapper.map(comments, CommentDto.class);



	}

	@Override
	public void deleteComment(Long commentId) throws ResourceNotFoundException{
	commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("given comment id not present in the Db", 404));

	}

}