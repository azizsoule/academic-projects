package com.app.ecommerce.listeners;

import com.app.ecommerce.services.*;
import com.app.ecommerce.utils.Table;
import io.debezium.config.Configuration;
import io.debezium.embedded.Connect;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.RecordChangeEvent;
import io.debezium.engine.format.ChangeEventFormat;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static io.debezium.data.Envelope.FieldName.*;
import static io.debezium.data.Envelope.Operation;
import static java.util.stream.Collectors.toMap;

@Slf4j
@Component
public class DebeziumListener {

    private final Executor executor = Executors.newSingleThreadExecutor();
    private final DebeziumEngine<RecordChangeEvent<SourceRecord>> debeziumEngine;
    @Autowired
    AdressService adressService;
    @Autowired
    ArticleService articleService;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    CatalogService catalogService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CityService cityService;
    @Autowired
    CommentService commentService;
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerTypeService customerTypeService;
    @Autowired
    DictionaryService dictionaryService;
    @Autowired
    DiscountService discountService;
    @Autowired
    ImageService imageService;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderStateService orderStateService;
    @Autowired
    PaymentDetailService paymentDetailService;
    @Autowired
    PaymentMethodService paymentMethodService;
    @Autowired
    PrivilegeService privilegeService;
    @Autowired
    PubService pubService;
    @Autowired
    RegionService regionService;
    @Autowired
    SexService sexService;
    @Autowired
    SubCategoryService subCategoryService;
    @Autowired
    UserGroupService userGroupService;
    @Autowired
    UserService userService;
    @Autowired
    WishItemService wishItemService;

    public DebeziumListener(Configuration customerConnectorConfiguration) {

        debeziumEngine = DebeziumEngine.create(ChangeEventFormat.of(Connect.class))
                .using(customerConnectorConfiguration.asProperties())
                .notifying(this::handleChangeEvent)
                .build();
    }

    private void handleChangeEvent(RecordChangeEvent<SourceRecord> sourceRecordRecordChangeEvent) {
        SourceRecord sourceRecord = sourceRecordRecordChangeEvent.record();

        log.info("Key = '" + sourceRecord.key() + "' value = '" + sourceRecord.value() + "'");

        Struct sourceRecordChangeValue = (Struct) sourceRecord.value();
        if (sourceRecordChangeValue != null) {
            Operation operation = Operation.forCode((String) sourceRecordChangeValue.get(OPERATION));
            Struct source = (Struct) sourceRecordChangeValue.get(SOURCE);
            String tableName = (String) source.get("table");
            if (operation != Operation.READ) {
                String record = operation == Operation.DELETE ? BEFORE : AFTER; // Handling Update & Insert operations.

                Struct struct = (Struct) sourceRecordChangeValue.get(record);
                Map<String, Object> payload = struct.schema().fields().stream()
                        .map(Field::name)
                        .filter(fieldName -> struct.get(fieldName) != null)
                        .map(fieldName -> Pair.of(fieldName, struct.get(fieldName)))
                        .collect(toMap(Pair::getKey, Pair::getValue));
                switch (tableName) {
                    case Table.ADDRESS:
                        adressService.replicateData(payload, operation);
                        break;
                    case Table.ADMIN_USER:
                        userService.replicateData(payload, operation);
                        break;
                    case Table.ARTICLE:
                        articleService.replicateData(payload, operation);
                        break;
                    case Table.CART_ITEM:
                        cartItemService.replicateData(payload, operation);
                        break;
                    case Table.CATALOG:
                        catalogService.replicateData(payload, operation);
                        break;
                    case Table.CATEGORY:
                        categoryService.replicateData(payload, operation);
                        break;
                    case Table.CITY:
                        cityService.replicateData(payload, operation);
                        break;
                    case Table.CUSTOMER:
                        customerService.replicateData(payload, operation);
                        break;
                    case Table.CUSTOMER_COMMENT:
                        commentService.replicateData(payload, operation);
                        break;
                    case Table.CUSTOMER_TYPE:
                        customerTypeService.replicateData(payload, operation);
                        break;
                    case Table.DICTIONARY:
                        dictionaryService.replicateData(payload, operation);
                        break;
                    case Table.DISCOUNT:
                        discountService.replicateData(payload, operation);
                        break;
                    case Table.IMAGE:
                        imageService.replicateData(payload, operation);
                        break;
                    case Table.INVENTORY:
                        inventoryService.replicateData(payload, operation);
                        break;
                    case Table.ORDER_DETAIL:
                        orderService.replicateData(payload, operation);
                        break;
                    case Table.ORDER_ITEM:
                        orderItemService.replicateData(payload, operation);
                        break;
                    case Table.ORDER_STATE:
                        orderStateService.replicateData(payload, operation);
                        break;
                    case Table.PAYMENT_DETAIL:
                        paymentDetailService.replicateData(payload, operation);
                        break;
                    case Table.PAYMENT_METHOD:
                        paymentMethodService.replicateData(payload, operation);
                        break;
                    case Table.PRIVILEGE:
                        privilegeService.replicateData(payload, operation);
                        break;
                    case Table.PUB:
                        pubService.replicateData(payload, operation);
                        break;
                    case Table.REGION:
                        regionService.replicateData(payload, operation);
                        break;
                    case Table.SEX:
                        sexService.replicateData(payload, operation);
                        break;
                    case Table.SUB_CATEGORY:
                        subCategoryService.replicateData(payload, operation);
                        break;
                    case Table.USER_GROUP:
                        userGroupService.replicateData(payload, operation);
                        break;
                    case Table.WISH_ITEM:
                        wishItemService.replicateData(payload, operation);
                        break;
                    default:
                        System.out.println("Pas de service configuré pour cet table");
                        break;
                }
                log.info("Updated Data: {} with Operation: {}", payload, operation.name());
            }
        }
    }

    @PostConstruct
    private void start() {
        this.executor.execute(this.debeziumEngine);
    }

    @PreDestroy
    private void stop() throws IOException {
        if (this.debeziumEngine != null) {
            this.debeziumEngine.close();
        }
    }

}
