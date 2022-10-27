package pdt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import pdt.dao.PostRepository;
import pdt.entity.Post;
import pdt.service.PostServiceImp;

@SessionAttributes("user")
@RestController
public class RestCont {

	@Autowired
	PostServiceImp postService;
	@Autowired
	PostRepository postRepository;

	@RequestMapping("/home")
	public List<Post> home() {

		return postRepository.getPostList();
	}

	@RequestMapping("/publish")
	public List<Post> publish(Post post) {

		postService.insertPost(post);

		return postRepository.getPostList();
	}

}
