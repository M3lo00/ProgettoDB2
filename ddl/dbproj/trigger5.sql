use dbproj;


drop trigger if exists newInsolvent;
create trigger newInsolvent
    after insert on `order` for each row
BEGIN
    IF NEW.valid=0 THEN
        UPDATE user
        SET Insolvent = 1, failedPay = failedPay+1
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

drop trigger if exists failedPayment;
create trigger failedPayment
    after update
    on `order`
    for each row
BEGIN
    IF NEW.valid=0 THEN
        update user
        set Insolvent = 1, failedPay = failedPay+1
        where idUser=NEW.refUser;
    end if;
end;

drop trigger if exists auditUser;
create  trigger auditUser
    after update
    on user
    for each row
BEGIN
    IF NEW.failedPay=3 AND NEW.Insolvent=1 THEN

        INSERT INTO audit(refUser, refOrder)
        VALUES (NEW.idUser, (   SELECT o.idOrder
                                FROM `order` o
                                WHERE o.paymentDate=(   SELECT MAX(o1.paymentDate)
                                                        FROM `order` o1
                                                        WHERE o1.refUser=NEW.idUser AND o1.valid=0)));
    end if;
END;

