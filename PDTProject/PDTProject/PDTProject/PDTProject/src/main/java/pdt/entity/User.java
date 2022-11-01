package pdt.entity;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@ToString
@Transactional
@Entity
public class User {
	
	@Id
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private String pw;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = true)
	private String imgUrl;
	
	@OneToMany(
	        mappedBy = "postId",
	        fetch = FetchType.LAZY,
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<Post> post = new ArrayList<>();
	
	@OneToMany(
	        mappedBy = "likeId",
	        fetch = FetchType.LAZY,
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<Ilike> ilike = new ArrayList<>();
	
	@OneToMany(
	        mappedBy = "replyId",
	        fetch = FetchType.LAZY,
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<Reply> reply = new ArrayList<>();

}
