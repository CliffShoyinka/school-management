package com.schoolmanagement.controller;

import com.schoolmanagement.payload.request.ContactMessageRequest;
import com.schoolmanagement.payload.response.ContactMessageResponse;
import com.schoolmanagement.service.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("contactMessages")
@RequiredArgsConstructor
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    //NOT: save() ****************************
    @PostMapping("/save")
    public RespsonseMessage<ContactMessageResponse> save(@Valid @RequestBody ContactMessageRequest contactMessageRequest) {

        return contactMessageService.save(contactMessageRequest);


        //2.15.31

    }


    //NOT: getAll() **********************************

    //NOT: searchByEmail() *********************

    //NOT: searchBySubject() ***********************

}
