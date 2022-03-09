SELECT o.idOrder, count(*)
FROM dbproj.order as o
    JOIN chosenoptional c on o.idOrder = c.refOrder
    JOIN ownoptservice o2 on o.refPack = o2.refPack
    WHERE o.refPack = 5
GROUP BY o.idOrder;
