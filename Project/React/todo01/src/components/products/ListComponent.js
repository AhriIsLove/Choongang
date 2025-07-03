import { useEffect, useState } from "react";
// import { getList } from "../../api/todoApi";
import useCustomMove from "../../hooks/useCustomMove";
import PageComponent from "../common/PageComponent";

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

    const [serverData, setServerData] = useState(initState);

    useEffect(() => {
        //productsApi.getList() 호출
        // getList({page,size}).then(data => {
        //     //성공시
        //     setServerData(data);
        // });
    }, [page, size, refresh]);

    return (
        <div className="border-2 border-blue-100 mt-10 mr-2 ml-2">
            <div className="flex flex-wrap mx-auto justify-center p-6">
                {
                    serverData.dtoList.map(product => 
                        <div 
                        key={product.pno} 
                        className="w-full min-w-[400px] p-2 m-2 rounded shadow-md" 
                        onClick={() => moveToRead(product.pno)}>
                            <div className="flex">
                                <div className="font-extrabold text-xl p-2 w-1/12">
                                    {product.pno}
                                </div>
                                <div className="font-extrabold text-xl p-2 m-1 w-8/12">
                                    {product.pname}
                                </div>
                                <div className="font-medium text-xl p-2 m-1 w-2/12">
                                    {product.price}
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