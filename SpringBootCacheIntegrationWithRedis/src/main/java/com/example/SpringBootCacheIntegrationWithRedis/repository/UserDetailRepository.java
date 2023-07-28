package com.example.SpringBootCacheIntegrationWithRedis.repository;

import com.example.SpringBootCacheIntegrationWithRedis.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetails,Long> {
  //  List<UserDetails> findAllByIsActiveAndDeletedFlag(boolean b, boolean b1);

    Optional<UserDetails> findByIsActiveAndDeletedFlagAndId(boolean b, boolean b1, Long id);

    List<UserDetails> findAllByIsActiveAndDeletedFlagOrderByIdDesc(boolean b, boolean b1);
}
