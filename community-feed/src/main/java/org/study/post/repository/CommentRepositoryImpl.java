package org.study.post.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.study.post.application.interfaces.CommentRepository;
import org.study.post.domain.comment.Comment;
import org.study.post.repository.entity.comment.CommentEntity;
import org.study.post.repository.jpa.JpaCommentRepository;
import org.study.post.repository.jpa.JpaPostRepository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;
    private final JpaPostRepository jpaPostRepository;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        CommentEntity commentEntity = new CommentEntity(comment);
        if (commentEntity.getId() != null) {
            jpaCommentRepository.updateComment(commentEntity);
            return commentEntity.toComment();
        }

        jpaPostRepository.incrementCommentCount(comment.getPost().getId());
        return jpaCommentRepository.save(commentEntity).toComment();
    }

    @Override
    public Comment findById(Long id) {
        return jpaCommentRepository.findById(id)
            .orElseThrow(IllegalArgumentException::new)
            .toComment();
    }

}
