/*Best seller optional product, i.e. the optional product with the
  greatest value of sales across all the sold service packages.*/
use dbproj;
create table bestOpt
(
    bestOpt_id      int primary key auto_increment,
    opt_id          int not null ,
    totalValue      int unsigned default '0' not null,
    constraint opt_fk
        foreign key (opt_id) references optservice (idOptService),
    index ind (totalValue)
);

CREATE TRIGGER newOptService
    AFTER INSERT ON optservice for each row
BEGIN
    INSERT INTO bestOpt (opt_id)
        VALUE (NEW.idOptService);
END;

create trigger addTotalValue
    after insert
    on chosenoptional
    for each row
BEGIN
    UPDATE bestOpt
    SET totalValue = totalValue + ( SELECT o.monthly
                                    FROM optservice o
                                    WHERE o.idOptService=NEW.refOptService)
    WHERE opt_id = NEW.refOptService;
END;
