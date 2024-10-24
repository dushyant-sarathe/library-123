package com.libraray.repository;

import com.libraray.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

   // List<BookEntity> findByTitle(String title, String author);
}
