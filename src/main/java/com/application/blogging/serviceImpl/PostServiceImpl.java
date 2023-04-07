package com.application.blogging.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.application.blogging.exceptionhandler.ResourceNotFoundException;
import com.application.blogging.model.Category;
import com.application.blogging.model.Post;
import com.application.blogging.model.User;
import com.application.blogging.model.dto.PostDto;
import com.application.blogging.model.response.PageDetails;
import com.application.blogging.model.response.PostResponse;
import com.application.blogging.repository.CategoryRepository;
import com.application.blogging.repository.PostRepository;
import com.application.blogging.repository.UserRepository;
import com.application.blogging.service.PostSevice;

@Service
public class PostServiceImpl implements PostSevice {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public PostResponse createPost(PostDto dto, Long userId, Long categoryId) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("given UserId not Present In Db", 404));
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("given catgeroyId not Present In Db", 404));
		Post post = mapper.map(dto, Post.class);
		post.setImageName("defualt");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		postRepository.save(post);
		return this.mapper.map(post, PostResponse.class);

	}

	@Override
	public PostResponse UpdatePost(PostDto dto, Long id) throws ResourceNotFoundException {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("given post id not present in the db", 404));
		post.setContent(dto.getContent());
		post.setTitle(dto.getTitle());
		post.setImageName(dto.getImageName());
		postRepository.save(post);

		return this.mapper.map(post, PostResponse.class);
	}

	@Override
	public void DeletePost(Long id) throws ResourceNotFoundException {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("given post id not present in the db", 404));
		postRepository.delete(post);

	}

	@Override
	public PageDetails getAllpost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		org.springframework.data.domain.Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = postRepository.findAll(pageable);

		List<Post> allPosts = pagePost.getContent();
		List<PostResponse> responses = allPosts.stream().map(post -> mapper.map(post, PostResponse.class))
				.collect(Collectors.toList());

		PageDetails details = new PageDetails();
		details.setContent(responses);
		details.setTotalElements(pagePost.getTotalElements());
		details.setPageNumber(pagePost.getNumber());
		details.setPageSize(pagePost.getSize());
		details.setTotalPages(pagePost.getTotalPages());
		details.setLastPage(pagePost.isLast());
		return details;

	}

	@Override
	public PostResponse getByPostId(Long id) throws ResourceNotFoundException {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("given id not present in the db", 404));
		return this.mapper.map(post, PostResponse.class);
	}

	@Override
	public List<PostResponse> searchPost(String keyword) {

		return null;
	}

	@Override
	public List<PostResponse> getAllPostByCategory(Long categoryId) throws ResourceNotFoundException {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("given category is not present in db", 404));
		List<Post> posts = postRepository.findByCategory(category);
		List<PostResponse> responses = posts.stream().map(post -> mapper.map(post, PostResponse.class))
				.collect(Collectors.toList());
		return responses;
	}

	@Override
	public List<PostResponse> getAllPostByUser(Long userId) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("given userID not found in Db", 404));
		List<Post> posts = postRepository.findByUser(user);
		List<PostResponse> responses = posts.stream().map(post -> mapper.map(post, PostResponse.class)).toList();
		return responses;
	}

}
