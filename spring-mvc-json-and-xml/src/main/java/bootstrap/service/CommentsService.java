package bootstrap.service;

import java.util.List;

import bootstrap.data.Comment;

public interface CommentsService {
	List<Comment> getComments();

	long addComment(Comment comment);
}
