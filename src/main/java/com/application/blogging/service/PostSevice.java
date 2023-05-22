package com.application.blogging.service;

import java.util.List;

import com.application.blogging.exceptionhandler.ResourceNotFoundException;
import com.application.blogging.model.dto.PostDto;
import com.application.blogging.model.response.PageDetails;
import com.application.blogging.model.response.PostResponse;

public interface PostSevice {

	PostResponse createPost(PostDto dto,Long userId,Long categoryId )throws ResourceNotFoundException;

	PostResponse UpdatePost(PostDto dto,Long id)throws ResourceNotFoundException;

	void DeletePost(Long id)throws ResourceNotFoundException;

	PageDetails getAllpost(Integer pageNumber,Integer pageSize,String sortBy, String sortDir);

	PostResponse getByPostId(Long id)throws ResourceNotFoundException;

	List<PostResponse> getAllPostByCategory(Long  categoryId) throws ResourceNotFoundException;

	List<PostResponse> getAllPostByUser(Long userId )throws ResourceNotFoundException;

	List<PostResponse>searchPost(String keyword)throws ResourceNotFoundException;



}