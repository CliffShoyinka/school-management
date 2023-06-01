package com.schoolmanagement.service;

import com.schoolmanagement.entity.concretes.ContactMessage;
import com.schoolmanagement.exception.ConflictException;
import com.schoolmanagement.payload.request.ContactMessageRequest;
import com.schoolmanagement.payload.response.ContactMessageResponse;
import com.schoolmanagement.payload.response.ResponseMessage;
import com.schoolmanagement.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

import static com.schoolmanagement.utils.Messages.ALREADY_SEND_A_MESSAGE_TODAY;

@Service
@RequiredArgsConstructor //final fieldlardan cons olusturuyor
public class ContactMessageService {

    private ContactMessageRepository contactMessageRepository;

    //NOT save() methodu **********************
    public ResponseMessage<ContactMessageResponse> save(ContactMessageRequest contactMessageRequest) {

        //ayni kisi ayni gun icinde sadece 1 defa mesaj gonderebilsin
        boolean isSameMessageWithSameEmailForToday =
                contactMessageRepository.existsByEmailEqualsAndDateEquals(contactMessageRequest.getEmail(), LocalDate.now());

        if (isSameMessageWithSameEmailForToday) throw new ConflictException(String.format(ALREADY_SEND_A_MESSAGE_TODAY));

        //DTO-POJO donusumu

        ContactMessage  contactMessage = createObject(contactMessageRequest);
        ContactMessage savedDate =  contactMessageRepository.save(contactMessage); //save methodu bize kaydedecegimiz datayi donduruyor

        return ResponseMessage.<ContactMessageResponse>builder()
                .message("Contact message created successfully")
                .httpStatus(HttpStatus.CREATED)
                .object(createResponse(savedDate))
                .build();

        //ResponsMessage pojo, pojoya donusturmek istemedigimiz icin ona gore objenin icine parametre vermemiz gerekiyor, dtoya cevirmemiz lazim. objem burada setlendi.
        //createResponse un oldugu yere gidicez oradan koseli parantezlerin icini doldurucaz
                //tur donusum problemi generics yapilardan kaynakli bir hata var

                //<~> ResponseMessage sinifindan alacagi parametrelerden birisi obje data turunde yani obje data turu neyse onu gonder demis oluyoruz.

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

    }

    //!!! POJO - DTO donusumu icin yardimci method

    private ContactMessageResponse createResponse(ContactMessage contactMessage) {
        return ContactMessageResponse.builder()
                .subject(contactMessage.getSubject())
                .message(contactMessage.getMessage())
                .email(contactMessage.getEmail())
                .date(contactMessage.getDate())
                .build();
    }

    public Page<ContactMessageResponse> getAll(int page, int size, String sort, String type) {

        PageRequest pageable = PageRequest.of(page, size, Sort.by(sort).ascending()); //normalde ascending olarak calis ama hic bir sey girmediyse default da sortlamayi da dicending olarak yap diyecegiz

        if (Objects.equals(type, "desc")) {

            pageable = PageRequest.of(page, size, Sort.by(sort).descending()); //ikisini de descending olarak ayarladik
        }

        return contactMessageRepository.findAll(pageable).map(this::createResponse); //response olmasi lazim, pojolar geldi dto olmasi lazim // this kendinden onceki gelen datayi temsil ediyor
        //return contactMessageRepository.findAll(pageable).map(r -> createResponse(r))
    }

    //NOT searchByEmail() methodu ***************************************************
    public Page<ContactMessageResponse> searchByEmail(String email, int page, int size, String sort, String type) {
        Pageable pageable = PageRequest.of(page,size,Sort.by(sort).ascending());

        if (Objects.equals(type, "desc")) {

            pageable = PageRequest.of(page, size, Sort.by(sort).descending()); //ikisini de descending olarak ayarladik
        }

        return contactMessageRepository.findByEmailEquals(email, pageable).map(this::createResponse); //findAllById pageable yapida calismiyor, pageable yapida calisan tek bir yapi var o da findAll methodu o zaman kendimiz yazicaz burayi
        //map lazim burada burada bize pojo geliyor biz bunu istemiyoruz
    }

    //NOT searchBySubject() methodu ***************************************************
    public Page<ContactMessageResponse> searchBySubject(String subject, int page, int size, String sort, String type) {

        Pageable pageable = PageRequest.of(page,size,Sort.by(sort).ascending());

        if (Objects.equals(type, "desc")) {

            pageable = PageRequest.of(page, size, Sort.by(sort).descending()); //ikisini de descending olarak ayarladik
        }
        return contactMessageRepository.findBySubjectEquals(subject, pageable).map(this::createResponse);
    }
}
