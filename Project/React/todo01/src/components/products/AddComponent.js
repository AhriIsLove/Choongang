import { useRef, useState } from "react";
import useCustomMove from "../../hooks/useCustomMove";
import { productAdd } from "../../api/productsApi";
import ResultModal from "../common/ResultModal";
import FetchingModal from "../common/FetchingModal";

const initState = {
    pname: '',
    pdesc: '',
    price: 0,
    files: [],
};

const AddComponent = () => {
    const [product, setProduct] = useState({ ...initState });
    const uploadRef = useRef();
    const [fetching, setFetching] = useState(false);
    const [result, setResult] = useState(null);// 결과상태

    const { moveToList } = useCustomMove();

    // 데이터 작성/수정시
    const handleChangeProduct = (e) => {
        // e.target : 작성/수정한 태그
        product[e.target.name] = e.target.value;

        setProduct({ ...product });
    };

    // Add 버튼 클릭시
    const handleClickAdd = () => {
        //이미지 파일들
        const files = uploadRef.current.files;
        //FormData : product와 이미지파일을 저장할 객체
        const formData = new FormData();

        for (let i = 0; i < files.length; i++) {
            formData.append("files", files[i]);
        }

        formData.append("pname", product.pname);
        formData.append("pdesc", product.pdesc);
        formData.append("price", product.price);

        // 로딩 시작
        setFetching(true);

        productAdd(formData).then(result => {
            // 성공
            setResult(result.result);// 결과 데이터 변경
            // setProduct({...initState});// product 초기화
            setFetching(false);//로딩 끝
        }).catch(e => {
            // 실패
            console.error(e);
        });
    };

    // 저장 완료시
    const closeModal = () => {
        setResult(null);
        moveToList({ page: 1 });// 목록으로 이동
    }

    return (
        <div className="border-2 border-sky-200 mt-10 m-2 p-4">
            {/* 이미지 로딩 */}
            {fetching ? <FetchingModal /> : <></>}

            {/* 모달 처리(팝업 처리) : 저장 완료시 */}
            {result ? <ResultModal title={'Product Add result'} content={`New ${result} Add 완료`} callbackFn={closeModal}></ResultModal> : <></>}

            <div className="flex justify-center">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">
                        Product Name
                    </div>
                    <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md"
                        name="pname" type={'text'} value={product.pname} onChange={handleChangeProduct}>
                    </input>
                </div>
            </div>
            <div className="flex justify-center">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">
                        Product Description
                    </div>
                    <textarea className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md"
                        rows="4"
                        name="pdesc" value={product.pdesc} onChange={handleChangeProduct}>
                    </textarea>
                </div>
            </div>
            <div className="flex justify-center">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">
                        Product Price
                    </div>
                    <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md"
                        name="price" type={'number'} value={product.price} onChange={handleChangeProduct}>
                    </input>
                </div>
            </div>
            <div className="flex justify-center">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">
                        Files
                    </div>
                    {/* 
                    ref : 파일 임시 저장 위치 
                    multiple : 여러 파일 가능 여부 */}
                    <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md"
                        ref={uploadRef} type={'file'} multiple={true}>
                    </input>
                </div>
            </div>
            <div className="flex justify-end">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <button className="rounded p-4 w-40 bg-blue-500 text-xl text-white"
                        type="button"
                        onClick={handleClickAdd}>
                        Add Product
                    </button>
                </div>
            </div>
        </div>
    );
};

export default AddComponent;