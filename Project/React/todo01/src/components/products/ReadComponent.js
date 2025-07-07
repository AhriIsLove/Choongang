import { useEffect, useState } from "react";
import useCustomMove from "../../hooks/useCustomMove";
import { API_SERVER_HOST, getOne } from "../../api/productsApi";

const initState = {
    pno: 0,
    pname: '',
    pdesc: '',
    price: 0,
    uploadFileNames: [],
};

const host = API_SERVER_HOST;

// 파라미터로 받은 객체에서 pno값만 사용
const ReadComponent = ({ pno }) => {
    const [product, setProduct] = useState(initState);
    const [fetching, setFetching] = useState(false);
    const { moveToList, moveToModify } = useCustomMove();

    useEffect(() => {
        setFetching(true);
        getOne(pno).then(productDto => {
            setProduct(productDto);
            setFetching(false);
        });
    }, [pno]);

    return (
        <div className="border-2 border-sky-200 mt-10 m-2 p-4">
            {makeDiv('PNO', product.pno)}
            {makeDiv('PNAME', product.pname)}
            {makeDiv('PRICE', product.price)}
            {makeDiv('PDESC', product.pdesc)}
            <div className="w-full justify-center flex flex-col m-auto items-center">
                {product.uploadFileNames.map((imgFile, i) =>
                    <img className="p-4 w-1/2"
                        alt="product" src={`${host}/api/products/view/${imgFile}`}
                        key={i}>
                    </img>
                )}
            </div>

            <div className="flex justify-end p-4 ">
                <button className="rounded p-4 m-2 text-xl w-32 text-white bg-blue-500"
                    type="button"
                    onClick={() => moveToList()}>
                    Product List
                </button>
                <button className="rounded p-4 m-2 text-xl w-32 text-white bg-red-500"
                    type="button"
                    onClick={() => moveToModify(pno)}>
                    Product Modify
                </button>
            </div>
        </div>
    );
};

const makeDiv = (title, value) =>
    <div className="flex justify-center">
        <div className="relative mb-4 flex w-full flex-wrap items-stretch">
            <div className="w-1/5 p-6 text-right font-bold bg-red-100">{title}</div>
            <div className="w-4/5 p-6 rounded-r border border-solid shadow-md">{value}</div>
        </div>
    </div>

export default ReadComponent;