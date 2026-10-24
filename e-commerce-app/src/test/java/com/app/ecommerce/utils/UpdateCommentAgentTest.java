package com.app.ecommerce.utils;

import com.app.ecommerce.models.Comment;
import com.app.ecommerce.models.Customer;
import com.app.ecommerce.models.Dictionary;
import com.app.ecommerce.repositories.CommentRepository;
import com.app.ecommerce.repositories.CustomerRepository;
import com.app.ecommerce.repositories.DictionaryRepository;
import com.app.ecommerce.services.DictionaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UpdateCommentAgentTest {
    @Autowired
    DictionaryService dictionaryRepository;

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void inti(){
        populateDatabase();
        Comment comment = new Comment();
        comment.setContent("Good comment but babière");
        comment.setCustomer(customerRepository.getById(1L));
        commentRepository.saveAndFlush(comment);
        Comment comment2 = new Comment();
        comment2.setContent("Good comment but ok");
        comment2.setCustomer(customerRepository.getById(1L));
        commentRepository.saveAndFlush(comment2);
    }
    @Test
    void shouldUpdateComment(){
        int blockCount = commentRepository.findByBlockedIsTrue().size();
        System.out.println("blockedCount"+blockCount);
        Dictionary word = dictionaryRepository.findById(2L);
        System.out.println("******Before******");
        System.out.println(word.getIdWord()+word.getLabel());
        System.out.println("******Before******");
        System.out.println("******AFETER******");
        word.setLabel("bracodji");
        System.out.println(word.getIdWord()+word.getLabel());
        System.out.println("******AFTER******");
        dictionaryRepository.save(word);
        assertTrue(commentRepository.findByBlockedIsTrue().size() != blockCount);

    }
    void populateDatabase(){
        Customer customer = new Customer();
        customer.setFirstName("papittou");
        customerRepository.save(customer);
        dictionaryRepository.saveAll(Arrays.asList(new Dictionary(null,"cool"),new Dictionary(null, "babière")));

    }
}