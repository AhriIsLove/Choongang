create or replace PACKAGE KK_COLLECTION_PKG AS
    g_in_sawonid   VARCHAR2(4)   := 'S003';   ---     손예진 (임의 입력 사원 지정)
    g_prod_cnt	    NUMBER(9)	:= 0;
    --------------------------------------------------------------------------------------                                
    ------- 행동강령 :    <<<  월 수불마감 PGM  >>>                       
    -------               1. 당월기초 입고 수량을 생성한다.(MMSUM30  기말재고를 익월 기초재고로 이월 )
    -------               2. 일자별 거래처 제품별 판매현황(SMCP10)정보를 생성 PGM                          
    -------               3. 일자별 제품별 판매현황(SMProd10)정보를 생성 PGM                               
    -------                  각각의 경우에 대해, 대분류별회계계정코드를 Update[2025.08.16]
    --------------------------------------------------------------------------------------
    
    PROCEDURE   KK_COLLECTION_MAIN(  p_sum_yymm     in	VARCHAR2
                                 ,  p_regi_emp_no  in	VARCHAR2
                                 );
    
    ---------------------------------------------------------------------------------------                                
    ------- 행동강령 : 1. 당월기초 입고 수량을 생성한다.
    ---------------------------------------------------------------------------------------                                
    PROCEDURE   KK_COLLECTION_PRC1( p_sum_yymm     in	VARCHAR2   );
    ---------------------------------------------------------------------------------------                                
    -------           2. 일자별 거래처 제품별 판매현황(SMCP10)정보를 생성 PGM    
    ---------------------------------------------------------------------------------------                                
    PROCEDURE   KK_COLLECTION_PRC2(  p_sum_yymm     in	VARCHAR2   );
    ---------------------------------------------------------------------------------------                                
    -------         3. 일자별 제품별 판매현황(SMProd10)정보를 생성 PGM                                
    ---------------------------------------------------------------------------------------                                
    PROCEDURE   KK_COLLECTION_PRC3( p_sum_yymm     in	VARCHAR2);
    ---------------------------------------------------------------------------------------
    ----------    9. 전체수불마감후 MMSUMM30을 SMSALE에 따라 STCK_QTY 차감 및 마감 처리작업  
    ---------------------------------------------------------------------------------------
    PROCEDURE   KK_COLLECTION_END(   p_sum_yymm     in	VARCHAR2    );
   
   
END;
/******************************************************************************
   KK_COLLECTION_PKG   END
*******************************************************************************/

create or replace PACKAGE BODY KK_COLLECTION_PKG AS
    PROCEDURE   KK_COLLECTION_MAIN(  p_sum_yymm in	VARCHAR2  ,  p_regi_emp_no  in	VARCHAR2) 
    IS
    BEGIN
        DELETE   MMSUM30
        WHERE    SUM_YYMM    =  p_sum_yymm;
    
        -- 일자별 거래처 제품별 판매현황(SMCP10)정보 해당월 삭제 
        DELETE   SMCP10
        WHERE    SUBSTR(YYMMDD,1,6)    =  p_sum_yymm;
        
        -- 일자별 제품별 판매현황(SMProd10)정보 해당월 삭제 
        DELETE   SMProd10
        WHERE    SUBSTR(YYMMDD,1,6)    =  p_sum_yymm;
        
        -- 일자별 판매실적 오류현황(SMSALE_ERR)정보 해당월 삭제 
        DELETE   SMSALE_ERR
        WHERE    YYMM        =  p_sum_yymm;
        
        dbms_output.put_line(' KK_COLLECTION_PRC1 Before  p_sum_yymm => ' ||p_sum_yymm );
        -- 1. 당월기초 입고 수량을 생성한다.
        KK_COLLECTION_PRC1(  p_sum_yymm );
        -- 2. 일자별 거래처 제품별 판매현황(SMCP10)정보를 생성 PGM
        KK_COLLECTION_PRC2(   p_sum_yymm => p_sum_yymm);
        -- 3. 일자별 제품별 판매현황(SMProd10)정보를 생성 PGM
        KK_COLLECTION_PRC3(    p_sum_yymm => p_sum_yymm );
        
        -- 반품현황기록
        
        -- 9. 전체수불마감후 MMSUMM30을 SMSALE에 따라 STCK_QTY 차감 및 마감 처리작업
        KK_COLLECTION_END(     p_sum_yymm => p_sum_yymm );
        
        COMMIT;
        
    END;
    
    
    /***************************************************************************
    Procedure Name : KK_COLLECTION_PRC1
    Description    : 당월기초 입고 수량을 생성한다.
    ***************************************************************************/
    PROCEDURE   KK_COLLECTION_PRC1(  p_sum_yymm     in	VARCHAR2    )   
    IS
   
    BEGIN
        DBMS_OUTPUT.ENABLE;
        dbms_output.put_line(' KK_COLLECTION_PRC1 p_sum_yymm => ' ||p_sum_yymm );
        ---     1) 당월 기초 입고 수량을 생성한다.
        INSERT INTO MMSUM30
            (    SUM_YYMM       
            ,   ITEM_CODE       
            ,   ITEM_GUBN       
            ,   STCK_QTY
            ,   SawonID
            ,   RegiDate
            )
        ( SELECT   p_sum_yymm
                    ,  ITEM_CODE
                    ,  '0'        -- 기초
                    ,  STCK_QTY
                     ,  SawonID
                    ,  SYSDATE
        FROM     MMSUM30   
        WHERE    SUM_YYMM  = TO_CHAR(ADD_MONTHS (TO_DATE(p_sum_yymm,'YYYYMM'),-1),'YYYYMM')
        AND      ITEM_GUBN = '1'    -- 기말
        );
    END;
    
    /**************************************************************************************
    Project        : KK 영업매출현황
    Module         : 수불관리
    Procedure Name : KK_COLLECTION_PRC2 
    Description    : 일자별 거래처 제품별 판매현황(SMCP10)정보를 생성한다.
                   - 일별 판매실적 현황(SMSALE)을 읽어 일자별 거래처 제품별 판매현황(SMCP10)정보를 생성
                   - 일별 판매실적 현황 , 제품(Product) 테이블 JOIN
                   - 사원은 global 변수인 g_in_sawonid 으로 입력
    Program History
    --------------------------------------------------------------------------
    Date       In Charge        Version   Description
    --------------------------------------------------------------------------
    2023.02.23 강태광            1.0      최초작성
    *************************************************************************************/
    PROCEDURE   KK_COLLECTION_PRC2(  p_sum_yymm     in	VARCHAR2)   
    IS  
        --- 일별 판매실적 현황(SMSALE)을 읽음 
        CURSOR  CSR_SMSALE  IS
        SELECT     S.YYMMDD     YYMMDD
                ,S.CustomID    CustomID 
                ,S.ITEM_CODE  ITEM_CODE   
                ,S.STCK_QTY   STCK_QTY 
                , P.Danga         Danga 
        FROM      SMSALE   S ,  Product P   --- 일별 판매실적 현황 , 제품(Product) 테이블
        WHERE     S.ITEM_CODE  = P.ITEM_CODE 
        AND       SUBSTR(S.YYMMDD,1,6)  = p_sum_yymm 
        ;
    BEGIN
    DBMS_OUTPUT.ENABLE;
    FOR 	rec_smsale IN CSR_SMSALE LOOP
        ------------------------------------------------------------------
        --     Initialize
        ------------------------------------------------------------------
        --  g_goods_trans_qty	:=	0;		---  
        INSERT INTO SMCP10   
            (   YYMMDD       
            ,   CustomID       
            ,   ITEM_CODE       
            ,   STCK_QTY
            ,   Danga
            ,   SawonID
            ,   RegiDate
            )
        VALUES(rec_smsale.YYMMDD 
            , rec_smsale.CustomID            
            , rec_smsale.ITEM_CODE   
            , rec_smsale.STCK_QTY   
            , rec_smsale.Danga                  
            , g_in_sawonid                               
            , SYSDATE
        );
    END LOOP;
    
    EXCEPTION
        WHEN OTHERS THEN
              DBMS_OUTPUT.PUT_LINE(SQLERRM||'에러 발생 ');
    END  KK_COLLECTION_PRC2;

    /**************************************************************************************************
    Project        : KK 영업매출현황
    Module         : 수불관리
    Procedure Name : KK_COLLECTION_PRC3 
    Description    :  일자별 제품별 판매현황(SMProd10)정보를 생성한다.
                   - 일별 판매실적 현황(SMSALE)을 읽어 일자별  제품별 판매현황(SMProd10)정보를 생성
                 
    Program History
    --------------------------------------------------------------------------
    Date       In Charge        Version   Description
    --------------------------------------------------------------------------
    2023.02.23 강태광            1.0      최초작성
    ************************************************************************************************/
    PROCEDURE   KK_COLLECTION_PRC3(  p_sum_yymm     in	VARCHAR2 )   
    IS
        --- GROUP BY 가지고 ==> YMMDD, ITEM_CODE별 수량 합계 에 대하여
        --- GROUP BY 가지고 ==> YMMDD, ITEM_CODE별 수량 합계 에 대하여 
        CURSOR  CSR_SMSALE  IS
        SELECT S.YYMMDD         YYMMDD
               ,S.ITEM_CODE     ITEM_CODE
               ,SUM(S.STCK_QTY) STCK_QTY  
               ,AVG(P.Danga)    Danga 
        FROM        SMSALE S ,Product P  -- 일별 판매실적현황 ,제품(Product)테이블
        WHERE       S.ITEM_CODE = P.ITEM_CODE 
        AND         SUBSTR(S.YYMMDD,1,6) = p_sum_yymm
        GROUP BY    S.YYMMDD, S.ITEM_CODE       
        ;
    BEGIN
        DBMS_OUTPUT.ENABLE;
    FOR rec_smsale_item IN CSR_SMSALE LOOP
        ------------------------------------------------------------------
        --     Initialize
        ------------------------------------------------------------------
        INSERT INTO SMProd10      
            (   YYMMDD       
            ,   ITEM_CODE       
            ,   STCK_QTY
            ,   Danga
            ,   SawonID
            ,   RegiDate
            )
        VALUES( rec_smsale_item.YYMMDD 
            , rec_smsale_item.ITEM_CODE   
            , rec_smsale_item.STCK_QTY   
            , rec_smsale_item.Danga                  
            , g_in_sawonid                               
            , SYSDATE
        );
    
    END LOOP;
    EXCEPTION
        WHEN OTHERS THEN
              DBMS_OUTPUT.PUT_LINE(SQLERRM||'에러 발생 ');
    END;
    
    /**************************************************************************************************
    Project        : KK 영업매출현황
    Module         : 수불관리
    Procedure Name : KK_COLLECTION_END 
    Description    : 전체 수불마감후 MMSUMM30을 SMSALE에 따라 STCK_QTY 
                차감 및 마감 처리작업   
    1.  만약 창고 기초재고가 판매량보다 크다면 기말재고 입력       
    2.  만약 창고 기초재고가 판매량보다 작다면 SMSALE_ERR 입력 - 횡령의심!
    Program History
    --------------------------------------------------------------------------
    Date       In Charge        Version   Description
    --------------------------------------------------------------------------
    2023.02.23 강태광            1.0      최초작성
    ************************************************************************************************/
    PROCEDURE   KK_COLLECTION_END(  p_sum_yymm     in	VARCHAR2)   
    IS
        --- MMSUMM30을 SMSALE에 대하여    YYMMDD, ITEM_CODE별 수량 합계 차감
        CURSOR   CSR_Store_Remain  IS
        SELECT   SUBSTR(S.YYMMDD,1,6)           YYMM
                 , S.ITEM_CODE                  ITEM_CODE
                 , SUM(S.STCK_QTY)              S_STCK_QTY  
                 , AVG(M.STCK_QTY)              M_STCK_QTY -- AVG를 한 이유는 어차피 1개만 뽑아내서 SUM,AVG,MIN,MAX가 동일한 값이 나오지만 GROUP BY를 사용해야 하기 때문에 아무거나 사용하였음
        FROM     ( SELECT * FROM SMSALE
                    WHERE  SUBSTR(YYMMDD,1,6) = p_sum_yymm
                  )   S ,   -- 일별 판매실적현황
                  ( SELECT * FROM MMSUM30
                    WHERE    SUM_YYMM  = p_sum_yymm
                    AND      ITEM_GUBN = '0'    -- 기초 재고에 한해   
                    )  M      -- 창고 재고(MMSUM30)테이블
        WHERE     S.ITEM_CODE  = M.ITEM_CODE
        GROUP BY  SUBSTR(S.YYMMDD,1,6) , S.ITEM_CODE
    ;
    
    BEGIN
        DBMS_OUTPUT.ENABLE;
        FOR 	rec_Store_Remain  IN CSR_Store_Remain  LOOP
            ------------------------------------------------------------------
            --    만약 창고 기초재고가 판매량보다 크다면 기말재고 입력 
            ------------------------------------------------------------------
            IF  rec_Store_Remain.M_STCK_QTY >  rec_Store_Remain.S_STCK_QTY  THEN  
                INSERT INTO MMSUM30       
                    (    SUM_YYMM        
                    ,   ITEM_CODE       
                    ,   ITEM_GUBN
                    ,   STCK_QTY
                    ,   SawonID
                    ,   RegiDate
                )
                VALUES(  rec_Store_Remain.YYMM 
                    , rec_Store_Remain.ITEM_CODE   
                    , '1'                              -- 기말재고    
                    , rec_Store_Remain.M_STCK_QTY - rec_Store_Remain.S_STCK_QTY   
                    , g_in_sawonid                               
                    ,  SYSDATE
                );
            ELSE   -- rec_Store_Remain.M_STCK_QTY <  rec_Store_Remain.S_STCK_QTY 
                INSERT INTO SMSALE_ERR
                    (    YYMM
                    ,   ITEM_CODE
                    ,   MMSUM30_QTY
                    ,   SMSALE_QTY
                    ,   SawonID
                    ,   RegiDate
                )
                VALUES(  rec_Store_Remain.YYMM 
                    , rec_Store_Remain.ITEM_CODE   
                    , rec_Store_Remain.M_STCK_QTY   -- 기초재고    
                    , rec_Store_Remain.S_STCK_QTY   -- 판매수량
                    , g_in_sawonid                               
                    , SYSDATE
                );
                g_prod_cnt  :=   rec_Store_Remain.M_STCK_QTY - rec_Store_Remain.S_STCK_QTY;
                dbms_output.put_line(rec_Store_Remain.YYMM||' 년월에 ' || rec_Store_Remain.ITEM_CODE  || ' 재고부족 양=>' || g_prod_cnt  );
                dbms_output.put_line('재고부족 양=>'||  g_prod_cnt);
            END IF;
        
        END LOOP;
        EXCEPTION
            WHEN OTHERS THEN
                  DBMS_OUTPUT.PUT_LINE(SQLERRM||'에러 발생 ');
   END;
END;
