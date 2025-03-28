SELECT
    DISTINCT I.ANIMAL_ID AS ANIMAL_ID,
    I.ANIMAL_TYPE AS ANIMAL_TYPE,
    I.NAME AS NAME
    
FROM
    ANIMAL_INS I
JOIN
    ANIMAL_OUTS O
    ON I.ANIMAL_ID = O.ANIMAL_ID
    
WHERE
    I.SEX_UPON_INTAKE IN ('Intact Male', 'Intact Female')
    AND O.SEX_UPON_OUTCOME IN ('Neutered Male', 'Spayed Female')
    
    