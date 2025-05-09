WITH HIGHEST_VIEW AS (
    SELECT
        BOARD_ID
    FROM 
        USED_GOODS_BOARD
    
    WHERE
        VIEWS = (SELECT MAX(VIEWS) FROM USED_GOODS_BOARD)
)


SELECT
    CONCAT('/home/grep/src/', F.BOARD_ID, '/', F.FILE_ID, F.FILE_NAME, F.FILE_EXT) AS FILE_PATH
    
FROM 
    USED_GOODS_FILE F
INNER JOIN
    HIGHEST_VIEW H
    ON F.BOARD_ID = H.BOARD_ID
    
ORDER BY F.FILE_ID DESC