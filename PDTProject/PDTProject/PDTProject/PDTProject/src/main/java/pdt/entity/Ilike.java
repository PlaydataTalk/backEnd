package pdt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Transactional
@Entity
public class Ilike {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long likeId;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id")
	private User userId;
	
	@ManyToOne(targetEntity = Post.class)
	@JoinColumn(name = "post_id")
	private Post postId;
	
	public Ilike(User userId, Post postId) {
		this.userId=userId;
		this.postId=postId;
	}
	

}
