package pdt.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import pdt.entity.Post;
import pdt.entity.User;

public interface PostRepository extends JpaRepository<Post, Long> {

	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(value = "select * from post", nativeQuery = true)
	public List<Post> getPostList();

	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("select p from Post p where p.userId = :id")
	public List<Post> getPostListWithUserId(@Param("id") User userId);

	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("update Post p set p.likePost = p.likePost + 1 where p.postId = :id")
	public void updateIlike(@Param("id") Long postId);

	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("update Post p set p.likePost = p.likePost - 1 where p.postId = :id")
	public void updateIlike2(@Param("id") Long postId);

}
