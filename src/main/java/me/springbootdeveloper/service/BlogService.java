package me.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.springbootdeveloper.config.error.exception.ArticleNotFoundException;
import me.springbootdeveloper.domain.Article;
import me.springbootdeveloper.dto.AddArticleRequest;
import me.springbootdeveloper.dto.UpdateArticleRequest;
import me.springbootdeveloper.repository.BlogRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor //final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service //빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    //블로그 글 추가 메서드
    public Article save(AddArticleRequest request, String userName) {
        return blogRepository.save(request.toEntity(userName));
    }

    //블로그 목록 조회 메서드
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    //블로그 글 조회 메서드
    public Article findById(Long id) {
        return blogRepository.findById(id).orElseThrow(ArticleNotFoundException::new);
    }

    //블로그 글 삭제 메서드
    public void delete(Long id) {
        Article article = blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        authorizeArticleAuthor(article);
        blogRepository.delete(article);
    }

    //블로그 글 수정 메서드
    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        authorizeArticleAuthor(article);
        article.update(request.getTitle(), request.getContent());

        return article;
    }

    //게시글 작성 유저인지 확인
    private static void authorizeArticleAuthor(Article article) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        if(!article.getAuthor().equals(userName)) {
            throw new IllegalArgumentException("not authorized");
        }
    }
}
