package com.application.blogging.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.application.blogging.service.CommentService;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService  commentService;

}
