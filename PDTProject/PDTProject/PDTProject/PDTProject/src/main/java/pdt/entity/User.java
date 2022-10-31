package pdt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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

}
