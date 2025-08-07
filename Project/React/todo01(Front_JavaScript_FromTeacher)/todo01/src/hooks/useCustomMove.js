import { useState } from "react"
import { createSearchParams, useNavigate, useSearchParams } from "react-router-dom"

const getNum  = (param, defaultValue) => {

  if(!param){
    return defaultValue
  }
  return parseInt(param)
}

const useCustomMove = () => {
    // navigate 선언 
    const navigate = useNavigate()

    const [refresh, setRefresh] = useState(false)
    const [queryParams] = useSearchParams()

    const page = getNum(queryParams.get('page'), 1)
    const size = getNum(queryParams.get('size'),10)

    // URL 쿼리 파라미터를 객체 형태로 만들어 주는 함수
    // 예시 : {page: 1, size: 10}
    const queryDefault = createSearchParams({page, size}).toString()
    
    const moveToList = (pageParam) => {

        let queryStr = ""
        if(pageParam) {
            const pageNum = getNum(pageParam.page, 1)
            const sizeNum = getNum(pageParam.size, 10)
            // URL 쿼리 파라미터를 객체 형태로 만들어 주는 함수
            // 예시 : {page: 1, size: 10}
            queryStr = createSearchParams({page:pageNum, size: sizeNum}).toString()
        } else {
            queryStr = queryDefault
        }

        console.log('moveToList queryStr->', queryStr)

        navigate({
            pathname: `../list`,  // 이동주소
            search:queryStr       // queryStr 값을 쿼리 파라미터로 붙여서 페이지를 이동
        })


        setRefresh(!refresh) //추가 
    }

    const moveToRead =(num) => {
        console.log(queryDefault)

        navigate({
           pathname: `../read/${num}` ,
           search: queryDefault
        })

    }


    const moveToModify = (num) => {

        console.log(queryDefault)

        navigate({
            pathname: `../modify/${num}`,
            search: queryDefault  //수정시에 기존의 쿼리 스트링 유지를 위해 
        })
  }



    return {moveToList , moveToRead, moveToModify, page , size , refresh }
}

export default useCustomMove
