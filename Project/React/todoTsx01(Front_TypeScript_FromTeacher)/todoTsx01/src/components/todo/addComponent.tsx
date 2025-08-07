import { useState, type ChangeEvent } from "react";
import useCustomMove from "../../hooks/useCustomMove";
import ResultModal from "../common/resultModal";
import { postAdd } from "../../api/todoApi";

const initState:TodoAdd = {
  title: '',
  writer: '',
  due_date:''
}
  

function AddComponent() {
    // ... (스프레드 문법)을 사용하면 initState 객체의 내용을 새로운 객체에 복사해서 초깃값으로 사용
    // 안정성 확보 --> 얕은 복사
    const [todo, setTodo] = useState<TodoAdd>({...initState});
    // const [todo, setTodo] = useState<TodoAdd>(initState);
    // 1.  <number | null>은 타입 파라미터(제네릭)
    // 2. (null) --> useState의 초기값
    // 3. result: 현재 상태값 (number 또는 null)
    // 4. setResult: 상태를 업데이트하는 함수
    const [result, setResult] = useState<number | null >(null)
    const {moveToList} = useCustomMove()

    const closeModal = () => {
        setResult(null);
        moveToList();
    }

    const handleChangeTodo = (e: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setTodo((prevState) => ({
                ...prevState,
                [name]: value
            })
        );
    }

   const handleClickAdd = (): void => {
        postAdd(todo)
        .then(result => {
        console.log(result)
        setResult(result.TNO)

        //초기화 
        setTodo({...initState})

        }).catch(e => {
            console.error(e)
        })
    }   

    return (
    <div className = "border-2 border-sky-200 mt-10 m-2 p-4"> 

        {result && <ResultModal title="등록 처리 완료" content={`${result}번 처리`} callbackFn={closeModal} />}

        <div className="flex justify-center">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                <div className="w-1/5 p-6 text-right font-bold">TITLE</div>
                <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md" 
                name="title"
                type={'text'} 
                value={todo.title}
                onChange={handleChangeTodo}
                >
                </input>
                </div>
            </div>
            <div className="flex justify-center">
            <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                <div className="w-1/5 p-6 text-right font-bold">WRITER</div>
                <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md" 
                name="writer"
                type={'text'} 
                value={todo.writer}
                onChange={handleChangeTodo}
                >
                </input>
            </div> 
            </div>
            <div className="flex justify-center">
            <div className="relative mb-4 flex w-full flex-wrap items-stretch">
            <div className="w-1/5 p-6 text-right font-bold">DUEDATE</div>
            <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md" 
                name="due_date"
                type={'date'} 
                value={todo.due_date}
                onChange={handleChangeTodo}
                >
                </input>
            </div>
            </div>
            <div className="flex justify-end">
            <div className="relative mb-4 flex p-4 flex-wrap items-stretch">
            <button type="button" 
            className="rounded p-4 w-36 bg-blue-500 text-xl text-white "
            onClick={handleClickAdd}     
            >
                ADD
            </button>
            </div>
        </div>
    </div>

    );
}

export default AddComponent;


