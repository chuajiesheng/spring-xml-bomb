package bootstrap;

import java.util.Arrays;

import javax.xml.bind.JAXBException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;

import bootstrap.data.Comment;
import bootstrap.data.Comments;
import bootstrap.service.CommentsService;
import bootstrap.service.InMemCommentsService;
import bootstrap.web.CommentsController;
import bootstrap.web.IndexController;

@EnableWebMvc
@Configuration
public class ApplicationConfiguration {

	@Bean
	public CommentsService commentsService() {
		return new InMemCommentsService();
	}

	@Bean
	public IndexController indexController() {
		return new IndexController();
	}

	@Bean
	public CommentsController commentsController() {
		return new CommentsController();
	}

	@Bean
	public ViewResolver viewResolver() throws JAXBException {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setClassesToBeBound(Comments.class, Comment.class);
		MarshallingView marshallingView = new MarshallingView(jaxb2Marshaller);

		ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
		contentNegotiatingViewResolver.setDefaultViews(Arrays.<View> asList(new MappingJacksonJsonView(), marshallingView));
		return contentNegotiatingViewResolver;
	}
}
