package pdt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pdt.dao.IlikeRepository;
import pdt.dao.PostRepository;
import pdt.dao.ReplyRepository;
import pdt.dao.UserRepository;
import pdt.entity.Ilike;
import pdt.entity.Post;
import pdt.entity.Reply;
import pdt.entity.User;

@Service
public class ReplyServiceImp implements ReplyService {
	
	@Autowired
	private ReplyRepository replyRepo;
	
	public void insertReply(Reply reply) {
		replyRepo.save(reply);

	}
	
	public void deleteByUserId(User userId) {
		
		replyRepo.deleteByUserId(userId);
		
	}
	
	

	

}
