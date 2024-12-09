//package com.example.sociography.repository;
//
//import com.example.sociography.model.Request;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//
//public interface RequestRepository extends JpaRepository<Request, Integer> {
//
//    @Query("SELECT r FROM Request r JOIN FETCH r.partner p JOIN FETCH r.photographer WHERE r.photographer.id = :photographerId AND r.status = :status")
//    List<Request> findByPhotographerIdAndStatus(Long photographerId, String status);
//}
//
package com.example.sociography.repository;

import com.example.sociography.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer> {

    @Query("SELECT r FROM Request r " +
           "LEFT JOIN FETCH r.partner p " +
           "LEFT JOIN FETCH r.photographer ph " +
           "WHERE r.recipientType = :recipientType " +
           "AND r.recipientId = :recipientId " +
           "AND r.status = :status")
    List<Request> findByRecipientTypeAndRecipientIdAndStatus(@Param("recipientType") String recipientType,
                                                             @Param("recipientId") int recipientId,
                                                             @Param("status") String status);
}
