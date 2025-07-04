import { useState } from "react";
import useCustomMove from "../../hooks/useCustomMove";
import { postAdd } from "../../api/todoApi";
import ResultModal from "../common/ResultModal";

const initState = {
    title:'',
    writer:'',
    due_date:'',
};

const AddComponent = () => {
    const [todo, setTodo] = useState({...initState});
    const [result, setResult] = useState(null);// 결과상태

    const {moveToList} = useCustomMove();

    // 데이터 작성/수정시
    const handleChangeTodo = (e) => {
        // e.target : 작성/수정한 태그
        todo[e.target.name] = e.target.value;

        // ...todo : setTodo로 저장하는데 수정된 todo의 데이터만 수정하고 그외 데이터는 유지
        setTodo({...todo});
    };

    // Add 버튼 클릭시
    const handleClickAdd = () => {
        //todoApi.postAdd(todo) 호출
        postAdd(todo).then(result => {
            // 성공
            console.log(result);

            setResult(result.TNO);// 결과 데이터 변경
            setTodo({...initState});// todo 초기화
        }).catch(e => {
            // 실패
            console.error(e);
        });
    };

    // 저장 완료시
    const closeModal = () => {
        setResult(null);
        moveToList();// 목록으로 이동
    }

    return (
        <div className="border-2 border-sky-200 mt-10 m-2 p-4">
            {/* 모달 처리(팝업 처리) : 저장 완료시 */}
            {result ? <ResultModal title={'Add result'} content={`New ${result} Added`} callbackFn={closeModal}></ResultModal> : <></>}
            <div className="flex justify-center">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">
                        Title
                    </div>
                    {/* 
                    rounded-r : 오른쪽 테두리 라운드 */}
                    <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md"
                    name="title" type={'text'} value={todo.title} onChange={handleChangeTodo}>
                    </input>
                </div>
            </div>
            <div className="flex justify-center">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">
                        Writer
                    </div>
                    <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md"
                    name="writer" type={'text'} value={todo.writer} onChange={handleChangeTodo}>
                    </input>
                </div>
            </div>
            <div className="flex justify-center">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">
                        DueDate
                    </div>
                    <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md"
                    name="due_date" type={'date'} value={todo.due_date} onChange={handleChangeTodo}>
                    </input>
                </div>
            </div>
            <div className="flex justify-end">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <button className="rounded p-4 w-36 bg-blue-500 text-xl text-white" 
                    onClick={handleClickAdd}>
                        Add Todo
                    </button>
                </div>
            </div>
        </div>
    );
};

export default AddComponent;