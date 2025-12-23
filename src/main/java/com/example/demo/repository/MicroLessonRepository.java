// package com.example.demo.repository;

// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;

// import com.example.demo.entity.MicroLesson;

// @Repository
// public interface MicroLessonRepository extends JpaRepository<MicroLesson, Long> {

//     @Query("""
//                 SELECT m FROM MicroLesson m
//                 WHERE (:tags IS NULL OR m.tags LIKE %:tags%)
//                   AND (:difficulty IS NULL OR m.difficulty = :difficulty)
//                   AND (:contentType IS NULL OR m.contentType = :contentType)
//             """)
//     List<MicroLesson> findByFilters(
//             @Param("tags") String tags,
//             @Param("difficulty") String difficulty,
//             @Param("contentType") String contentType);

//     List<MicroLesson> findByCourseId(Long courseId);
// }

package com.example.demo.repository;

import com.example.demo.model.MicroLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MicroLessonRepository extends JpaRepository<MicroLesson, Long> {

    // JPQL query to filter by optional fields: title, difficulty, contentType
    @Query("SELECT m FROM MicroLesson m " +
           "WHERE (:title IS NULL OR m.title = :title) " +
           "AND (:difficulty IS NULL OR m.difficulty = :difficulty) " +
           "AND (:contentType IS NULL OR m.contentType = :contentType)")
    List<MicroLesson> findByFilters(
            @Param("title") String title,
            @Param("difficulty") String difficulty,
            @Param("contentType") String contentType
    );
}
