const PageComponent = ({serverData, movePage}) => {
    return (
        <div className="flex justify-center m-6">
            {/* 이전페이지 이동 버튼 */}
            {serverData.prev ? 
            <div className="m-2 p-2 w-16 text-center font-bold text-blue-400"
            onClick={() => movePage({page:serverData.prevPage})}>
                Prev
            </div>
            :
            <></>}
            {/* 페이지 목록 */}
            {/* pageNumList를 반복하는데 pageNum을 Key로 지정함 */}
            {serverData.pageNumList.map(pageNum => 
                <div key={pageNum} 
                className={`m-2 p-2 w-12 text-center rounded shadow-md text-white 
                ${serverData.current == pageNum ? 'bg-gray-500' : 'bg-blue-400'}`}
                onClick={() => movePage({page:pageNum})}>
                    {pageNum}
                </div>
            )}
            {/* 다음페이지 이동 버튼 */}
            {serverData.next ? 
            <div className="m-2 p-2 w-16 text-center font-bold text-blue-400"
            onClick={() => movePage({page:serverData.nextPage})}>
                Next
            </div>
            :
            <></>}
        </div>
    );
};

export default PageComponent;