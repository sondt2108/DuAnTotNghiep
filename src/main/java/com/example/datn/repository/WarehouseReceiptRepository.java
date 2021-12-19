package com.example.datn.repository;

import com.example.datn.models.PhieuNhap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseReceiptRepository extends JpaRepository<PhieuNhap, Integer> {
    @Query(value = "Select * from phieunhap where CAST(idpn AS varchar) iLike %:id%", nativeQuery = true)
    Page<PhieuNhap>  findByIdPN(String id, Pageable pager);

	
}
