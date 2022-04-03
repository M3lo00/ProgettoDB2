use dbproj;

create trigger newInsolvent
    after insert on `order` for each row
BEGIN
    IF NEW.valid=0 THEN
        UPDATE user
        SET Insolvent = 1
        WHERE idUser=NEW.refUser;
    end if;
end;

create trigger noMoreInsolvent
    after update
    on `order`
    for each row
BEGIN
    IF NEW.valid=1 AND 0=(  SELECT COUNT(*)
                            FROM `order` o
                            WHERE o.refUser=NEW.refUser AND o.valid=0) THEN
        update user
        set Insolvent=0
        where NEW.refUser=user.idUser;
    end if;
end;

create trigger failedPayment
    after update
    on `order`
    for each row
BEGIN
    IF NEW.valid=0 THEN
        update user
        set failedPay = failedPay+1
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
        VALUES (NEW.idUser, (   SELECT MAX(o.idOrder)
                                FROM `order` o
                                WHERE o.refUser=NEW.idUser AND o.valid=0));
    end if;
END;

