import { useCallback } from "react";
import { Outlet, useNavigate } from "react-router-dom";
import BasicLayout from "../../layouts/BasicLayout";

const IndexPage = () => {
    //URL을 업데이트하고 기록 스택을 관리
    //페이지 이동을 하기 위한 변수 선언
    const navigate = useNavigate();

    //이벤트 생성 : List 클릭
    const handleClickList = useCallback(() => {
        navigate({
            pathname: 'list'
        });
    });
    //이벤트 생성 : add 클릭
    const handleClickAdd = useCallback(() => {
        navigate({
            pathname: 'add'
        });
    });

    return (
        <BasicLayout>
            <div className="text-black font-extrabold mt-10">
                Products Menus
            </div>
            <div className="w-full flex m-2 p-2">
                <div className="text-xl m-1 p-2 w-20 font-extrabold text-center underline" onClick={handleClickList}>
                    List
                </div>
                <div className="text-xl m-1 p-2 w-20 font-extrabold text-center underline" onClick={handleClickAdd}>
                    Add
                </div>
            </div>
            <div className="flex flex-wrap w-full">
                <Outlet />
            </div>
        </BasicLayout>
    );
};

export default IndexPage;