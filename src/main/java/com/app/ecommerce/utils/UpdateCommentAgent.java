//package com.app.ecommerce.utils;
//
//import com.app.ecommerce.models.Dictionary;
//import com.app.ecommerce.repositories.CommentRepository;
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.PostPersist;
//import javax.persistence.PostUpdate;
//
//@Component
//public class UpdateCommentAgent {
//
//    private final CommentRepository commentRepository;
//
//
//    public UpdateCommentAgent(@Lazy CommentRepository repository ){
//        this.commentRepository = repository;
//    }
//
//    @PostPersist
//    public void onPersist(Dictionary dictionary){
//
//        check(dictionary.getLabel());
//    }
//    @PostUpdate
//    public void onUpdate(final Dictionary dictionary){
//        check(dictionary.getLabel());
//    }
//    void check(String label){
//       try{
//           if(commentRepository.count()!=0){
//               commentRepository.findAll().forEach(comment -> {
//               if(comment.getContent().contains(label)){
//                   if(comment.isBlocked()){
//                       comment.setBlocked(true);
//                   };
//                   commentRepository.save(comment);
//               }
//           });
//           }
//        }catch (Exception e){
//           System.out.println(e.getMessage());
//       }
//    }
//
//}
