SELECT c.refOptService, count(*)
FROM 'order' as o
    JOIN chosenoptional c on o.idOrder = c.refOrder
    JOIN package p on o.refPack = p.idPackage
    JOIN `order` o2 on p.idPackage = o2.refPack
