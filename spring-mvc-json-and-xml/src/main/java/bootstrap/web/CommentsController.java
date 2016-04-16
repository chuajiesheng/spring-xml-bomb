package bootstrap.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bootstrap.data.Comment;
import bootstrap.data.Comments;
import bootstrap.service.CommentsService;

@Controller
@RequestMapping("/comments")
public class CommentsController {

	@Autowired
	public CommentsService commentsService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Comments getComments() {
		return Comments.comments(commentsService.getComments());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Comment getComment(@PathVariable("id") Long id) {
		for (Comment comment : commentsService.getComments()) {
			if (id.equals(comment.getId())) {
				return comment;
			}
		}
		return new Comment();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String postCommentForm(@ModelAttribute Comment comment) {
		System.out.println("id=" + comment.getId());
		commentsService.addComment(comment);
		return "redirect:comments";
	}
}
