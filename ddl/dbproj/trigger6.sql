create trigger addToTotalValue
    AFTER INSERT on chosenoptional for each row
BEGIN
    UPDATE optservice
    SET totSold = totSold + (optservice.monthly * ( SELECT o.periodo
                                                    FROM `order` AS o
                                                    WHERE o.idOrder=NEW.refOrder))
    WHERE idOptService = NEW.refOptService;
end;