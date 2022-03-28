use dbproj;


create trigger newInsolvent
    after insert on `order` for each row
BEGIN
    DECLARE alreadyIns int;
    SET alreadyIns=(
    SELECT COUNT(*)
    FROM dbproj.insolventUser
    WHERE insolvent_id=NEW.refUser);
    IF NEW.valid=0 AND alreadyIns=0 THEN
        INSERT INTO insolventUser(insolvent_id)
        VALUES (NEW.refUser);
    end if;
end;

create trigger noMoreInsolvent
    after update
    on `order`
    for each row
BEGIN
    IF NEW.valid=1 THEN
        DELETE FROM insolventUser i WHERE i.insolvent_id = NEW.refUser;
        update user
        set Insolvent=0
        where NEW.refUser=user.idUser;
    end if;
end;



create trigger newSuspended
    after insert
    on `order`
    for each row
BEGIN
    IF NEW.valid=0 THEN
        update user
        SET failedPay = failedPay +1,
            Insolvent=1
        WHERE idUser=NEW.refUser;
    end if;
end;

create trigger failedPayment
    after update
    on `order`
    for each row
BEGIN
    IF NEW.valid=0 THEN
        update user
        set failedPay = failedPay+1,
            Insolvent=1
        where idUser=NEW.refUser;
    end if;
end;


create  trigger auditUser
    after update
    on user
    for each row
BEGIN
    IF NEW.failedPay=3 AND NEW.Insolvent=1 THEN
        INSERT INTO audit(refUser, refOrder)
        VALUES (NEW.idUser, ( SELECT MAX(o.idOrder)
                              FROM `order` o
                              WHERE o.refUser=NEW.idUser));
    end if;
END;

