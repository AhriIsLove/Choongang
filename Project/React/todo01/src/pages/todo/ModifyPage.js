import { useParams } from "react-router-dom";
import ModifyComponent from "../../components/todo/ModifyComponent";

const ModifyPage = () => {
    const {tno} = useParams();
    
    return (
        <div className="p-4 w-full bg-white">
            <div className="text-3xl font-extrabold">
                Todo Modify Page {tno}
            </div>
            <ModifyComponent tno={tno}></ModifyComponent>
        </div>
    );
};

export default ModifyPage;