import { useState } from "react";
import { createSearchParams, useNavigate, useSearchParams } from "react-router-dom";

//숫자 변환 : 없으면 디폴트
const getNum = (param, defaultValue) => {
    if(!param){
        return defaultValue;
    }
    return parseInt(param);
};

const useCustomMove = () => {
    //Navigate 선언
    const navigate = useNavigate();

    //useState
    // refresh변수를 선언하는데 useState로 false가 들어간다.
    // setRefresh()로 변경 가능하다
    const [refresh, setRefresh] = useState(false);
    //useSearchParams
    // 현재 위치의 쿼리 매개변수에 대한 데이터를 읽고 ...
    const [queryParams] = useSearchParams();

    const page = getNum(queryParams.get('page'), 1);
    const size = getNum(queryParams.get('size'), 10);

    // createSearchParams : URL 쿼리 파라미터를 객체 형태로 만들어 주는 함수
    const queryDefault = createSearchParams({page, size}).toString();
    // queryDefault : {page: 1, size: 10}

    // console.log('moveToList queryDefault : ' + queryDefault);
    
    //List페이지로 이동(page=1&size=10)
    const moveToList = (pageParam) => {
        let queryStr = "";
        if(pageParam){
            const pageNum = getNum(pageParam.page, 1);
            const sizeNum = getNum(pageParam.size, 10);
            queryStr = createSearchParams({page:pageNum, size:sizeNum}).toString();
        }
        else{
            queryStr = queryDefault;
        }
        console.log('moveToList queryStr : ' + queryStr);

        //페이지 이동 -> 라우터
        navigate({
            pathname:`../list`, //이동 페이지
            search:queryStr     //queryStr를 파라미터로 페이지 이동
        });

        //refresh를 변경하여 useEffect에서 감지할수 있도록 변경
        setRefresh(!refresh); //추가
    };

    //Read페이지로 이동(tno=1)
    const moveToRead = (tno) => {
        // console.log(queryDefault);
        
        //페이지 이동 -> 라우터
        navigate({
            pathname:`../read/${tno}`,
            search:queryDefault //수정시 기존의 queryStr 유지(page,size값)
        });
    };

    const moveToModify = (tno) => {
        navigate({
            pathname:`../modify/${tno}`,
            search:queryDefault //수정시 기존의 queryStr 유지(page,size값)
        });
    };

    return {moveToList, page, size, refresh, moveToRead, moveToModify};
};

export default useCustomMove;