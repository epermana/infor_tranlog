impala-shell -q "Insert into transactionlog_impala_test3
select  
infor_itrn.infor_itrnkey as tranID,
infor_itrn.trantype as tranType,
to_date(infor_itrn.adddate) as startDate,
CONCAT(cast(hour(infor_itrn.adddate) as string),':',cast(minute(infor_itrn.adddate) as string),':',cast(second(infor_itrn.adddate) as string) ) as startTime,
to_date(infor_itrn.editdate) as endDate,
CONCAT(cast(hour(infor_itrn.editdate) as string),':',cast(minute(infor_itrn.editdate) as string),':',cast(second(infor_itrn.editdate) as string) ) as endTime,
infor_itrn.editwho as employee,
cast(infor_orderdetail.serialkey as string) as controlNum,
cast(infor_orderdetail.orderlinenumber as string) as lineNum,
cast(infor_itrn.whseid as int) as whID,
cast(infor_itrn.fromloc as string) as fromLoc,
infor_itrn.toloc as toLoc,
cast(infor_itrn.SERIALKEY as string) as palletID,
infor_itrn.SKU as itemNo,
infor_itrn.LOT as lotNo,
cast(infor_itrn.QTY as int) as qty,
infor_itrn.NETWGT as netWeight,
infor_itrn.GROSSWGT as grossWeight,
infor_itrn.storerkey as customer from infor_itrn
 LEFT OUTER JOIN infor_orderdetail 
 ON (infor_itrn.lot=infor_orderdetail.lot)"
