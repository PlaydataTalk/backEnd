package pdt.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import pdt.dao.IlikeRepository;
import pdt.dao.PostRepository;
import pdt.dao.ReplyRepository;
import pdt.dao.UserRepository;
import pdt.entity.Post;
import pdt.entity.Reply;
import pdt.entity.User;
import pdt.service.IlikeServiceImp;
import pdt.service.PostServiceImp;
import pdt.service.ReplyService;
import pdt.service.UserService;

@SessionAttributes("postList")
@Controller
public class Cont {

	@Autowired
	PostServiceImp postService;

	@Autowired
	PostRepository postRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	IlikeRepository ilikeRepository;

	@Autowired
	IlikeServiceImp ilikeService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ReplyService replyService;
	
	@Autowired
	ReplyRepository replyRepository;

	@RequestMapping("/gohome")
	public String goHome(@ModelAttribute("user") User user, Model model) {
		User findUser = userService.getUser(user);
		System.out.println(model.getAttribute("user"));
		System.out.println("********"+findUser);
		List<Post> postList = postRepository.getPostList();
		model.addAttribute("postList", postList);
		return "home";
	}

	@RequestMapping("/write")
	public String write(@ModelAttribute("user") User user, Model model, @ModelAttribute("post") Post post) {
		System.out.println(model.getAttribute("user"));
		//User user1 = (User) model.getAttribute("user");
		//System.out.println(user1.getUserId());
		//System.out.println(user1.getImgUrl());
		//System.out.println(user.getUserId());
		System.out.println(post.getUserId().getImgUrl());
		System.out.println(post.getText());
		System.out.println(post);
		postService.insertPost(post);
		List<Post> postList = postRepository.getPostList();
		model.addAttribute("postList", postList);
		return "home";
	}

	@RequestMapping("/gowrite")
	public String gowrite(@ModelAttribute("user") User user, Model model) {
		//System.out.println(model.getAttribute("user"));
		return "write";
	}
	
	@RequestMapping("/ilike")
	public String addLike(@RequestParam("userId") User user, Model model, @RequestParam("postId") Post post) {
		boolean a = ilikeService.addLike(user.getUserId(), post);
		System.out.println(a);
		if (a) {
			postService.updateIlike(post.getPostId());
			return "home";
		} else {
			ilikeService.deleteByUserId(user);
			postService.updateIlike2(post.getPostId());
		}

		return "home";

	}

	@RequestMapping("/ilike2")
	public String addLike2(Model model, @RequestParam("postId") Post post) {
		System.out.println(post.getPostId());
		postService.updateIlike(post.getPostId());
		return "home";
	}
	
	@RequestMapping("/goreply")
	public String goReply(@RequestParam("postId")Post post, Model model) {
		Post findPost = postService.getPost(post);
		System.out.println("********"+findPost);
		List<Reply> replyList = replyRepository.findByPostId(findPost);
		model.addAttribute("replyList", replyList);
		return "reply";
	}

}
