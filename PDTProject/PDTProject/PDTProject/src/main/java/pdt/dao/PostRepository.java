package pdt.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import pdt.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(value = "select * from post", nativeQuery = true)
	public List<Post> getPostList();
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("update Post p set p.likePost = p.likePost + 1 where p.postId = :id")
	public void updateIlike(@Param("id") Long postId);
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("update Post p set p.likePost = p.likePost - 1 where p.postId = :id")
	public void updateIlike2(@Param("id") Long postId);

}
