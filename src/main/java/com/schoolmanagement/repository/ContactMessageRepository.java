package com.schoolmanagement.repository;

import com.schoolmanagement.entity.concretes.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository <ContactMessage, Long> {

}
