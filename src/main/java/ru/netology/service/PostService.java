package ru.netology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PostService {

@Autowired
  private final PostRepository repository;
  private final ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public List<Post> all() {
    return repository.all();
  }

  public Post getById(long id) {
    return repository.getById(id);
  }

  public Post save(Post post) {
    return repository.save(post);
  }

  public void removeById(long id) {
    posts.remove(id);
  }
}

