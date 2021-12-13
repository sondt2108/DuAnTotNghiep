package com.example.datn.repository;

import java.util.List;

import com.example.datn.models.PercentReview;
import com.example.datn.models.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PercentRiviewRepository extends JpaRepository<Review, Integer>{
	
	
    

    @Query(value = "select rv.id, score, count(*) * 100.0 / sum(count(*)) over() as scorePercent From review rv inner join products pr"+ 
    " on pr.product_id = rv.product_id  where pr.product_id = 6"+
    " group by score, , rv.id", nativeQuery = true)
    List<PercentReview> findByScorePercent();
}