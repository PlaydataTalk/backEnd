package pdt.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
//@ToString
@Transactional
@Entity
public class Reply {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long replyId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User userId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "post_id")
	private Post postId;
	
	private String text;

}
