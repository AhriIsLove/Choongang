import { useEffect, useState } from "react";
import useCustomMove from "../../hooks/useCustomMove";
import { getList } from "../../api/todoApi";
import PageComponent from "../common/pageComponent";

function ListComponent() {

    const {page,size,refresh, moveToList, moveToRead}:UseCustomMoveReturn = useCustomMove(); 

    // 서버에서 데이터를 아직 받아오지 않았을 때 serverData가 undefined 상태로 있다가, 
    // 서버 응답이 오면 setServerData를 통해 PageResponseDTO<Todo> 타입의 실제 데이터로 
    // 채워진다는 것을 명시
    const [serverData, setServerData] = useState<PageResponseDTO<Todo>|undefined>()

    useEffect(() => {
        getList({page, size}).then(data => {
            console.log('ListComponent useEffect data->', data);
            setServerData(data);
        });
    }, [page, size, refresh]);

    return (  
        <div className="border-2 border-blue-100 mt-10 mr-2 ml-2">
        {serverData &&
        <>
            <div className="flex flex-wrap mx-auto justify-center p-6">
                {serverData.dtoList.map(todo =>
                    <div
                    key= {todo.tno} 
                    className="w-full min-w-[400px] p-2 m-2 rounded shadow-md"
                    onClick={() => moveToRead(todo.tno)} //이벤트 처리 추가 
                    >
                        <div className="flex ">
                            <div className="font-extrabold text-2xl p-2 w-1/12">
                                {todo.tno}
                            </div>
                            <div className="text-1xl m-1 p-2 w-8/12 font-extrabold">
                                {todo.title}
                            </div>
                            <div className="text-1xl m-1 p-2 w-2/10 font-medium">
                                {todo.due_date}
                            </div>
                        </div>
                    </div>
                )}
            </div>
            <PageComponent serverData={serverData} movePage={moveToList}></PageComponent>
        </>
        }

        </div>
    );
}

export default ListComponent;