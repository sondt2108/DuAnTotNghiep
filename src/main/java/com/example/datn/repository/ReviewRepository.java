package com.example.datn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.datn.models.PercentReview;
import com.example.datn.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
	
	
        @Query(value = "SELECT * FROM review r inner join products p on r.product_id = p.product_id  where phone_number =  :phoneNumber  and p.product_id = :product_id", nativeQuery = true)
        Review findByReview(@Param(value = "phoneNumber") String phoneNumber ,@Param(value = "product_id") int product_id);
        
        @Query(value = "select * from review where product_id = :id", nativeQuery = true)
        List<Review> findByProductId(@Param(value = "id") int id );

        @Query(value = "select score, count(*) * 100.0 / sum(count(*)) over() as scorePercent From review rv inner join products pr"+ 
        " on pr.product_id = rv.product_id  where pr.product_id = 6"+
        " group by score", nativeQuery = true)
        List<Review> findByScorePercent();
    }

