#aggiungere una tupla ogni volta che viene creato un pack (INT)
#incrementare il pack ogni volta che viene acquistato
#Due modalità di acquisto ( valid, valid dopo not valid)

create table totpurchaseperpackandvalidity
(
    package_id      int not null,
    periodo         int not null,
    totalPurchases  int default 0 not null,
    constraint primary key (package_id, periodo),
    constraint numberOfTotalPurchasesPerPackage_fk0
        foreign key (package_id) references package (idPackage)
);

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

create trigger addPurchaseToPackage
after insert on `order`
for each row
BEGIN
    IF NEW.valid = true THEN
        UPDATE totpurchaseperpackandvalidity
        SET totalPurchases = totalPurchases + 1
        WHERE (package_id, periodo) IN (NEW.refPack, NEW.periodo);
    END IF;
END;

#IN (SELECT s.idPackage, s.periodo
 #   FROM `package` AS s
  #  WHERE s.idPackage = NEW.refPack
   #   AND s.periodo= NEW.periodo);

WHERE (package_id, periodo) IN (SELECT s.refPack, s.periodo
                                        FROM `order` AS s
                                        WHERE s.refPack = NEW.refPack
                                        AND s.periodo= NEW.periodo);