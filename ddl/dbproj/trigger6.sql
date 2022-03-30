create trigger addToTotalValue
    AFTER INSERT on chosenoptional for each row
BEGIN
    UPDATE optservice
    SET totSold = totSold + optservice.monthly
    WHERE idOptService = NEW.refOptService;
end;