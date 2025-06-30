// callbackFn(콜백함수) : 파라미터로 전달된 함수
// - closeModal : ResultModal을 닫고 List로 이동한다.
const ResultModal = ({title, content, callbackFn}) => {
    return (
        // z-[1055] : z-index를 1055로 설정
        // 클릭 가능한 레벨을 z-index로 설정 가능하도록 함
        // 높을 수록 입력 순위가 높음
        // default는 z-index:auto이다
        // auto는 어떠한 index보다 낮음
        <div className="fixed top-0 left-0 z-[1055] flex h-full w-full justify-center bg-black bg-opacity-20"
        // 배경 어디든 클릭시
        onClick={() => {
            if(callbackFn) {
                callbackFn();
            }
        }}>
            {/* dark : 다크모드일때 설정 */}
            <div className="absolute bg-white shadow dark:bg-gray-700 opacity-100 w-1/4 rounded mt-10 mb-10 px-6 min-w-[600px]">
                <div className="justify-center bg-warning-400 mt-6 mb-6 text-2xl border-b-4 border-gray-500">
                    {title}
                </div>
                <div className="text-4xl border-orange-400 border-b-4 pt-4 pb-4">
                    {content}
                </div>
                <div className="justify-end flex">
                    <button className="rounded bg-blue-500 mt-4 mb-4 px-6 pt-4 pb-4 text-lg text-white" 
                    onClick={()=>{
                        if(callbackFn) {
                            callbackFn();
                        }
                    }}>
                        Close Modal
                    </button>
                </div>
            </div>
        </div>
    );
};

export default ResultModal;