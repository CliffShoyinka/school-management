package com.schoolmanagement.repository;

import com.schoolmanagement.entity.concretes.ContactMessage;
import com.schoolmanagement.payload.response.ContactMessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ContactMessageRepository extends JpaRepository <ContactMessage, Long> {

    boolean existsByEmailEqualsAndDateEquals(String email, LocalDate now);

    Page<ContactMessage> findByEmailEquals(String email, Pageable pageable);

    //turetilebilen bir kod yazmis olduk
    //findBy gibi degil findAll gibi calisiyor aslinda, turetilen methodlar exist ile baslamasi gerekiyor ya da findBy o yuzden findBy ile turettik.
    //collecttion

    Page<ContactMessage> findBySubjectEquals(String subject, Pageable pageable);
}
