#aggiungere una tupla ogni volta che viene creato un pack (INT)
#incrementare il pack ogni volta che viene acquistato
#Due modalità di acquisto ( valid, valid dopo not valid)



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
    after insert
    on `order`
    for each row
BEGIN
    IF NEW.valid = 1 THEN
        UPDATE totpurchaseperpackandvalidity
        SET totalPurchases = totalPurchases + 1
        WHERE package_id =NEW.refPack AND periodo=NEW.periodo;
    END IF;
END;

create trigger updatePurchaseToPackage
    after update
    on `order`
    for each row
BEGIN
    IF NEW.valid = 1 THEN
        UPDATE totpurchaseperpackandvalidity
        SET totalPurchases = totalPurchases + 1
        WHERE package_id =NEW.refPack AND periodo=NEW.periodo;
    END IF;
END;

