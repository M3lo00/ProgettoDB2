DECLARE x int
SELECT o.idOrder, count(*) INTO x
FROM dbproj.order as o
    JOIN chosenoptional c on o.idOrder = c.refOrder
    JOIN ownoptservice o2 on o.refPack = o2.refPack
    WHERE o.refPack = 5
GROUP BY o.idOrder;
