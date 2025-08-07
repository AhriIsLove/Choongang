import { useState } from "react";
import { createSearchParams, useNavigate, useSearchParams } from "react-router";

// 페이지 네비게이션 유틸리티 함수들을 모아둔 커스텀 훅 
// UseCustomMoveReturn 정확히 그 함수가 반환할 타입을 정의
// 코드를 더 안전하게 작성할 수 있고, 자동완성 같은 IDE 기능도 제공
// TypeScript에서는 함수가 어떤 값을 반환하는지 명확하게 알려주기 위해 이런 반환 타입을 정의
function useCustomMove():UseCustomMoveReturn {

    const navigate = useNavigate();
    const [queryParams] = useSearchParams()
    const [refresh, setRefresh] = useState<boolean>(false);

    const pageStr: string|null = queryParams.get('page') 
    const sizeStr: string|null = queryParams.get('size')

    const page: number = pageStr ? parseInt(pageStr) : 1;
    const size: number = sizeStr ? parseInt(sizeStr) : 10;

    //page=1&size=10
    const queryDefault = createSearchParams({
        page: page.toString(),
        size: size.toString()

    }).toString()


    const moveToModify = (tno: number) => {
        console.log('useCustomMove moveToModify tno->', tno);
        console.log('useCustomMove moveToModify queryDefault->', queryDefault);

        navigate({
            pathname: `../modify/${tno}`,
            search: queryDefault
        })
    }    

    const moveToRead = (tno: number) => {
        navigate({
            pathname: `../read/${tno}`,
            search: queryDefault
        })
    }

    // ? -> TypeScript에서 선택적 매개변수(optional parameter)
    // pageParam은 선택적 매개변수로 정의 
    // pageParam이 제공되지 않으면 기본값으로 page=1, size=10을 사용
    const moveToList = (pageParam?: PageParam) => {
        let queryStr = ''
        if (pageParam) {
            const pageNum = Number(pageParam.page) || 1
            const sizeNum = Number(pageParam.size) || 10 
            queryStr = createSearchParams({
                page: String(pageNum),
                size: String(sizeNum),
            }).toString()

            if(queryStr === queryDefault){
                setRefresh(!refresh)
            }
        } else {
            queryStr = queryDefault;
        }

        navigate({
            pathname: '../list',
            search: queryStr
        })


    }
    return {page, size, moveToList, refresh, moveToModify, moveToRead}

}

export default useCustomMove;