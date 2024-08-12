create table Order {
    id bigint(20) NOT NULL AUTO_INCREMENT,
    orderNumber varchar(255) DEFAULT NULL,
    orderLineItemId: bigint(20) NOT NULL
}

create table OrderLineItems {
    id bigint(20) NOT NULL AUTO_INCREMENT,
    skuCode varchar(255) NOT NULL,
    price double default NOT NULL,
    quantity int NOT NULL
}