package com.example.demo.Repository;

import com.example.aouth.Entity.UserOAUTH2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserOAUTH2, UUID> {
}
