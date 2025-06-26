import { useState } from "react";
import { createSearchParams, useNavigate, useSearchParams } from "react-router-dom";

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

    const queryDefault = createSearchParams({page, size}).toString();
};