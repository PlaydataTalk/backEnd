package pdt.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pdt.dao.IlikeRepository;
import pdt.dao.PostRepository;
import pdt.dao.ReplyRepository;
import pdt.dao.UserRepository;
import pdt.entity.Post;
import pdt.entity.Reply;
import pdt.entity.User;
import pdt.firebase.FirebaseService;
import pdt.service.IlikeServiceImp;
import pdt.service.PostServiceImp;
import pdt.service.ReplyService;
import pdt.service.UserService;
import pdt.service.UserServiceImp;

@SessionAttributes("user")
@RestController
public class RestCont {

	@Autowired
	PostServiceImp postService;

	@Autowired
	PostRepository postRepository;

	@Autowired
	UserServiceImp userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	IlikeRepository ilikeRepository;

	@Autowired
	IlikeServiceImp ilikeService;

	@Autowired
	ReplyService replyService;

	@Autowired
	ReplyRepository replyRepository;

	@Autowired
	FirebaseService firebaseService;

	@RequestMapping("/createuser")
	public String createUser(@RequestBody User user) throws InterruptedException, ExecutionException {

		return firebaseService.saveUserDetails(user);
	}

	@RequestMapping("/getuserinform")
	public User getUser(@RequestParam String userId) throws InterruptedException, ExecutionException {
		User a = firebaseService.getUserDetails(userId);
		userService.insertUser(a);
		System.out.println(a);
		return a;
	}

	@RequestMapping("/home")
	public List<Post> home() {

		return postRepository.getPostList();
	}

//	@RequestMapping("/publish")
//	public List<Post> publish(Post post) {
//
//		postService.insertPost(post);
//
//		return postRepository.getPostList();
//	}

//	@RequestMapping(value = "/logIn", method = RequestMethod.POST)
//	public String login(User user, Model model) {
//		User findUser = userService.getUser(user);
//
//		if (findUser != null && findUser.getPw().equals(user.getPw())) {
//			model.addAttribute("user", findUser);
//			return "forward:gohome";
//		} else {
//			return "redirect:login.html";
//		}
//	}
//
//	@GetMapping("/logout")
//	public String logout(SessionStatus status) {
//		status.setComplete();
//		return "redirect:index.html";
//	}

	@RequestMapping("/gohome")
	public List<Post> goHome(@ModelAttribute("user") User user, Model model) {
		List<Post> postList = postRepository.getPostList();
		//model.addAttribute("postList", postList);
		return postList;
	}

	@RequestMapping("/write")
	public List<Post> write(@ModelAttribute("user") User user, Model model, @ModelAttribute("post") Post post) {

		String[] arr = post.getKeyword().split(", ");
		post.setKeyword1(arr[0]);
		post.setKeyword2(arr[1]);
		post.setKeyword3(arr[2]);
		postService.insertPost(post);
		List<Post> postList = postRepository.getPostList();
		return postList;
		//model.addAttribute("postList", postList);
		
	}

//	@RequestMapping("/gowrite")
//	public String gowrite(@ModelAttribute("user") User user, Model model) {
//		// System.out.println(model.getAttribute("user"));
//		return "write";
//	}

	@RequestMapping("/ilike")
	public Long addLike(@RequestParam("userId") User user, Model model, @RequestParam("postId") Post post) {
		boolean a = ilikeService.addLike(user.getUserId(), post);
		System.out.println(a);
		if (a) {
			postRepository.countLike(post.getPostId());
			
			return postRepository.returnLike(post.getPostId());
		} else {
			ilikeService.deleteByUserIdAndPostId(user, post);
			postRepository.countLike(post.getPostId());
		}
		return postRepository.returnLike(post.getPostId());

	}

	@RequestMapping("/goreply")
	public List<Reply> goReply(@RequestParam("postId") Post post, Model model) {
		Post findPost = postService.getPost(post);
		model.addAttribute("postId", findPost.getPostId());
		List<Reply> replyList = replyRepository.findByPostId(findPost);
		//model.addAttribute("replyList", replyList);
		return replyList;
	}

	@RequestMapping("/reply")
	public List<Post> reply(Model model, Reply reply) {
//		System.out.println(model.getAttribute("user"));
//		System.out.println(reply.getUserId());
//		System.out.println(reply.getPostId());
//		System.out.println(reply.getText());
//		System.out.println(reply);
		replyService.insertReply(reply);
		List<Post> postList = postRepository.getPostList();
		//model.addAttribute("postList", postList);
		return postList;
	}

//	@RequestMapping("/goreplywrite")
//	public String goReplyWrite(@ModelAttribute("postId") Post post, Model model) {
//
//		return "replywrite";
//	}

	@RequestMapping("/getuser")
	public List<Post> getUser(@RequestParam("user") User user, Model model) {
		User findUser = userService.getUser(user);
		//model.addAttribute("user", findUser);
		List<Post> postList = postRepository.getPostListWithUserId(findUser);
		//model.addAttribute("postList", postList);
		return postList;
	}

	@RequestMapping("/goprofile")
	public List<Post> goProfile(@RequestParam("user") User user, Model model) {
		User findUser = userService.getUser(user);
		model.addAttribute("user", findUser);
		List<Post> postList = postRepository.getPostListWithUserId(findUser);
		model.addAttribute("postList", postList);
		return postList;
	}

	@RequestMapping("/updateprofile")
	public User updateProfile(User user, Model model) {
		System.out.println(user);
		userService.updateUser(user);
		User findUser = userService.getUser(user);
		//model.addAttribute("user", userService.getUser(user));
		return findUser;
	}

	@RequestMapping("/goupdatepost")
	public Post goUpdatePost(@RequestParam("postId") Post post, Model model) {
		Post findPost = postService.getPost(post);
		model.addAttribute("post", findPost);
		return findPost;
	}

	@RequestMapping("/updatepost")
	public List<Post> updatePost(Post post, Model model) {
		postService.updatePost(post);
		User findUser = userService.getUser(post.getUserId());
		List<Post> postList = postRepository.getPostListWithUserId(findUser);
		//model.addAttribute("postList", postList);
		return postList;
	}

	@RequestMapping("/deletepost")
	public List<Post> deletePost(Post post, Model model, User user) {
		System.out.println(post);
		postService.deletePost(post);
		System.out.println(user);
		List<Post> postList = postRepository.getPostListWithUserId(user);
		//model.addAttribute("postList", postList);
		return postList;
	}

//	@RequestMapping("/getalluser")
//	public List<User> getAllUser() throws InterruptedException, ExecutionException {
//		List<User> listUser = firebaseService.getUserDetails();
//		return listUser;
//	}

}
