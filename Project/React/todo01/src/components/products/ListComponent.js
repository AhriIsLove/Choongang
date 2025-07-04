import { useEffect, useState } from "react";
import useCustomMove from "../../hooks/useCustomMove";
import PageComponent from "../common/PageComponent";
import FetchingModal from "../common/FetchingModal";
import { API_SERVER_HOST, getList } from "../../api/productsApi";

const host = API_SERVER_HOST;

const initState = {
    dtoList: [],
    pageNumList: [],
    pageRequestDTO: null,
    prev: false,
    next: false,
    totalCount: 0,
    prevPage: 0,
    nextPage: 0,
    totalPage: 0,
    current: 0
};

const ListComponent = () => {
    const { moveToList, page, size, refresh, moveToRead } = useCustomMove(); //refresh

    const [serverData, setServerData] = useState(initState);

    const [fetching, setFetching] = useState(false);

    useEffect(() => {
        setFetching(true);
        //productsApi.getList() 호출
        getList({ page, size }).then(data => {
            //성공시
            setServerData(data);
            setFetching(false);
        });
    }, [page, size, refresh]);

    return (
        <div className="border-2 border-blue-100 mt-10 mr-2 ml-2">
            {fetching ? <FetchingModal /> : <></>}
            <div className="flex flex-wrap mx-auto justify-center p-6">
                {
                    serverData.dtoList.map(product =>
                        <div
                            key={product.pno}
                            className="w-1/2 min-w-[400px] p-1 rounded shadow-md border-2"
                            onClick={() => moveToRead(product.pno)}>
                            <div className="flex flex-col h-full">
                                <div className="font-extrabold text-2xl p-2 w-full">
                                    {product.pno}
                                </div>
                                <div className="text-xl m-1 p-2 w-full flex flex-col">
                                    <div className="w-full overflow-hidden">
                                        <img className="m-auto rounded-md w-60"
                                        alt="product" src={`${host}/api/products/view/s_${product.simage}`}>
                                        </img>
                                    </div>
                                    <div>
                                        <div className="text-center p-1">
                                            이름 : {product.pname}
                                        </div>
                                        <div className="text-center p-1">
                                            가격 : {product.price}
                                        </div>
                                    </div>
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