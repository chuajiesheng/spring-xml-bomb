package bootstrap.service;

import java.util.LinkedList;
import java.util.List;

import bootstrap.data.Comment;

public class InMemCommentsService implements CommentsService {

	private long nextId = 0;
	private List<Comment> comments = new LinkedList<Comment>();

	public InMemCommentsService() {
		Comment comment = new Comment();
		comment.setAuthorName("John Doe");
		comment.setAuthorEmail("");
		comment.setAuthorUrl("");
		comment.setMessage("Hello, world!");
		addComment(comment);
	}

	@Override
	public List<Comment> getComments() {
		return comments;
	}

	@Override
	public synchronized long addComment(Comment comment) {
		long id = nextId;
		nextId = nextId + 1;
		comment.setId(id);
		comments.add(comment);
		return id;
	}
}
