Select CARD.*
FROM GROUPS, GROUPCARD, CARD
WHERE CARD.ID=GROUPCARD.CARDID AND GROUPCARD.GROUPID=GROUPS.ID AND GROUPS.ID=2
ORDER BY CARD.ID