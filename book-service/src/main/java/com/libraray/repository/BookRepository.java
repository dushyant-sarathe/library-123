package com.libraray.repository;

import com.libraray.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query("SELECT b FROM BookEntity b WHERE b.title = :title AND b.author = :author")
    List<BookEntity> findByTitleAndAuthor(@Param("title") String title, @Param("author") String author);
}
