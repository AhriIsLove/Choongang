import { useEffect, useState } from "react";
import useCustomMove from "../../hooks/useCustomMove";
import { deleteOne, getOne, putOne } from "../../api/todoApi";

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
        <></>
    );
};

export default ModifyComponent;