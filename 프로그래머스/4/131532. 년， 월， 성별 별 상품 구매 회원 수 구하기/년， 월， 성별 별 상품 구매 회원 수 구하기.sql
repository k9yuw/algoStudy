-- 1단계: 필요한 정보 정리
WITH USER_SALES AS (
    SELECT 
        O.USER_ID,
        YEAR(O.SALES_DATE) AS YEAR,
        MONTH(O.SALES_DATE) AS MONTH,
        U.GENDER
    FROM ONLINE_SALE O
    JOIN USER_INFO U ON O.USER_ID = U.USER_ID
    WHERE U.GENDER IS NOT NULL
)

-- 2단계: 년/월/성별별로 회원 수 집계
SELECT 
    YEAR,
    MONTH,
    GENDER,
    COUNT(DISTINCT USER_ID) AS USERS
FROM USER_SALES
GROUP BY YEAR, MONTH, GENDER
ORDER BY YEAR, MONTH, GENDER;
