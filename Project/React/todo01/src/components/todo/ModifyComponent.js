import { useEffect, useState } from "react";
import useCustomMove from "../../hooks/useCustomMove";
import { deleteOne, getOne, putOne } from "../../api/todoApi";
import ResultModal from "../common/ResultModal";

const initState = {
    tno:0,
    title:'',
    writer:'',
    due_date:'',
    complete:false,
};

const ModifyComponent = ({tno}) => {
    //수정한 데이터
    const [todo, setTodo] = useState({...initState});
    //결과
    const [result, setResult] = useState(null);

    //작업후 페이지 이동
    const {moveToList, moveToRead} = useCustomMove();

    //수정 버튼 누를 때
    const handleClickModify = () => {
        putOne(todo).then(data => {
            setResult('Modified');
        });
    };

    //삭제 버튼 누를 때
    const handleClickDelete = () => {
        deleteOne(tno).then(data => {
            setResult('Deleted');
        });
    };

    // 모달(팝업) 닫을 때
    const closeModal = () => {
        if(result === 'Deleted'){
            moveToList();
        }
        else{
            moveToRead(tno);
        }
    };

    //tno변경시 다시 조회
    useEffect(() => {
        getOne(tno).then(data => setTodo(data));
    }, [tno]);

    //내용 수정할 때
    const handleChangeTodo = (e) => {
        todo[e.target.name] = e.target.value;

        setTodo({...todo});
    };
    //Complete 수정할 때
    const handleChangeTodoComplete = (e) => {
        const value = e.target.value;

        todo.complete = (value === 'Y');

        setTodo({...todo});
    };

    return (
        <div className="border-2 border-sky-200 mt-10 m-2 p-4">
            {result ? 
            <ResultModal title={'처리결과'} content={result + ' tno : ' + tno} callbackFn={closeModal}></ResultModal>
            :
            <></>}
            <div className="flex justify-center mt-10">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">
                        TNO
                    </div>
                    <div className="w-4/5 p-6 rounded-r border border-solid shadow-md bg-gray-100">
                        {todo.tno}
                    </div>
                </div>
            </div>
            <div className="flex justify-center">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">
                        WRITER
                    </div>
                    <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-300 shadow-md"
                    name="writer"
                    type="text"
                    value={todo.writer}
                    onChange={handleChangeTodo}>
                    </input>
                </div>
            </div>
            <div className="flex justify-center">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">
                        TITLE
                    </div>
                    <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-300 shadow-md"
                    name="title"
                    type="text"
                    value={todo.title}
                    onChange={handleChangeTodo}>
                    </input>
                </div>
            </div>
            <div className="flex justify-center">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">
                        DUEDATE
                    </div>
                    <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-300 shadow-md"
                    name="due_date"
                    type="date"
                    value={todo.due_date}
                    onChange={handleChangeTodo}>
                    </input>
                </div>
            </div>
            <div className="flex justify-center">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">
                        COMPLETE
                    </div>
                    <select className="border-solid border-2 rounded m-1 p-2"
                    name="status"
                    value={todo.complete ? 'Y' : 'N'}
                    onChange={handleChangeTodoComplete}>
                        <option value='Y'>Completed</option>
                        <option value='N'>Not Yet</option>
                    </select>
                </div>
            </div>
            <div className="flex justify-end p-4">
                <button className="inline-block rounded p-4 m-2 text-xl w-32 text-white bg-red-500"
                type="button"
                onClick={handleClickDelete}>
                    DELETE
                </button>
                <button className="rounded p-4 m-2 text-xl w-32 text-white bg-blue-500"
                type="button"
                onClick={handleClickModify}>
                    MODIFY
                </button>
            </div>
        </div>
    );
};

export default ModifyComponent;