package org.study.post.repository.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.study.post.repository.entity.post.PostEntity;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

    @Modifying
    @Query(value = """
        update PostEntity p set p.content = :#{#postEntity.getContent()},
        p.state = :#{#postEntity.getState()}, p.updDt = now()
        where p.id = :#{#postEntity.getId()}
        """)
    void updatePostEntity(PostEntity postEntity);

    @Modifying
    @Query("""
        update PostEntity p set p.likeCount = p.likeCount + :likeCount, p.updDt = now()
        WHERE p.id = :postId
        """)
    void updateLikeCount(Long postId, Integer likeCount);

    @Modifying
    @Query("""
        update PostEntity p set p.commentCount = p.commentCount + 1, p.updDt = now()
        WHERE p.id = :id
        """)
    void incrementCommentCount(Long id);

    @Query("select p from PostEntity p where p.author.id = :authorId")
    List<PostEntity> findAllPostIdsByAuthorId(Long authorId);

}
