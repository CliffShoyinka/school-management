package com.schoolmanagement.service;

import com.schoolmanagement.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //final fieldlardan cons olusturuyor
public class ContactMessageService {

    private ContactMessageRepository contactMessageRepository;

}
