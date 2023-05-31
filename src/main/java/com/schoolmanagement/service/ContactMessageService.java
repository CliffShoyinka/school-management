package com.schoolmanagement.service;

import com.schoolmanagement.entity.concretes.ContactMessage;
import com.schoolmanagement.exception.ConflictException;
import com.schoolmanagement.payload.request.ContactMessageRequest;
import com.schoolmanagement.payload.response.ContactMessageResponse;
import com.schoolmanagement.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.schoolmanagement.utils.Messages.ALREADY_SEND_A_MESSAGE_TODAY;

@Service
@RequiredArgsConstructor //final fieldlardan cons olusturuyor
public class ContactMessageService {

    private ContactMessageRepository contactMessageRepository;

    //NOT save() methodu **********************
    public RespsonseMessage<ContactMessageResponse> save(ContactMessageRequest contactMessageRequest) {

        //ayni kisi ayni gun icinde sadece 1 defa mesaj gonderebilsin
        boolean isSameMessageWithSameEmailForToday =
                contactMessageRepository.existsByEmailEqualsAndDateEquals(contactMessageRequest.getEmail(), LocalDate.now());

        if (isSameMessageWithSameEmailForToday) throw new ConflictException(String.format(ALREADY_SEND_A_MESSAGE_TODAY));

        //DTO-POJO donusumu

        ContactMessage  contactMessage = createObject(contactMessageRequest);
        ContactMessage savedDate =  contactMessageRepository.save(contactMessage); //save methodu bize kaydedecegimiz datayi donduruyor


    }

    //DTO-POJO donusumu icin yardimci method
    private ContactMessage createObject(ContactMessageRequest contactMessageRequest) {

        return ContactMessage.builder()
                .name(contactMessageRequest.getMessage()) //name kisminda builder vasitasiyla getter setterla ugrasmiyoruz. builder sayesinde sadece ismini belirledigimiz bir contact message olusturabiliriz.
                .subject(contactMessageRequest.getSubject())  //builder sayesinde istedigimiz kadar datayi setleyip gonderebiliyoruz eger not null yoksa fieldlarin icerisinde. not null olanlari sadece setleyip gonderebiliriz.
                .message(contactMessageRequest.getMessage())
                .email(contactMessageRequest.getEmail())
                .date(LocalDate.now())
                .build(); //build dedigimiz anda bize argumanlarla resetlenmis yeni bir contact message nesnesi olusturur

                //Idyi kullanmiyoruz cunku idyi oto generate kullandik

        //20dk



    }
}
