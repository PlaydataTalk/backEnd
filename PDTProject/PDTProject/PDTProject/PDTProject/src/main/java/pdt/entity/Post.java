package pdt.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@ToString
@DynamicInsert
@DynamicUpdate
@Transactional
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id")
	private User userId;

	@Column(nullable = false)
	private String text;

	@CreatedDate
	private LocalDateTime createDate;

	@LastModifiedDate
	private LocalDateTime updateDate;
	
	@Column(nullable = true)
	private String keyword;

	@Column(nullable = true)
	private String keyword1;
	
	@Column(nullable = true)
	private String keyword2;
	
	@Column(nullable = true)
	private String keyword3;
	
	@ColumnDefault("0")
	private Long likeCount;
	
	@OneToMany(
	        mappedBy = "replyId",
	        fetch = FetchType.LAZY,
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<Reply> reply = new ArrayList<>();
	
	@OneToMany(
	        mappedBy = "likeId",
	        fetch = FetchType.LAZY,
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<Ilike> ilike = new ArrayList<>();

}