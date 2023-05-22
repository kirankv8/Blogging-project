package com.application.blogging.contoller;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import com.application.blogging.exceptionhandler.ResourceNotFoundException;
import com.application.blogging.model.dto.PostDto;
import com.application.blogging.model.response.PageDetails;
import com.application.blogging.model.response.PostResponse;
import com.application.blogging.service.FileService;
import com.application.blogging.service.PostSevice;
import com.application.blogging.utill.AppConstants;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1")
public class PostController {

	@Autowired
	private PostSevice postSevice;

	@Value("${project.image}")
	String path;

	@Autowired
	private FileService fileService;

	@PostMapping("user/{userId}/category/{categoryId}/create/post")
	public PostResponse createPost(@RequestBody PostDto postDto, @PathVariable Long userId,
			@PathVariable Long categoryId) throws ResourceNotFoundException {
		return postSevice.createPost(postDto, userId, categoryId);
	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostResponse>> getAllPosts(@PathVariable Long categoryId)
			throws ResourceNotFoundException {
		List<PostResponse> responses = postSevice.getAllPostByCategory(categoryId);
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostResponse>> getALLPostsByUser(@PathVariable Long userId)
			throws ResourceNotFoundException {
		List<PostResponse> responses = postSevice.getAllPostByUser(userId);
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}

	@PutMapping("/post/{postId}")
	public ResponseEntity<PostResponse> updatePost(@RequestBody PostDto dto, @PathVariable Long postId)
			throws ResourceNotFoundException {
		PostResponse response = postSevice.UpdatePost(dto, postId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("post/{postId}/delete")
	public ResponseEntity<String> deletePost(@PathVariable Long postId) throws ResourceNotFoundException {
		postSevice.DeletePost(postId);
		return new ResponseEntity<>("post succussfully deleted", HttpStatus.ACCEPTED);
	}

	@GetMapping("getBy/post/{postId}")
	public ResponseEntity<PostResponse> getBypostId(@PathVariable Long postId) throws ResourceNotFoundException {
		PostResponse postResponse = postSevice.getByPostId(postId);
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}

	@GetMapping("/getAll/posts")
	public ResponseEntity<PageDetails> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
		PageDetails allPosts = this.postSevice.getAllpost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<>(allPosts, HttpStatus.OK);

	}

	@GetMapping("post/getBy/")
	public ResponseEntity<List<PostResponse>> getPostByKeyword(@RequestParam String keyword)
			throws ResourceNotFoundException {
		List<PostResponse> postResponses = postSevice.searchPost(keyword);
		return new ResponseEntity<>(postResponses, HttpStatus.OK);

	}

	@PostMapping("/upload/image/post/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable Long postId)
			throws IOException, ResourceNotFoundException {
		String filename = this.fileService.uploadImage(path, image);
		PostResponse response = postSevice.getByPostId(postId);
		response.setImageName(filename);
		PostDto dto = new PostDto();
		dto.setImageName(filename);
		dto.setAddedDate(response.getAddedDate());
		dto.setCategory(response.getCategory());
		dto.setContent(response.getContent());
		dto.setTitle(response.getTitle());
		dto.setUser(response.getUser());
		//PostResponse updatedPost = postSevice.UpdatePost(dto, postId);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
    //method to serve files
    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        org.springframework.util.StreamUtils.copy(resource,response.getOutputStream())   ;

    }

}
