package bootstrap;

import java.util.*;

import javax.xml.bind.JAXBException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;
import org.springframework.http.converter.*;
import org.springframework.http.converter.xml.*;

import bootstrap.data.Comment;
import bootstrap.data.Comments;
import bootstrap.service.CommentsService;
import bootstrap.service.InMemCommentsService;
import bootstrap.web.CommentsController;
import bootstrap.web.IndexController;

import org.springframework.http.converter.*;
import org.springframework.http.converter.xml.*;


@EnableWebMvc
@Configuration
public class ApplicationConfiguration extends WebMvcConfigurerAdapter{
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		System.out.println("configureMessageConverters");
		converters.add(new Jaxb2CollectionHttpMessageConverter<List<Comment>>());
		super.configureMessageConverters(converters);
	}

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