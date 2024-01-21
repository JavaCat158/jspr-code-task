package ru.netology.repository;


import org.springframework.stereotype.Repository;
import ru.netology.model.Post;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;


// Stub
@Repository
public class PostRepository {
  private final AtomicLong counterID= new AtomicLong(0);
  private final ConcurrentHashMap<Long, Post> posts= new ConcurrentHashMap<>();
  public List<Post> all() {
    return new CopyOnWriteArrayList<>(posts.values());
  }

  public Post getById(long id) {
    return posts.get(id);
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      post.setId(counterID.incrementAndGet());
    }
    posts.put(post.getId(), post);
    return post;
  }

  public void removeById(long id) {
    posts.remove(id);
  }
}
