package org.example.repository;

import org.example.model.Singer;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository

public interface SingerRepository extends JpaRepository<Singer, Long> {

}
