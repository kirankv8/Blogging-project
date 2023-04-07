package com.application.blogging.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.blogging.config.AppConstants;
import com.application.blogging.exceptionhandler.ResourceNotFoundException;
import com.application.blogging.model.dto.PostDto;
import com.application.blogging.model.response.PageDetails;
import com.application.blogging.model.response.PostResponse;
import com.application.blogging.service.PostSevice;

@RestController
@RequestMapping("/api/v1")
public class PostController {

	@Autowired
	private PostSevice postSevice;

	@PostMapping("user/{userId}/category/{categoryId}/create/post")
	public PostResponse createPost(@RequestBody PostDto postDto, @PathVariable Long userId,
			@PathVariable Long categoryId) throws ResourceNotFoundException {
		return postSevice.createPost(postDto, userId, categoryId);
	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostResponse>> getAllPosts(@PathVariable Long categoryId)
			throws ResourceNotFoundException {
		List<PostResponse> responses = postSevice.getAllPostByCategory(categoryId);
		return new ResponseEntity<List<PostResponse>>(responses, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostResponse>> getALLPostsByUser(@PathVariable Long userId)
			throws ResourceNotFoundException {
		List<PostResponse> responses = postSevice.getAllPostByUser(userId);
		return new ResponseEntity<List<PostResponse>>(responses, HttpStatus.OK);
	}

	@PutMapping("/post/{postId}")
	public ResponseEntity<PostResponse> updatePost(@RequestBody PostDto dto, @PathVariable Long postId)
			throws ResourceNotFoundException {
		PostResponse response = postSevice.UpdatePost(dto, postId);
		return new ResponseEntity<PostResponse>(response, HttpStatus.OK);
	}

	@DeleteMapping("post/{postId}/delete")
	public ResponseEntity<String> deletePost(@PathVariable Long postId) throws ResourceNotFoundException {
		postSevice.DeletePost(postId);
		return new ResponseEntity<String>("post succussfully deleted", HttpStatus.ACCEPTED);
	}

	@GetMapping("getBy/post/{postId}")
	public ResponseEntity<PostResponse> getBypostId(@PathVariable Long postId) throws ResourceNotFoundException {
		PostResponse postResponse = postSevice.getByPostId(postId);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	@GetMapping("/getAll/posts")
	public ResponseEntity<PageDetails> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue ="asc", required = false) String sortDir) {
		PageDetails allPosts = this.postSevice.getAllpost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PageDetails>(allPosts, HttpStatus.OK);

	}

}
