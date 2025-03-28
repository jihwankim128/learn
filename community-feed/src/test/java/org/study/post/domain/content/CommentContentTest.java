package org.study.post.domain.content;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommentContentTest {

    @Test
    void givenContentLengthIsOk_whenCreateCommentContent_thenReturnTextContent() {
        // given
        String content = "this is a test content";

        // when, then
        CommentContent comment = new CommentContent(content);

        // then
        assertEquals(content, comment.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenCreateCommentContent_thenThrowError() {
        // given
        String content = "a".repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁", "닭", "굵"})
    void givenContentLengthIsOverAndKorean_whenCreateCommentContent_thenThrowError(String koreanContent) {
        // given
        String content = koreanContent.repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

}