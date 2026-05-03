package ru.practicum.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.dto.comment.AdminUpdateCommentStatusDto;
import ru.practicum.dto.comment.CommentDto;
import ru.practicum.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/comments")
public class CommentAdminController {
    private final CommentService commentService;

    @GetMapping
    public List<CommentDto> adminPendigCommentList(@RequestParam(required = false) List<Long> users) {
        return commentService.adminPendigCommentList(users);
    }

    @PatchMapping("/{commentId}")
    public CommentDto adminUpdateCommentStatus(@PathVariable("commentId") Long commentId,
                                               @Valid @RequestBody AdminUpdateCommentStatusDto dto) {
        return commentService.adminUpdateCommentStatus(commentId, dto);
    }

    @DeleteMapping("/{commentId}")
    public void adminDeleteComment(@PathVariable Long commentId) {
        commentService.adminDeleteComment(commentId);
    }
}
