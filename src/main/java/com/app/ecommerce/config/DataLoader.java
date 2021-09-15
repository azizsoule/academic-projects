package com.app.ecommerce.config;

import com.app.ecommerce.models.OrderState;
import com.app.ecommerce.models.Privilege;
import com.app.ecommerce.models.User;
import com.app.ecommerce.models.UserGroup;
import com.app.ecommerce.repositories.OrderStateRepository;
import com.app.ecommerce.services.PrivilegeService;
import com.app.ecommerce.services.UserGroupService;
import com.app.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserService userService;

    @Autowired
    UserGroupService userGroupService;

    @Autowired
    PrivilegeService privilegeService;

    @Autowired
    OrderStateRepository orderStateRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(initialDataAlreadySetup()) return;
        List<OrderState> orderStates = new ArrayList<>(Arrays.asList(
                new OrderState("PENDING", "En cours"),
                new OrderState("COMPLETED", "Terminnée"),
                new OrderState("CANCELED", "Annulée")
        ));
        orderStateRepository.saveAll(orderStates);
        String [] accessList = {"CATALOG", "CATEGORIE", "SUB_CATEGORIE",};
        List<String> privileges = Arrays.asList(accessList);
        List<Privilege> savedPrivileges= new ArrayList<>();
        privileges.forEach(privilege->{
            savedPrivileges.add(createPrivilegeIfNotFound("CREATE_"+privilege,"Privilège permettant de créer un objet "+privilege.toLowerCase())) ;
            savedPrivileges.add(createPrivilegeIfNotFound("READ_"+privilege,"Privilège permettant de consulter un objet "+privilege.toLowerCase()));
            savedPrivileges.add(createPrivilegeIfNotFound("UPDATE_"+privilege,"Privilège permettant de modifier un objet "+privilege.toLowerCase()));
            savedPrivileges.add(createPrivilegeIfNotFound("DELETE_"+privilege,"Privilège permettant de supprimer un objet "+privilege.toLowerCase()));
        });
        UserGroup sadminGroup = createGroupeIfNotFound("SUPER_ADMIN",savedPrivileges);
        User user = new User("sa","sapassword");
        user.setUserGroup(sadminGroup);
        userService.register(user);
    }
    @Transactional
    Privilege createPrivilegeIfNotFound(String name, String description) {
        Privilege privilege = new Privilege(name,description);
        try {
            return privilegeService.save(privilege);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Transactional
    UserGroup createGroupeIfNotFound(String libelle, List<Privilege> privileges) {
        UserGroup groupe = new UserGroup(libelle);
        Set<Privilege> privilegeSet = new HashSet<>(privileges);
        groupe.setPrivileges(privilegeSet);
        try {
            return userGroupService.save(groupe);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    boolean initialDataAlreadySetup(){
        return privilegeService.count()!=0 && userGroupService.count()!=0;
    }
}
