// import { data } from "autoprefixer";
import { useEffect, useState } from "react";
import { getList } from "../../api/todoApi";
import useCustomMove from "../../hooks/useCustomMove";
import PageComponent from "../common/PageComponent";

//
const initState = {
    dtoList: [],
    pageNumList: [],
    pageRequestDTO: null,
    prev:false,
    next:false,
    totalCount: 0,
    prevPage: 0,
    nextPage: 0,
    totalPage: 0,
    current: 0
};

const ListComponent = () => {
    const {moveToList, page, size, refresh, moveToRead} = useCustomMove(); //refresh

    // serverData는 나중에 사용
    // initState라는 초기값을 가진 serverData라는 상태 변수를 만들 거고,
    // 이 변수의 값을 업데이트할 때는 setServerData라는 함수를 사용
    // React 커뮤니티에서 약속처럼 쓰는 표준 컨벤션 --> setServerData
    // useState -> 상태 관리 Hook
    const [serverData, setServerData] = useState(initState);

    //React component가 랜더링 될 때마다 특정 작업(Side effect)을 실행할 수 있도록 하는 리액트 Hook
    // useEffect --> 마치 Listener
    // 하단의 page, size, refresh가 변경됨을 감지하면
    // getList를 실행하는 useEffect 함수
    // getList가 성공하면 .then으로 내부 코드까지 실행(이때 getList는 비동기(async)함수)
    useEffect(() => {
        //todoApi.getList() 호출
        getList({page,size}).then(data => {
            //성공시
            // console.log('getList data', data);
            setServerData(data);
        });
    }, [page, size, refresh]);

    return (
        // border-2: 이건 테두리의 두께, border-blue-100: blue-100은 파란색 계열 중에서 아주 연한 색깔 
        // margin-top의 줄임말 요소 위쪽에 여백  mr-2: 이건 margin-right의 줄임말! 요소 오른쪽에 여백
        <div className="border-2 border-blue-100 mt-10 mr-2 ml-2">
            {/*
            flex: 이 클래스가 적용된 요소는 '플렉스 컨테이너'-> 안에 있는 자식 요소들을 원하는 방향으로 정렬하거나 공간을 분배
            flex-wrap:컨테이너 안에 자식 요소들이 너무 많아서 한 줄에 다 못 들어갈 때 자동으로 다음 줄로 넘어가게 해주는 역할 
            mx-auto : margin-left와 margin-right를 auto로 설정 , 부모 요소 안에서 해당 요소가 가운데 정렬
            justify-center : 플렉스 컨테이너 안의 자식 요소들을 메인 축(기본적으로는 가로 방향)을 기준으로 가운데 정렬
            p-6: padding 클래스, 상하좌우 네 방향 모두에 일정한 안쪽 여백
            */}
            <div className="flex flex-wrap mx-auto justify-center p-6">
                {
                    // 1. key= {todo.tno}  리액트에서 목록(리스트) 같은 거 만들 때, 각각의 요소들이 변하거나 추가되거나 
                    // 삭제될 때 리액트가 효율적으로 화면을 업데이트할 수 있게 도와주는 고유한 식별자
                    // 2. className 화면에는 가로 꽉 차게(근데 최소 400px), 적당한 여백과 둥근 모서리, 그림자 효과    
                    // 3. onClick -> moveToRead(todo.tno)})을 실행
                    serverData.dtoList.map(todo => 
                        <div 
                        key={todo.tno} 
                        className="w-full min-w-[400px] p-2 m-2 rounded shadow-md" 
                        onClick={() => moveToRead(todo.tno)}>
                            <div className="flex">
                                <div className="font-extrabold text-xl p-2 w-1/12">
                                    {todo.tno}
                                </div>
                                <div className="font-extrabold text-xl p-2 m-1 w-8/12">
                                    {todo.title}
                                </div>
                                <div className="font-medium text-xl p-2 m-1 w-2/12">
                                    {todo.due_date}
                                </div>
                            </div>
                        </div>
                    )
                }
            </div>
            <PageComponent serverData={serverData} movePage={moveToList}></PageComponent>
        </div>
    );
};

export default ListComponent;