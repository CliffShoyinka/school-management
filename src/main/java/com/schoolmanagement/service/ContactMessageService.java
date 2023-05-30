package com.schoolmanagement.service;

import com.schoolmanagement.payload.request.ContactMessageRequest;
import com.schoolmanagement.payload.response.ContactMessageResponse;
import com.schoolmanagement.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor //final fieldlardan cons olusturuyor
public class ContactMessageService {

    private ContactMessageRepository contactMessageRepository;

    //NOT save() methodu **********************
    public RespsonseMessage<ContactMessageResponse> save(ContactMessageRequest contactMessageRequest) {

        //ayni kisi ayni gun icinde sadece 1 defa mesaj gonderebilsin
        boolean isSameMessageWithSameEmailForToday =
                contactMessageRepository.existsByEmailEqualsAndDateEquals(contactMessageRequest.getEmail(), LocalDate.now());

        if (isSameMessageWithSameEmailForToday) throw new
    }
}
