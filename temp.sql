select *
from package

insert into package (name, sms)
    values ('prova1', 12);

drop trigger newPackage

CREATE TRIGGER newPackage
    AFTER INSERT ON package FOR EACH ROW
BEGIN
    INSERT INTO totpurchaseperpackandvalidity(package_id, periodo)
    VALUES (NEW.idPackage, 12);
    INSERT INTO totpurchaseperpackandvalidity(package_id, periodo)
    VALUES (NEW.idPackage, 24);
    INSERT INTO totpurchaseperpackandvalidity(package_id, periodo)
    VALUES (NEW.idPackage, 36);
end;

drop table totpurchaseperpackandvalidity;

create table totpurchaseperpackandvalidity
(
    package_id      int not null,
    periodo         int not null,
    totalPurchases  int default 0 not null,
    constraint primary key (package_id, periodo),
    constraint numberOfTotalPurchasesPerPackage_fk0
        foreign key (package_id) references package (idPackage)
);

insert into `order` (refUser, refPack, creationDate, startDate, periodo, totalAmount, valid)
    VALUES (4,5, CURRENT_DATE, current_date, 12, 15, 1);

select *
from `order`
WHERE valid=true

create trigger updatePurchaseToPackage
    after update
    on `order`
    for each row
BEGIN
    IF NEW.valid = true THEN
        UPDATE totpurchaseperpackandvalidity
        SET totalPurchases = totalPurchases + 1
        WHERE package_id =NEW.refPack AND periodo=NEW.periodo;
    END IF;
END;