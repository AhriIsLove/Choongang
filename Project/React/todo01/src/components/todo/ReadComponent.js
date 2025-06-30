import { useEffect, useState } from "react";
import useCustomMove from "../../hooks/useCustomMove";
import { getOne } from "../../api/todoApi";

const initState = {
    tno:0,
    title:'',
    writer:'',
    due_date:null,
    complete:false,
};

// 파라미터로 받은 객체를 tno로 사용
// const ReadComponent = (tno) => {

// 파라미터로 받은 객체에서 tno값만 사용
const ReadComponent = ({tno}) => {
    const [todo, setTodo] = useState(initState);
    const {moveToList, moveToModify} = useCustomMove();

    useEffect(() => {
        getOne(tno).then(todoDto => {
            console.log('getOne(tno) : ', todoDto);
            setTodo(todoDto);
        });
    }, [tno]);

    return (
        <div className="border-2 border-sky-200 mt-10 m-2 p-4">
            {makeDiv('Tno',todo.tno)}
            {makeDiv('Title',todo.title)}
            {makeDiv('Writer',todo.writer)}
            {makeDiv('Due_Date',todo.due_date)}
            {makeDiv('Complete',todo.complete ? 'Completed' : 'Not Yet')}
            <div className="flex justify-end p-4 ">
                <button className="rounded p-4 m-2 text-xl w-32 text-white bg-blue-500"
                type="button" 
                onClick={() => moveToList()}>
                    List
                </button>
                <button className="rounded p-4 m-2 text-xl w-32 text-white bg-red-500"
                type="button" 
                onClick={() => moveToModify(tno)}>
                    Modify
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