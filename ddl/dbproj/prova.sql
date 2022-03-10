DECLARE x, y int
SELECT o.idOrder, count(*) INTO x
FROM dbproj.order as o
    INNER JOIN ownoptservice o2 on o.refPack = o2.refPack
    WHERE o.refPack = 5
GROUP BY o.idOrder;

SELECT o1.idOrder, count(*) INTO y
FROM dbproj.order as o1
         INNER JOIN ownoptservice o2 on o1.refPack = o2.refPack
WHERE o1.refPack = 5
GROUP BY o1.idOrder;


