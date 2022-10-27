package pdt.util;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pdt.dao.PostRepository;
import pdt.dao.ReplyRepository;
import pdt.dao.UserRepository;
import pdt.entity.Post;
import pdt.entity.Reply;
import pdt.entity.User;

@Aspect
@Configuration
public class LoadDatabase {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase1(UserRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new User("abc@gmail.com", "1234", "유재석", "MrYou.png")));
			log.info("Preloading " + repository.save(new User("efg@naver.com", "1234", "백종원", "MrBaek.jpg")));
		};
	}

	@Bean
	CommandLineRunner initDatabase2(PostRepository repository) {

		return args -> {
			log.info("Preloading " + repository
					.save(Post.builder().userId(userRepository.findById("abc@gmail.com").get()).text("안녕?").build()));
			log.info("Preloading " + repository.save(
					Post.builder().userId(userRepository.findById("efg@naver.com").get()).text("너 몇기니?").build()));
			log.info("Preloading " + repository.save(
					Post.builder().userId(userRepository.findById("abc@gmail.com").get()).text("왜 반말이야?").build()));
			log.info("Preloading " + repository.save(
					Post.builder().userId(userRepository.findById("efg@naver.com").get()).text("몇기 세요?").build()));
			log.info("Preloading " + repository.save(
					Post.builder().userId(userRepository.findById("abc@gmail.com").get()).text("11기 입니다.").build()));
			log.info("Preloading " + repository.save(
					Post.builder().userId(userRepository.findById("efg@naver.com").get()).text("머리 박으렴").build()));
		};
	}

	@Bean
	CommandLineRunner initDatabase3(ReplyRepository repository) {

		return args -> {
			log.info("Preloading "
					+ repository.save(Reply.builder().userId(userRepository.findById("abc@gmail.com").get()).postId(postRepository.findById(1L).get()).text("댓글달기").build()));

		};
	}
}
