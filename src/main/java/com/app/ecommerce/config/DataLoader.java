package com.app.ecommerce.config;

import com.app.ecommerce.models.*;
import com.app.ecommerce.repositories.OrderStateRepository;
import com.app.ecommerce.repositories.PaymentMethodRepository;
import com.app.ecommerce.repositories.RegionRepository;
import com.app.ecommerce.repositories.SexRepository;
import com.app.ecommerce.services.*;
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
    RegionRepository regionRepository;

    @Autowired
    OrderStateRepository orderStateRepository;
    @Autowired
    PaymentMethodRepository paymentMethodRepository;
    @Autowired
    SexRepository sexRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(initialDataAlreadySetup()) return;
        List<OrderState> orderStates = new ArrayList<>(Arrays.asList(
                new OrderState("PENDING", "En cours"),
                new OrderState("COMPLETE", "Terminnée"),
                new OrderState("CANCELED", "Annulée")
        ));
        List<PaymentMethod> paymentMethods = new ArrayList<>(Arrays.asList(
                new PaymentMethod("Paiement à la livraison", true,"YOU"),
                new PaymentMethod("Orange Money",true, "ORANGE"),
                new PaymentMethod("Moov Money",true, "MOOV"),
                new PaymentMethod("MO MO",true, "MTN"),
                new PaymentMethod("VISA",false, "VISA"),
                new PaymentMethod("PAYPAL",false, "PAYPAL")
        ));
        List<Sex> sexes = new ArrayList<>(Arrays.asList(
                new Sex("HOMME"),
                new Sex("FEMME"),
                new Sex("AUTRE")
        ));
        List<Region> regions = new ArrayList<>(Arrays.asList(
                new Region("Région des lagunes"),
                new Region("Région de l'agneby tiassa"),
                new Region("Région du moronou")
        ));
        orderStateRepository.saveAll(orderStates);
        paymentMethodRepository.saveAll(paymentMethods);
        sexRepository.saveAll(sexes);
        regionRepository.saveAll(regions);
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
