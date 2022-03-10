create table insolventUser
(
    insolvent_id        int not null primary key,
    constraint insolvent_fk
        foreign key (insolvent_id) references user (idUser)
);

create trigger newInsolvent
    after insert on `order` for each row
BEGIN
    IF NEW.valid=0 THEN
        INSERT INTO insolventUser(insolvent_id)
        VALUES (NEW.refUser);
    end if;
end;

create trigger noMoreInsolvent
    after update on `order` for each row
BEGIN
    IF NEW.valid=1 THEN
        DELETE FROM insolventUser i WHERE i.insolvent_id = NEW.refUser;
    end if;
end;

create table suspOrder
(
    order_id        int not null primary key,
    constraint order_fk
        foreign key (order_id) references `order` (idOrder)
);

create trigger newSuspended
    after insert on `order` for each row
BEGIN
    IF NEW.valid=0 THEN
        INSERT INTO suspOrder(order_id)
        VALUES (NEW.idOrder);
    end if;
end;

create trigger payedOrder
    after update on `order` for each row
BEGIN
    IF NEW.valid=1 THEN
        DELETE FROM suspOrder i WHERE i.order_id = NEW.idOrder;
    end if;
end;